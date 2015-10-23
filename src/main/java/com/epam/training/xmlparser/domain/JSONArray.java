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
        builder.append(JSONConstants.ARRAY_PREFIX);
        for (JSONComposite item : items) {
            builder.append(item)
                    .append(JSONConstants.ITEM_SEPARATOR);
        }
        if (builder.length() > JSONConstants.ITEM_SEPARATOR.length()) {
            builder.delete(builder.length() - JSONConstants.ITEM_SEPARATOR.length(),
                    builder.length());
        }
        builder.append(JSONConstants.ARRAY_SUFFIX);
        return builder.toString();
    }

}
