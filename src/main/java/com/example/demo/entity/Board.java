package com.example.demo.entity;

import com.example.demo.repository.Articles;

import java.util.ArrayList;

public class Board {
    private Integer id;
    private String name;
    private ArrayList<Articles> board = new ArrayList<>();
}
