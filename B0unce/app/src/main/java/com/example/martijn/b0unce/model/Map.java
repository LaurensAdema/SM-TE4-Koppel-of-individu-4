package com.example.martijn.b0unce.model;

import android.graphics.Point;

import com.example.martijn.b0unce.model.ball.Ball;
import com.example.martijn.b0unce.model.ball.Circle;
import com.example.martijn.b0unce.model.obstacles.Accelerator;
import com.example.martijn.b0unce.model.obstacles.Gyroscope;
import com.example.martijn.b0unce.model.obstacles.Obstacle;
import com.example.martijn.b0unce.model.obstacles.Ramp;
import com.example.martijn.b0unce.model.obstacles.ReSwipe;
import com.example.martijn.b0unce.model.obstacles.SpeedDown;
import com.example.martijn.b0unce.model.obstacles.SpeedUp;
import com.example.martijn.b0unce.model.obstacles.Wall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Martijn on 20-4-2016.
 */
public class Map {
    private HashMap<Point, MapObject> obstacles;
    private int level;
    private String name;
    private Account author;
    private HashMap<Account, Integer> highscores;
    private int maxBounces;
    private boolean swipe;

    public Map(Account player, int level, String name, HashMap<Point, MapObject> map) {
        swipe = true;
        highscores = new HashMap<>();
        this.level = level;
        this.name = name;
        this.author = player;
        this.obstacles = map;
    }

    public static HashMap<Point, MapObject> generateMap(File file) {
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

        HashMap<Point, MapObject> map = new HashMap<>();

        int y = 10 - 1;
        int x = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                x = 0;
                for (char b : line.toCharArray()) {

                    switch (b) {
                        case '0':
                            break;
                        case 'W':
                            map.put(new Point(x,y), new Wall());
                            break;
                        case 'R':
                            map.put(new Point(x,y), new ReSwipe());
                            break;
                        case '-':
                            map.put(new Point(x,y), new SpeedDown());
                            break;
                        case '+':
                            map.put(new Point(x,y), new SpeedUp());
                            break;
                        case 'G':
                            map.put(new Point(x,y), new Gyroscope());
                            break;
                        case 'A':
                            map.put(new Point(x,y), new Accelerator());
                            break;
                        case 'D':
                            map.put(new Point(x,y), new Ramp());
                            break;
                        case 'O':
                            map.put(new Point(x,y), new Ball(new Point(x,y), 1, new Circle()));
                            break;
                    }

                    x++;
                }

                y--;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
