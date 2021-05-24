package gr.uoa.madgik.rolect.model.form;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class Section {

    @Field("id")
    String id;
    String name;
    String description;
    String detailedDescription;
    Integer order;
    Boolean mandatory;
    String dependingQuestionId;
    List<String> dependingAnswerIds;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getDependingQuestionId() {
        return dependingQuestionId;
    }

    public void setDependingQuestionId(String dependingQuestionId) {
        this.dependingQuestionId = dependingQuestionId;
    }

    public List<String> getDependingAnswerIds() {
        return dependingAnswerIds;
    }

    public void setDependingAnswerIds(List<String> dependingAnswerIds) {
        this.dependingAnswerIds = dependingAnswerIds;
    }
}
