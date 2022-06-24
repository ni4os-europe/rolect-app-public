package gr.uoa.madgik.rolect.model.schema;

import org.springframework.data.mongodb.core.mapping.Field;

public class Term {
    @Field("id")
    String id;
    String label;
    String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
