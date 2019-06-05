package parser;

import group.Student;
import group.Subject;
import org.w3c.dom.*;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<Student> readFromXmlFile(String fileName) {
        List<Student> studentList = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void error(SAXParseException exception) throws SAXException {
                    exception.printStackTrace();
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    exception.printStackTrace();
                }

                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    exception.printStackTrace();
                }
            });

            Document doc = builder.parse(fileName);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("student");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String firstName = eElement.getAttribute("firstname");
                    String lastName = eElement.getAttribute("lastname");
                    String groupNumber = eElement.getAttribute("groupnumber");

                    Student student = new Student();
                    student.setFirsName(firstName);
                    student.setLastName(lastName);
                    student.setGroupNumber(groupNumber);

                    NodeList sList = eElement.getElementsByTagName("subject");
                    for (int i = 0; i < sList.getLength(); i++) {
                        Node sNode = sList.item(i);
                        NamedNodeMap attributes = sNode.getAttributes();
                        Node attrTitle = attributes.getNamedItem("title");
                        Node attrMark = attributes.getNamedItem("mark");
                        String title = attrTitle.getTextContent();
                        int mark = Integer.parseInt(attrMark.getTextContent());
                        student.getSubjects().add(new Subject(title, mark));
                    }

                    double average = Double.parseDouble(eElement.getElementsByTagName("average").item(0).getTextContent());
                    student.setAverage(average);
                    studentList.add(student);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    public static void writeToXmlFile(List<Student> list, String fileName) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("group");
            doc.appendChild(rootElement);

            for (Student studentItem : list) {
                Element student = doc.createElement("student");
                rootElement.appendChild(student);

                Attr attrFirstName = doc.createAttribute("firstname");
                attrFirstName.setValue(studentItem.getFirsName());
                student.setAttributeNode(attrFirstName);

                Attr attrLastName = doc.createAttribute("lastname");
                attrLastName.setValue(studentItem.getLastName());
                student.setAttributeNode(attrLastName);

                Attr attrGroupNumber = doc.createAttribute("groupnumber");
                attrGroupNumber.setValue(studentItem.getGroupNumber());
                student.setAttributeNode(attrGroupNumber);

                for (Subject subject : studentItem.getSubjects()) {
                    Element subjElem = doc.createElement("subject");

                    Attr attrMark = doc.createAttribute("mark");
                    attrMark.setValue(String.valueOf(subject.getMark()));
                    subjElem.setAttributeNode(attrMark);

                    Attr attrTitle = doc.createAttribute("title");
                    attrTitle.setValue(subject.getTitle());
                    subjElem.setAttributeNode(attrTitle);

                    student.appendChild(subjElem);
                }

                Element average = doc.createElement("average");
                average.appendChild(doc.createTextNode(String.valueOf(studentItem.getAverage())));
                student.appendChild(average);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "group.dtd");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);

            System.out.println("\nNew XML-file saved!");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
