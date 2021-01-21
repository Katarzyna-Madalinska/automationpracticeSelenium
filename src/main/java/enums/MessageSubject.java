package enums;

public enum MessageSubject {
    CUSTMER_SERVICE("Customer service"), WEBMASTER("Webmaster");
    private String value;

    MessageSubject(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
