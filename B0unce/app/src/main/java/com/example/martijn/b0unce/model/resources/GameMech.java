package com.example.martijn.b0unce.model.resources;

import com.example.martijn.b0unce.model.MapObject;
import com.example.martijn.b0unce.model.ball.Ball;
import com.example.martijn.b0unce.model.ball.Circle;
import com.example.martijn.b0unce.model.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by laure on 21-4-2016.
 */
public class GameMech {
    public static ArrayList<MapObject> Collision(MapObject mapObject, GamePoint newLoc) {
        ArrayList<MapObject> collision = new ArrayList<>();
        HashMap<GamePoint, MapObject> obstacles = mapObject.getMap().getObstacles();
        for (Map.Entry<GamePoint, MapObject> set : obstacles.entrySet()) {
            if (mapObject instanceof Ball) {
                Ball ball = (Ball) mapObject;
                if (ball.getType() instanceof Circle) {
                    if(intersects(ball, set.getValue()))
                    {
                        collision.add(set.getValue());
                    }
                }
            }
        }
        return collision;
    }

    public static boolean intersects(Ball circle, MapObject rect)
    {
        double circleDistanceX = Math.abs(circle.getPosition().x - rect.getPosition().x);
        double circleDistanceY = Math.abs(circle.getPosition().y - rect.getPosition().y);

        if (circleDistanceX > (1 + circle.RADIUS)) { return false; }
        if (circleDistanceY > (1 + circle.RADIUS)) { return false; }

        if (circleDistanceX <= (1/2)) { return true; }
        if (circleDistanceY <= (1/2)) { return true; }

        double cornerDistance_sq = Math.pow(circleDistanceX - 1/2,2) +
                Math.pow(circleDistanceY - 1/2 , 2);

        return (cornerDistance_sq <= (Math.pow(circle.RADIUS,2)));
    }
}
