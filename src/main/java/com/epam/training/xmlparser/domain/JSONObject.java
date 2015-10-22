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

}
