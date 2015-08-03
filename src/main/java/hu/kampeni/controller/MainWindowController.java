package hu.kampeni.controller;

import hu.kampeni.components.CustomButton;
import hu.kampeni.model.Messages;
import hu.kampeni.model.bean.Language;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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
@Deprecated
public class MainWindowController implements Initializable {
//TODO: Ennek az osztálynak baszni kell valamit
    private CustomButton newGame;
    private CustomButton loadGame;
    private CustomButton statistics;
    private CustomButton exitGame;
    private ChoiceBox<String> languageSelector;

    private Messages messages;

    private void handleNewGame(ActionEvent event) {
        System.err.println("--------------");
    }

    private void handleLoadGame(MouseEvent event) {
        System.err.println("x: " + event.getX() + ", y: " + event.getY());
        System.err.println("load game");
    }

    private void handleExitGame() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messages = MessageHandler.getMessagesInstance();

        initButtons();
        initLanguageSelector();
    }

    private void initButtons() {
        newGame = new CustomButton(messages.NEW_GAME);
        loadGame = new CustomButton(messages.LOAD_GAME);
        statistics = new CustomButton(messages.STATISTICS);
        exitGame = new CustomButton(messages.EXIT_GAME);

        newGame.setOnAction(this::handleNewGame);
        loadGame.setOnMouseClicked(this::handleLoadGame);
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
                    initButtons();
                    break;
                case HUNGARIAN:
                    MessageHandler.setLanguage(Language.HUNGARIAN);
                    MessageHandler.refresh();
                    initButtons();
                    break;
            }
        });
    }
}
