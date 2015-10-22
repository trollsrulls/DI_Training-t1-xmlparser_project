package com.epam.training.xmlparser.domain;

public class JSONValue implements JSONComposite {

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
