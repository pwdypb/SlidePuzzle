package hu.kampeni.controller;

import hu.kampeni.model.MessageHandler;
import hu.kampeni.model.Messages;
import hu.kampeni.model.bean.Language;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the main window of the application.
 *
 * @author Benjámin Árva
 * @since 2015.07.29.
 */
public class MainWindowController implements Initializable {

    @FXML
    private Button newGameButton;

    @FXML
    private Button loadGameButton;

    @FXML
    private Button exitGameButton;

    @FXML
    private ChoiceBox<String> languageSelector;

    private Messages messages;

    public void handleNewGame(ActionEvent event) {
        System.err.println("--------------");
    }

    public void handleLoadGame(MouseEvent event) {
        System.err.println("x: " + event.getX() + ", y: " + event.getY());
        System.err.println("load game");
    }

    public void handleExitGame() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messages = MessageHandler.getMessagesInstance();

        initTexts();
        initLanguageSelector();
    }

    private void initTexts() {
        newGameButton.setText(messages.NEW_GAME);
        loadGameButton.setText(messages.LOAD_GAME);
        exitGameButton.setText(messages.EXIT_GAME);
    }

    private void initLanguageSelector() {
        final String ENGLISH = "English";
        final String HUNGARIAN = "Hungarian";

        languageSelector.setItems(FXCollections.observableArrayList(
                ENGLISH, HUNGARIAN
        ));
        languageSelector.getSelectionModel().selectFirst();
        languageSelector.getSelectionModel()
                .selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            String language = languageSelector.getItems().get((Integer) newValue);
            switch (language) {
                case ENGLISH:
                    MessageHandler.setLanguage(Language.ENGLISH);
                    MessageHandler.refresh();
                    initTexts();
                    break;
                case HUNGARIAN:
                    MessageHandler.setLanguage(Language.HUNGARIAN);
                    MessageHandler.refresh();
                    initTexts();
                    break;
            }
        });
    }
}
