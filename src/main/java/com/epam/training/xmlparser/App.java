package com.epam.training.xmlparser;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class App {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/resources/xml_entity.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("staff");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Staff id : " + eElement.getAttribute("id"));
                    System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

                }
            }

            JSONObject jsonObject = XML.toJSONObject("<?xml version='1.0' encoding='utf-8'?>\n" +
                    "<document xmlns='http://www.lotus.com/dxl' version='9.0' maintenanceversion='1.0'\n" +
                    " replicaid='862564870080072B' form='fmDAAuthoritative'>\n" +
                    "\t<noteinfo noteid='2e9fa' unid='B1D133B74B2738056525747C00103DE9' sequence='57'>\n" +
                    "\t\t<created>\n" +
                    "\t\t\t<datetime>20080704T082724,25+0530</datetime>\n" +
                    "\t\t</created>\n" +
                    "\t\t<modified>\n" +
                    "\t\t\t<datetime dst='true'>20150606T080009,62-05</datetime>\n" +
                    "\t\t</modified>\n" +
                    "\t\t<revised>\n" +
                    "\t\t\t<datetime dst='true'>20150603T143333,37-05</datetime>\n" +
                    "\t\t</revised>\n" +
                    "\t\t<lastaccessed>\n" +
                    "\t\t\t<datetime dst='true'>20150606T080009,61-05</datetime>\n" +
                    "\t\t</lastaccessed>\n" +
                    "\t\t<addedtofile>\n" +
                    "\t\t\t<datetime>20091110T185705,50-06</datetime>\n" +
                    "\t\t</addedtofile>\n" +
                    "\t</noteinfo>\n" +
                    "</document>");
            System.out.println(jsonObject.toString(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
