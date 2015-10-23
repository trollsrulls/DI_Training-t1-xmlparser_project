package com.epam.training.xmlparser.domain;

public class JSONValue implements JSONComposite {

    public static final String TEXT_KEY = "#text";

    private String value;

    public JSONValue() { }

    public JSONValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
