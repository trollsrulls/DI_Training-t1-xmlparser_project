package com.epam.training.xmlparser.domain;

import java.util.HashMap;
import java.util.Map;

public class JSONObject implements JSONComposite {

    private Map<String, JSONComposite> entries;

    public JSONObject() {
        entries = new HashMap<>();
    }

    public JSONComposite get(String key) {
        return entries.get(key);
    }

    public void put(String key, JSONComposite value) {
        entries.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");

        final String quot = "\"";
        for (Map.Entry<String, JSONComposite> item : entries.entrySet()) {
            builder.append(quot)
                    .append(item.getKey())
                    .append(quot)
                    .append(": ")
                    .append(item.getValue() instanceof JSONValue ? quot + item.getValue() + quot : item.getValue())
                    .append(", ");
        }
        if (builder.length() > 2) {
            builder.delete(builder.length() - 2, builder.length() - 1);
        }

        builder.append("}");
        return builder.toString();
    }

}
