package hu.kampeni.controller;

import hu.kampeni.model.Constants;
import hu.kampeni.model.Messages;
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
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This class handles the xml parsing and the language change.
 * You should instantiate Messages class via this.
 *
 * @author Benjámin Árva
 * @since 2015.07.28.
 */
public final class MessageHandler {
    private static Messages messages;
    private static Document document;
    private static Map<String, String> xmlStringList;
    private static Language language;

    static {
        language = Language.ENGLISH;
        messages = new Messages();
        refresh();
    }

    /**
     * Prevents instantiation of the class.
     */
    private MessageHandler() {
    }

    /**
     * This method refreshes the strings in the messages class.
     * Should only use it after language change.
     */
    public static void refresh() {
        parseXml();
        refreshList();
        initFields();
    }

    private static void initFields() {
        for (Field field : messages.getClass().getDeclaredFields()) {
            try {
                field.set(messages, getValueById(field.getName()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void refreshList() {
        if (xmlStringList == null) {
            xmlStringList = new HashMap<>();
        }
        xmlStringList.clear();
        Element element = document.getDocumentElement();
        NodeList nl = element.getElementsByTagName(Constants.XML_MSG);

        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                Element message = (Element) nl.item(i);

                String key = message.getAttribute(Constants.XML_ID);
                String value = message.getAttribute(Constants.XML_VALUE);

                xmlStringList.put(key, value);
            }
        }
    }

    private static String getValueById(String id) {
        return xmlStringList.get(id);
    }

    private static void parseXml() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String fileName = "";

        if (language.equals(Language.ENGLISH)) {
            fileName = "en-EN.xml";
        } else {
            fileName = "hu-HU.xml";
        }

        URL url = Class.class.getResource("/messages/" + fileName);
        File xml;
        try {
            xml = new File(url.toURI());
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(xml);
        } catch (URISyntaxException | ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a messages class, which holds the strings for the application.
     *
     * @return {@link Messages}
     */
    public static Messages getMessagesInstance() {
        return messages;
    }

    public static void setLanguage(Language language) {
        MessageHandler.language = language;
    }
}
