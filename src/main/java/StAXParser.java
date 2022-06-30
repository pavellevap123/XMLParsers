import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StAXParser {

    private static final String XML_PATH = "src/main/resources/journal.xml";

    public static void main(String[] args) {
        try {
            printXmlByXmlCursorReader(Paths.get(XML_PATH));
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private static void printXmlByXmlCursorReader(Path path)
            throws FileNotFoundException, XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(
                new FileInputStream(path.toFile()));

        int eventType = reader.getEventType();

        while (reader.hasNext()) {
            eventType = reader.next();
            if (eventType == XMLEvent.START_ELEMENT) {

                switch (reader.getName().getLocalPart()) {
                    case "title" -> {
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            System.out.printf("Title : %s%n", reader.getText());
                        }
                    }
                    case "address" -> {
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            System.out.printf("Address : %s%n", reader.getText());
                        }
                    }
                    case "tel" -> {
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            System.out.printf("Telephone : %s%n", reader.getText());
                        }
                    }
                    case "email" -> {
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            System.out.printf("Email : %s%n", reader.getText());
                        }
                    }
                    case "url" -> {
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            System.out.printf("URL : %s%n", reader.getText());
                        }
                    }
                    case "article" -> {
                        String id = reader.getAttributeValue(null, "ID");
                        System.out.printf("Article ID : %s%n", id);
                    }
                    case "author" -> {
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            System.out.printf("Author : %s%n", reader.getText());
                        }
                    }
                    case "hotkey" -> {
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            System.out.printf("Hotkey : %s%n", reader.getText());
                        }
                    }
                }
            }
        }
    }
}
