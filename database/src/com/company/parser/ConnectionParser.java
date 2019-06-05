package com.company.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionParser {
    public static Map<String, String> getConnectionParamFromXml() {
        Map<String, String> params = new HashMap<>();
        String fileName = "dataSource.xml";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    exception.printStackTrace();
                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                    exception.printStackTrace();
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    exception.printStackTrace();
                }
            });
            Document doc = builder.parse(fileName);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("datasources");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String sourceName = eElement.getElementsByTagName("source-name").item(0).getTextContent();
                    params.put("sourceName", sourceName);
                    String url = eElement.getElementsByTagName("connection-url").item(0).getTextContent();
                    params.put("url", url);
                    String driverClass = eElement.getElementsByTagName("driver-class").item(0).getTextContent();
                    params.put("driverClass", driverClass);
                    String userName = eElement.getElementsByTagName("user-name").item(0).getTextContent();
                    params.put("userName", userName);
                    String password = eElement.getElementsByTagName("password").item(0).getTextContent();
                    params.put("password", password);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return params;
    }
}
