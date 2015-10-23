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
        parseNode(doc, root);
        return root;
    }

    private void putAttributesToJsonObject(Node node, JSONObject jsonObject) {
        if (node instanceof Element) {
            NamedNodeMap attrs = node.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                String key = attrs.item(i).getNodeName();
                JSONValue value = new JSONValue(attrs.item(i).getNodeValue());
                jsonObject.put(key, value);
            }
        }
    }

    private List<Node> extractChildNodes(Node node) {
        NodeList children = node.getChildNodes();
        List<Node> childNodes = new ArrayList<>();
        for (int i = 0; i < children.getLength(); i++) {
            childNodes.add(children.item(i));
        }
        return childNodes;
    }

    private void fillJsonObjectLevel(List<Node> childNodes, JSONObject jsonObject) {
        for (int i = 0; i < childNodes.size(); i++) {
            int start = i;
            for (int j = i + 1; j < childNodes.size(); j++) {
                if (!childNodes.get(i).getNodeName()
                        .equals(childNodes.get(j).getNodeName())) {
                    i = j - 1;
                    break;
                }
            }
            int end = i;

            Node currentChild = childNodes.get(start);

            if (JSONValue.TEXT_KEY.equals(currentChild.getNodeName())) {
                if (end - start == 0 && StringUtils.isNotBlank(currentChild.getNodeValue())) {
                    JSONValue value = new JSONValue(currentChild.getNodeValue());
                    jsonObject.put(JSONValue.TEXT_KEY, value);
                }
            } else {
                if (end - start == 0) {
                    JSONObject childJsonObject = new JSONObject();
                    jsonObject.put(currentChild.getNodeName(), childJsonObject);
                    parseNode(currentChild, childJsonObject);
                } else {
                    JSONArray childJsonArray = new JSONArray();
                    jsonObject.put(currentChild.getNodeName(), childJsonArray);
                    for (int j = start; j <= end; j++) {
                        JSONObject arrayObject = new JSONObject();
                        childJsonArray.put(arrayObject);
                        parseNode(childNodes.get(j), arrayObject);
                    }
                }
            }
        }
    }

    private void parseNode(Node node, JSONObject jsonObject) {
        putAttributesToJsonObject(node, jsonObject);
        List<Node> childNodes = extractChildNodes(node);
        Collections.sort(childNodes, (o1, o2) -> o1.getNodeName().compareTo(o2.getNodeName()));
        fillJsonObjectLevel(childNodes, jsonObject);
    }

}
