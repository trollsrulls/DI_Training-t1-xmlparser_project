package com.epam.training.xmlparser;

import com.epam.training.xmlparser.domain.JSONObject;
import com.epam.training.xmlparser.service.JSONConverter;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public final class App {

    public static final String RESOURCES_DIR = "src/resources/";
    public static final String OUT_DIR = "src/out/";

    public static void main(String[] args) {
        final String fileName = "xml_entity";
        try {
            File xmlFile = new File(RESOURCES_DIR + fileName + ".xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            JSONConverter jsonConverter = new JSONConverter();
            JSONObject jsonObject = jsonConverter.parseDocument(doc);

            try (Writer writer = new FileWriter(OUT_DIR + fileName + ".json")) {
                writer.write(jsonObject.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private App() { }

}
