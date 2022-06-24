package gr.uoa.madgik.rolect.controller.compliance;

import com.lowagie.text.DocumentException;
import gr.uoa.madgik.rolect.mail.MailService;
import gr.uoa.madgik.rolect.model.assessment.Assessment;
import gr.uoa.madgik.rolect.model.assessment.Response;
import gr.uoa.madgik.rolect.model.misc.ContactForm;
import gr.uoa.madgik.rolect.model.misc.Country;
import gr.uoa.madgik.rolect.model.submitted_form.SubmittedForm;
import gr.uoa.madgik.rolect.pdf.PdfService;
import gr.uoa.madgik.rolect.security.UserPrincipal;
import gr.uoa.madgik.rolect.service.AssessmentService;
import gr.uoa.madgik.rolect.service.AuthService;
import gr.uoa.madgik.rolect.service.CountryService;
import gr.uoa.madgik.rolect.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ComplianceController {

    @Autowired
    MailService mailService;

    @Autowired
    AuthService authService;

    @Autowired
    AssessmentService assessmentService;

    @Autowired
    ResponseService responseService;

    @Autowired
    CountryService countryService;

    @PostMapping("/contact")
    public void getContact(@RequestBody ContactForm payload){
        mailService.sendMail(payload);
    }

    @PostMapping(value = "/submit")
    public ResponseEntity<Map<String, String>> submit(@RequestBody SubmittedForm payload) throws DocumentException, IOException {
        // Save response
        // Save assessment
        Assessment cl = assessmentService.submitAssessment(payload, responseService.submitResponse(payload));
        byte[] reportBytes = assessmentService.createAssessmentReport(cl.getId());

        UserPrincipal p = authService.getPrincipal();
        String path = assessmentService.savePdfReport(cl.getId(), reportBytes, "assessment_"+cl.getId(), p == null ? "unauthenticated" : p.getUsername());

        cl.setReportPath(path);
        assessmentService.saveAssessment(cl);

        return ResponseEntity.ok(Collections.singletonMap("id", cl.getUuid()));
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getAssessmentReport(@RequestParam("id") String uuid) throws IOException, DocumentException {
        System.out.println(uuid);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "Report");
        headers.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,HttpHeaders.CONTENT_DISPOSITION);
        return ResponseEntity.ok()
                .headers(headers)
                .body(assessmentService.getAssessmentReport(uuid));
    }

    @GetMapping("/assessment")
    public ResponseEntity<Assessment> getAssessment(@RequestParam("id") Long id){
        return ResponseEntity.ok(assessmentService.getAssessmentById(id));
    }

    @GetMapping("/response")
    public ResponseEntity<Response> getResponse(@RequestParam("id") String id){
        return ResponseEntity.ok(responseService.getResponseById(id));
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getCountries(){
        return ResponseEntity.ok(countryService.getAllCountries());
    }

}
