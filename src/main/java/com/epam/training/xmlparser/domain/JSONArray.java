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
            builder.append(item)
                    .append(", ");
        }
        if (builder.length() > 2) {
            builder.delete(builder.length() - 2, builder.length() - 1);
        }
        builder.append("]");
        return builder.toString();
    }

}
