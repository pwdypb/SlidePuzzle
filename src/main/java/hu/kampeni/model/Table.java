package hu.kampeni.model;

import hu.kampeni.model.bean.Position;
import hu.kampeni.model.bean.Tile;

/**
 * It represents a table.
 *
 * @author Benjámin Árva
 * @since 2015.07.28.
 */
public class Table {
    private Tile[][] tiles;
    private int width;
    private int height;

    //TODO: image

    public Table(int width, int height) {
        this.width = width;
        this.height = height;
        initTiles();
    }

    private void initTiles() {
        tiles = new Tile[height][width];
        int count = 1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Position p = new Position(j, i);
                tiles[i][j] = new Tile(p, count++);
            }
        }
    }
}
