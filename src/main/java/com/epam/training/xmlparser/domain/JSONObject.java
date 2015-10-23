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
        builder.append(JSONConstants.OBJECT_PREFIX);
        for (Map.Entry<String, JSONComposite> item : entries.entrySet()) {
            builder.append(JSONConstants.QUOT)
                    .append(item.getKey())
                    .append(JSONConstants.QUOT)
                    .append(JSONConstants.KEY_VALUE_SEPARATOR)
                    .append(item.getValue())
                    .append(JSONConstants.ITEM_SEPARATOR);
        }
        if (builder.length() > JSONConstants.ITEM_SEPARATOR.length()) {
            builder.delete(builder.length() - JSONConstants.ITEM_SEPARATOR.length(),
                    builder.length());
        }
        builder.append(JSONConstants.OBJECT_SUFFIX);
        return builder.toString();
    }

}
