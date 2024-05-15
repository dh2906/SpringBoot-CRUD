package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;

@Controller
public class IntroduceController {
    @GetMapping("/introduce")
    public String introduce(@RequestParam(value = "name",
            required = false, defaultValue = "이동훈") String userName, Model model) {
        model.addAttribute("name", userName);
        return "introduce";
    }

    @GetMapping("/json")
    @ResponseBody
    public HashMap<String, Object> printJson() {
        HashMap<String, Object> user = new HashMap<>();
        user.put("name", "이동훈");
        user.put("age", 23);
        return user;
    }
}
