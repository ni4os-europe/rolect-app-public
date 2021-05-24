package gr.uoa.madgik.rolect.model.response;

import java.util.List;

public class SectionResponse {

    String name;
    Boolean hidden;
    List<QuestionResponse> questions;

    public SectionResponse() {
    }

    public SectionResponse(String name, List<QuestionResponse> questions) {
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public List<QuestionResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionResponse> questions) {
        this.questions = questions;
    }
}
