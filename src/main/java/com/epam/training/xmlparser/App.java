package com.epam.training.xmlparser;

import com.epam.training.xmlparser.domain.JSONObject;
import com.epam.training.xmlparser.service.JSONConverter;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class App {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/resources/xml_entity.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            JSONConverter jsonConverter = new JSONConverter();
            JSONObject jsonObject = jsonConverter.parseDocument(doc);

            try (Writer writer = new FileWriter("src/out/converted.json")) {
                writer.write(jsonObject.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
