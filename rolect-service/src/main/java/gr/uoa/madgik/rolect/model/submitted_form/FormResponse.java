package gr.uoa.madgik.rolect.model.submitted_form;

import java.util.List;

public class FormResponse {

    List<SectionResponse> sections;

    public FormResponse(List<SectionResponse> sections) {
        this.sections = sections;
    }

    public FormResponse() {
    }

    public List<SectionResponse> getSections() {
        return sections;
    }

    public void setSections(List<SectionResponse> sections) {
        this.sections = sections;
    }

    public Boolean checkObligatoryFields(){

        for (SectionResponse section : this.sections) {
            for (QuestionResponse question : section.questions) {
                if (question.priority.equals("obligatory")){
                    if (question.response == null)
                        return false;
                    if (question.response.equals(""))
                        return false;
                    if (question.response.equals("No"))
                        return false;
                }
            }
        }
        return true;
    }
}

