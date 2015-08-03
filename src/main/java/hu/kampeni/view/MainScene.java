package hu.kampeni.view;

import javafx.scene.Scene;

/**
 * This class describes the scene of the main window.
 * It's using {@link MainLayout}, the fun part is in that class.
 *
 * @author Benjámin Árva
 * @since 2015. 08. 01.
 */
public final class MainScene extends Scene {
    public MainScene() {
        super(new MainLayout());
    }
}
