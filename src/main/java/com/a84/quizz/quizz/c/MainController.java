package com.a84.quizz.quizz.c;

import com.a84.quizz.quizz.m.User;
import com.a84.quizz.quizz.m.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes({"grosminet", "name"})
public class MainController {

    @Autowired
    UserDAO userDAO ;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            @RequestParam(name="name", required = true) String name,
            @RequestParam(name="password", required = true) String password,
            @RequestParam(name="passwordc", required = true) String passwordc,
            Model model) {
// Check if provided passwords are the same
        if (password.equals(passwordc)) {
// Check if the name is already used.
            boolean found = userDAO.existsByName(name);
            if (found) {
                model.addAttribute("error_msg", "Error: pick another name.");
                return "register";
            }
// Now we can add the new user.
            User u = new User();
            u.setName(name);
            String salt = BCrypt.gensalt();
            u.setSalt(salt);
            u.setHashedPassword(BCrypt.hashpw(password, salt));
            u.setAccessLevel(0);
            userDAO.save(u);
            return "index";
        }
        else {
            model.addAttribute("error_msg", "Error: Passwords don't match.");
        }
        return "register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@RequestParam(name="name", required = false)
                                       String name, Model model) {
        if (name != null) {
            model.addAttribute("grosminet", name);
            model.addAttribute("name", name);
        }
        else if (model.getAttribute("grosminet") != null) {
            model.addAttribute("name", model.getAttribute("grosminet"));
        }
        return "register";
    }

    @GetMapping("/index")
    public String index(@RequestParam(name="name", required = false)
                                String name, Model model) {
        if (name != null) {
            model.addAttribute("grosminet", name);
            model.addAttribute("name", name);
        }
        else if (model.getAttribute("grosminet") != null) {
            model.addAttribute("name", model.getAttribute("grosminet"));
        }
        return "index";
    }
}
