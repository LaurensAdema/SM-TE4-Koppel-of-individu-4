package com.example.martijn.b0unce.model;

import com.example.martijn.b0unce.model.ball.Ball;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Martijn on 20-4-2016.
 */
public class Account implements Serializable {
    private String name;
    private String country;
    private Ball ball;
    private List<Map> maps;
}
