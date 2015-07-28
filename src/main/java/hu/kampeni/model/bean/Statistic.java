package hu.kampeni.model.bean;

import java.sql.Time;

/**
 * Represents a row in a statistics table
 *
 * @author Benjámin Árva
 * @since 2015.07.28.
 */
public class Statistic {
    private String userName;
    private Time time;
    private Difficulty difficulty;

    public Statistic(String userName, Time time, Difficulty difficulty) {
        this.userName = userName;
        this.time = time;
        this.difficulty = difficulty;
    }
}
