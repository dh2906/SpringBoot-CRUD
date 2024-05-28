package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    ArticleService articleService;

    PostController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String viewArticles(@RequestParam(
            value = "boardId", required = false) Integer boardId, Model model) {
        if (boardId == null)
            model.addAttribute("items", articleService.getArticlesView());

        else {
            model.addAttribute("items", articleService.getArticlesViewByBoardId(boardId));
            model.addAttribute("boardName", articleService.getBoardName(boardId));
        }

        return "community";
    }
}
