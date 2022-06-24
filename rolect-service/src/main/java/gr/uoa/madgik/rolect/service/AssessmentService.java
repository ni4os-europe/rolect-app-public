package gr.uoa.madgik.rolect.service;

import com.lowagie.text.DocumentException;
import gr.uoa.madgik.rolect.exception.ResourceNotFoundException;
import gr.uoa.madgik.rolect.model.assessment.Assessment;
import gr.uoa.madgik.rolect.model.assessment.Response;
import gr.uoa.madgik.rolect.model.submitted_form.SubmittedForm;
import gr.uoa.madgik.rolect.pdf.PdfService;
import gr.uoa.madgik.rolect.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class AssessmentService {

    @Autowired
    AssessmentRepository assessmentRepository;

    @Autowired
    AuthService authService;

    @Autowired
    PdfService pdfService;

    @Autowired
    ResponseService responseService;

    @Value("${app.pdf.base-path}")
    private String reportsBaseDirectory;

    public Assessment saveAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    public Assessment submitAssessment(SubmittedForm submittedForm, Response response){

        if(submittedForm.getAssessmentId() != null){
            return updateAssessment(submittedForm);
        }
        else return saveAssessment(response);
    }

    public Assessment saveAssessment(Response response){
        Assessment assessment = new Assessment();
        assessment.setCreatedAt(response.getCreatedAt());
        assessment.setUpdatedAt(response.getUpdatedAt());
        assessment.setUserId(response.getUserId());
        assessment.setResponseId(response.getId());
        assessment.setStatus(response.getStatus());
        assessment.setResourceUrl(response.getResourceUrl());
        return assessmentRepository.save(assessment);
    }

    public Assessment updateAssessment(SubmittedForm submittedForm){
        Assessment assessment = getAssessmentById(submittedForm.getAssessmentId());
        assessment.setStatus(submittedForm.getStatus());
        assessment.setResourceUrl(submittedForm.getResourceUrl());
        assessment.setUpdatedAt(new Date());
        return assessmentRepository.save(assessment);
    }


    public List<Assessment> getAssessments(Long id){
        return assessmentRepository.findAllByUserId(id);
    }

    public void deleteAssessment(Long id){
        responseService.deleteById(getAssessmentById(id).getResponseId());
        assessmentRepository.deleteById(id);
    }

    public Assessment getAssessmentById(Long id){
        return assessmentRepository.findById(id).orElse(null);
    }

    public byte[] createAssessmentReport(Long id) throws IOException, DocumentException {

        Assessment assessment = getAssessmentById(id);
        Response response = responseService.getResponseById(assessment.getResponseId());
        return pdfService.generatePdf(response.getAnswers(),response.getCreatedAt());
    }

    public byte[] getAssessmentReport(String uuid) throws IOException {
        Assessment assessment = assessmentRepository.findByUuid(uuid).orElseThrow(
                () -> new ResourceNotFoundException("uuid", uuid));
        return pdfService.getReportPdf(assessment.getReportPath());
    }

    public String savePdfReport(Long id, byte[] arr, String fileName, String username) throws IOException, DocumentException{
        Path pathNoBase = Paths.get(pdfService.createTodayDirForUserIfNotExists(username), fileName.replace(" ", "_") + ".pdf");

        Path fullPath = Paths.get(reportsBaseDirectory, pathNoBase.toString());
        try (OutputStream os = Files.newOutputStream(fullPath)) {
            os.write(arr);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return pathNoBase.toString();
    }
}
