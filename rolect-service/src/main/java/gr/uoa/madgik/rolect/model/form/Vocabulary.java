package gr.uoa.madgik.rolect.model.form;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class Vocabulary {

    @Field("id")
    String id;
    String name;
    String description;
    List<Term> terms;

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

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }
}
