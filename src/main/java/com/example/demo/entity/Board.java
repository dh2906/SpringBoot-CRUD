package com.example.demo.entity;

import com.example.demo.repository.ArticleRepositoryJdbc;

import java.util.ArrayList;

public class Board {
    private Integer id;
    private String name;
    private ArrayList<ArticleRepositoryJdbc> board = new ArrayList<>();
}
