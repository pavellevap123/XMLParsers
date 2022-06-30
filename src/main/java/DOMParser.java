import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class DOMParser {
    private static final String XML_PATH = "src/main/resources/journal.xml";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(XML_PATH));
        document.getDocumentElement().normalize();

        NodeList nList = document.getElementsByTagName("journal");
        Node node = nList.item(0);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;
            System.out.println("Title : " + eElement.getElementsByTagName("title").item(0).getTextContent());
            System.out.println("Address : " + eElement.getElementsByTagName("address").item(0).getTextContent());
            System.out.println("Telephone : " + eElement.getElementsByTagName("tel").item(0).getTextContent());
            System.out.println("Email : " + eElement.getElementsByTagName("email").item(0).getTextContent());
            System.out.println("URL : " + eElement.getElementsByTagName("url").item(0).getTextContent());

            NodeList articlesList = document.getElementsByTagName("article");
            for (int i = 0; i < articlesList.getLength(); i++) {
                Node articleNode = articlesList.item(i);
                if (articleNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element articlesElement = (Element) articleNode;
                    System.out.println("Article id : " + articlesElement
                            .getAttribute("ID"));
                    System.out.println("Title : " + articlesElement
                            .getElementsByTagName("title")
                            .item(0).getTextContent());
                    System.out.println("Author : " + articlesElement
                            .getElementsByTagName("author")
                            .item(0)
                            .getTextContent());
                    System.out.println("URL : " + articlesElement
                            .getElementsByTagName("url")
                            .item(0)
                            .getTextContent());
                    NodeList hotkeysList = document.getElementsByTagName("hotkeys");
                    for (int j = 0; j < hotkeysList.getLength(); j++) {
                        Node hotkeyNode = hotkeysList.item(j);
                        if (hotkeyNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element hotkeyElement = (Element) hotkeyNode;
                            System.out.println("Hotkey : " + hotkeyElement
                                    .getElementsByTagName("hotkey")
                                    .item(0)
                                    .getTextContent());
                        }
                    }
                }
            }
        }
    }
}
