package hu.kampeni.view;

import hu.kampeni.components.CustomButton;
import hu.kampeni.controller.MessageHandler;
import hu.kampeni.model.Messages;
import hu.kampeni.model.bean.Language;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

/**
 * This class describes the layout of the main window.
 *
 * @author Benjámin Árva
 * @since 2015. 08. 01.
 */
public class MainLayout extends FlowPane {
    private CustomButton newGame;
    private CustomButton loadGame;
    private CustomButton statistics;
    private CustomButton exitGame;
    private ChoiceBox<String> languageSelector;

    private Messages messages;

    public MainLayout() {
        super();
        messages = MessageHandler.getMessagesInstance();
        initLanguageSelector();
        initButtons();
        setAlignment(Pos.CENTER);
        setVgap(20);
        setHgap(2000);

        getChildren().addAll(newGame, loadGame, statistics, exitGame, languageSelector);
    }

    private void initButtons() {
        newGame = new CustomButton(messages.NEW_GAME);
        loadGame = new CustomButton(messages.LOAD_GAME);
        statistics = new CustomButton(messages.STATISTICS);
        exitGame = new CustomButton(messages.EXIT_GAME);

        exitGame.setOnMouseClicked(this::handleExitGame);
    }

    private void refreshButtons() {
        newGame.setText(messages.NEW_GAME);
        loadGame.setText(messages.LOAD_GAME);
        statistics.setText(messages.STATISTICS);
        exitGame.setText(messages.EXIT_GAME);
    }

    private void handleExitGame(MouseEvent e) {
        System.exit(0);
    }

    //FIXME: combobox value change
    private void initLanguageSelector() {
        final String eng = messages.ENGLISH;
        final String hun = messages.HUNGARIAN;

        languageSelector = new ChoiceBox<>();
        languageSelector.setItems(FXCollections.observableArrayList(
                messages.ENGLISH, messages.HUNGARIAN
        ));
        languageSelector.getSelectionModel().selectFirst();
        languageSelector.getSelectionModel()
                .selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            String language = languageSelector.getItems().get((Integer) newValue);

            if (language.equals(eng)) {
                MessageHandler.setLanguage(Language.ENGLISH);
            } else if (language.equals(hun)) {
                MessageHandler.setLanguage(Language.HUNGARIAN);
            }

            MessageHandler.refresh();
            refreshButtons();
        });
    }
}
