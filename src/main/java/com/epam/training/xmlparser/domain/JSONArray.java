package com.epam.training.xmlparser.domain;

import java.util.ArrayList;
import java.util.List;

public class JSONArray implements JSONComposite {

    private List<JSONComposite> items;

    public JSONArray() {
        items = new ArrayList<>();
    }

    public JSONComposite get(int index) {
        return items.get(index);
    }

    public void put(JSONComposite item) {
        items.add(item);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (JSONComposite item : items) {
            builder.append(item);
        }
        builder.append("],");
        return builder.toString();
    }

}
