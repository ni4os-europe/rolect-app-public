package gr.uoa.madgik.rolect.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String field;
    private Object value;

    public ResourceNotFoundException(String field, Object value) {
        super(String.format("Could not find resource (field=%s, value(%s)=%s)", field, value.getClass(), value));
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
