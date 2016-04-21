package com.example.martijn.b0unce.model;

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
import com.example.martijn.b0unce.model.resources.GamePoint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Martijn on 20-4-2016.
 */
public class Map implements Serializable {
    private HashMap<GamePoint, MapObject> obstacles;
    private int level;
    private String name;
    private Account author;
    private HashMap<Account, Integer> highscores;
    private int maxBounces;
    private boolean swipe;

    private Ball ball;

    public static int GRIDSIZE = 10;

    public Map(Account player, int level, int maxBounces, String name, HashMap<GamePoint, MapObject> map, Ball ball) {
        swipe = true;
        highscores = new HashMap<>();
        this.level = level;
        this.name = name;
        this.author = player;
        this.obstacles = map;
        this.ball = ball;
        this.maxBounces = maxBounces;
    }

    public MapObject getObstacle(int x, int y) {
        return null;
    }

    public boolean tick() {
        // TODO update
        if(!swipe)
        {
            ball.Move();
        }
        return true;
    }

    public int getMaxBounces() {
        return maxBounces;
    }

    public boolean Swipe(int angle) {
        if(!swipe) {
            //TODO: Move ball
            swipe = true;
            ball.setDirection(angle);
            return true;
        }
        return false;
    }

    public Ball getBall()
    {
        return ball;
    }


    public static HashMap<GamePoint, MapObject> generateMap(File file) {
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

        HashMap<GamePoint, MapObject> map = new HashMap<>();

        int y = GRIDSIZE - 1;
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
                            map.put(new GamePoint(x,y), new Wall());
                            break;
                        case 'R':
                            map.put(new GamePoint(x,y), new ReSwipe());
                            break;
                        case '-':
                            map.put(new GamePoint(x,y), new SpeedDown());
                            break;
                        case '+':
                            map.put(new GamePoint(x,y), new SpeedUp());
                            break;
                        case 'G':
                            map.put(new GamePoint(x,y), new Gyroscope());
                            break;
                        case 'A':
                            map.put(new GamePoint(x,y), new Accelerator());
                            break;
                        case 'D':
                            map.put(new GamePoint(x,y), new Ramp());
                            break;
                        case 'O':
                            map.put(new GamePoint(x,y), new Ball(new GamePoint(x,y), 1, new Circle()));
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

    public void setSwipe(boolean swipe) {
        this.swipe = swipe;
    }

    public HashMap<GamePoint, MapObject> getObstacles() {
        return obstacles;
    }
}
