package gr.uoa.madgik.rolect.controller.compliance;

import com.lowagie.text.DocumentException;
import gr.uoa.madgik.rolect.mail.MailService;
import gr.uoa.madgik.rolect.model.response.FormResponse;
import gr.uoa.madgik.rolect.pdf.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
public class ComplianceController {

    @Autowired
    PdfService pdfService;

    @Autowired
    MailService mailService;

    @PostMapping("/contact")
    public void getContact(@RequestBody Map<String, Object> payload){
        mailService.sendMail(payload);
    }

    @PostMapping(value = "/download",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getReport(@RequestBody FormResponse payload) throws IOException, DocumentException {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"RoLECT_Report");
        headers.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,HttpHeaders.CONTENT_DISPOSITION);
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfService.generatePdf(payload));
    };
}
