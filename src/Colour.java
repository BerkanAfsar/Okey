public enum Colour {

    RED("R","Red"),
    YELLOW("Y","Yellow"),
    BLUE("B","Blue"),
    BLACK("BL", "Black");


    private String key;
    private String value;


    Colour(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
