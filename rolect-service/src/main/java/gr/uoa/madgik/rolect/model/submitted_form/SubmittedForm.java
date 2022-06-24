package gr.uoa.madgik.rolect.model.submitted_form;

public class SubmittedForm {

    Long assessmentId;
    String responseId;
    String status;
    String resourceUrl;
    FormResponse answers;


    public SubmittedForm(Long assessmentId, String responseId, String status, String resourceUrl, FormResponse answers) {
        this.assessmentId = assessmentId;
        this.responseId = responseId;
        this.status = status;
        this.resourceUrl = resourceUrl;
        this.answers = answers;
    }

    public Long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public FormResponse getAnswers() {
        return answers;
    }

    public void setAnswers(FormResponse answers) {
        this.answers = answers;
    }
}
