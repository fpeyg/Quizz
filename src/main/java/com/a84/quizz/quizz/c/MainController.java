package com.a84.quizz.quizz.c;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("grosminet")
public class MainController {

    @RequestMapping(value = "/login")
    public String login(@RequestParam(name="name", required = false)
                                String name, Model model) {
        if (name != null) {
            model.addAttribute("grosminet", name);
            model.addAttribute("name", name);
        }
        else if (model.getAttribute("grosminet") != null) {
            model.addAttribute("name", model.getAttribute("grosminet"));
        }
        return "login";
    }
}
