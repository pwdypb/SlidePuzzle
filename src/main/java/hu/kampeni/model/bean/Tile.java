package hu.kampeni.model.bean;

/**
 * Represents a tile on the table.
 *
 * @author Benjámin Árva
 * @since 2015.07.28.
 */
public class Tile {
    private Position position;
    private int number;

    public Tile(Position position, int number) {
        this.position = position;
        this.number = number;
    }
}
