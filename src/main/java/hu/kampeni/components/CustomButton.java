package hu.kampeni.components;

import javafx.scene.control.Button;

/**
 * Custom button for this application, with predefined properties, like size, font, etc.
 *
 * @author Benjámin Árva
 * @since 2015. 08. 01.
 */
public class CustomButton extends Button {

    public CustomButton(String text) {
        setMinSize(250, 60);
        setPrefSize(250, 60);
        getStyleClass().add("custom-button");
        setText(text);
    }

}
