package hu.kampeni.controller;

import hu.kampeni.model.Messages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    public void handleNewGame(ActionEvent event) {
        System.err.println("--------------");
    }

    public void handleLoadGame(MouseEvent event) {
        System.err.println("x: " + event.getX() + ", y: " + event.getY());
        System.err.println("load game");
    }

    public void handleExitGame(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newGameButton.setText(Messages.NEW_GAME);
        loadGameButton.setText(Messages.LOAD_GAME);
        exitGameButton.setText(Messages.EXIT_GAME);
    }
}
