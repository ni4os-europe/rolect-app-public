package gr.uoa.madgik.rolect.model.submitted_form;

public class QuestionResponse {

    String name;
    Boolean mandatory;
    Boolean hidden;
    String priority;
    Object response;

    public QuestionResponse() {
    }

    public QuestionResponse(String name, Boolean mandatory, String priority, Object response) {
        this.name = name;
        this.mandatory = mandatory;
        this.priority = priority;
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
