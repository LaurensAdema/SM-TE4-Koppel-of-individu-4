package com.example.martijn.b0unce.model;

import com.example.martijn.b0unce.model.resources.GamePoint;

import java.io.Serializable;

/**
 * Created by laure on 21-4-2016.
 */
public class MapObject implements Serializable {
    protected Map map;
    protected GamePoint position;

    public MapObject(GamePoint position) {
        this.position = position;
    }

    public Map getMap() {
        return map;
    }

    public GamePoint getPosition() {
        return position;
    }
}
