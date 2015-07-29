package hu.kampeni.model;

import hu.kampeni.model.bean.Language;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds the messages for the application.
 *
 * @author Benjámin Árva
 * @since 2015.07.28.
 */
public class Messages {
    public static final String NEW_GAME;
    public static final String LOAD_GAME;
    public static final String EXIT_GAME;
    private static Document document;
    private static Map<String, String> messages;
    private static Language language;

    static {
        language = Language.ENGLISH;
        parseXml();
        refreshList();

        NEW_GAME = getValueById("NEW_GAME");
        LOAD_GAME = getValueById("LOAD_GAME");
        EXIT_GAME = getValueById("EXIT_GAME");
    }

    private static void refreshList() {
        if (messages == null) {
            messages = new HashMap<>();
        }
        messages.clear();
        Element element = document.getDocumentElement();
        NodeList nl = element.getElementsByTagName(Constants.XML_MSG);

        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                Element message = (Element) nl.item(i);

                String key = message.getAttribute(Constants.XML_ID);

                String value;
                if (language.equals(Language.ENGLISH)) {
                    value = message.getAttribute(Constants.XML_ENG_VALUE);
                } else {
                    value = message.getAttribute(Constants.XML_HUN_VALUE);
                }

                messages.put(key, value);
            }
        }
    }

    private static String getValueById(String id) {
        return messages.get(id);
    }

    private static void parseXml() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        URL url = Class.class.getResource("/messages/messages.xml");
        File xml;
        try {
            xml = new File(url.toURI());
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(xml);
        } catch (URISyntaxException | ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

    }
}
