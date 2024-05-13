package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Object> printJson() {
        Map<String, Object> user = new HashMap<String, Object>();
        user.put("name", "이동훈");
        user.put("age", 23);
        return user;
    }
}
