package com.example.martijn.b0unce.model;

import android.graphics.Point;

import com.example.martijn.b0unce.model.ball.Ball;
import com.example.martijn.b0unce.model.obstacles.Obstacle;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Martijn on 20-4-2016.
 */
public class Map {
    private HashMap<Point, Obstacle> obstacles;
    private int level;
    private String name;
    private Account author;
    private HashMap<Account, Integer> highscores;
    private int maxBounces;
    private boolean swipe;

    public Map(Account player, int level, String name, HashMap<Point, Obstacle> map) {
        swipe = true;
        highscores = new HashMap<>();
        this.level = level;
        this.name = name;
        this.author = player;
        this.obstacles = map;
    }

    public static HashMap<Point, Obstacle> generateMap(File file) {
        /**
         * Obstacle overview:
         * - wall           : W
         * - reswipe        : R
         * - speedDown      : -
         * - speedUp        : +
         * - Gyroscoop      : G
         * - Accelerometer  : A
         * - Ramp:          : D
         * - Ball:          : O
         */
        return null;
    }
}
