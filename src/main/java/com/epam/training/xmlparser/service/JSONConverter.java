package com.epam.training.xmlparser.service;

import com.epam.training.xmlparser.domain.JSONArray;
import com.epam.training.xmlparser.domain.JSONObject;
import com.epam.training.xmlparser.domain.JSONValue;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONConverter {

    public JSONObject parseDocument(Document doc) {
        JSONObject root = new JSONObject();
        visitNode(doc, root);
        return root;
    }

    private void visitNode(Node node, JSONObject jsonObject) {

        if (node instanceof Element) {
            NamedNodeMap attrs = node.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                String key = attrs.item(i).getNodeName();
                JSONValue value = new JSONValue(attrs.item(i).getNodeValue());
                jsonObject.put(key, value);
            }
        }

        NodeList children = node.getChildNodes();
        List<Node> childNodes = new ArrayList<>();
        for (int i = 0; i < children.getLength(); i++) {
            childNodes.add(children.item(i));
        }
        Collections.sort(childNodes, (o1, o2) -> o1.getNodeName().compareTo(o2.getNodeName()));

        for (int i = 0; i < childNodes.size(); i++) {
            int start = i;
            for (int j = i + 1; j < childNodes.size(); j++) {
                if (childNodes.get(i).getNodeName().equals(childNodes.get(j).getNodeName())) {
                    i = j;
                }
            }
            int end = i;

            if (end - start == 0) {
                Node currentChild = childNodes.get(end);

                if (JSONValue.TEXT_KEY.equals(currentChild.getNodeName())
                        && StringUtils.isNotBlank(currentChild.getNodeValue())) {
                    JSONValue value = new JSONValue(currentChild.getNodeValue());
                    jsonObject.put(JSONValue.TEXT_KEY, value);
                    continue;
                }
                JSONObject childJsonObject = new JSONObject();
                jsonObject.put(currentChild.getNodeName(), childJsonObject);
                visitNode(currentChild, childJsonObject);
            } else {
                if (JSONValue.TEXT_KEY.equals(childNodes.get(start).getNodeName())) {
                    continue;
                }
                JSONArray childJsonArray = new JSONArray();
                jsonObject.put(childNodes.get(start).getNodeName(), childJsonArray);
                for (int j = start; j <= end; j++) {
                    JSONObject arrayObject = new JSONObject();
                    childJsonArray.put(arrayObject);
                    visitNode(childNodes.get(j), arrayObject);
                }
            }
        }
    }

}
