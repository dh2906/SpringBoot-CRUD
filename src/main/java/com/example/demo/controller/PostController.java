package com.example.demo.controller;

import com.example.demo.service.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    Service service;

    PostController(Service service) {
        this.service = service;
    }

    @GetMapping("/posts")
    public String viewArticles(@RequestParam(
            value = "boardId", required = false) Integer boardId, Model model) {
        if (boardId == null)
            model.addAttribute("items", service.getAllArticleToView());

        else {
            model.addAttribute("items", service.getBoardAllArticleToView(boardId));
            model.addAttribute("boardName", service.getBoardName(boardId));
        }

        return "community";
    }
}
