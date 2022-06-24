package gr.uoa.madgik.rolect.model.schema;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class Question {


    @Field("id")
    String id;
    String name;
    String description;
    String sectionId;
    Integer order;
    Boolean mandatory;
    String priority;
    String responseType;
    String vocabularyId;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(String vocabularyId) {
        this.vocabularyId = vocabularyId;
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
