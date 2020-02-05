package com.a84.quizz.quizz.c;

import com.a84.quizz.quizz.m.User;
import com.a84.quizz.quizz.m.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@SessionAttributes("session_name")
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
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/index")
    public String index(ModelMap model) {
        System.out.println(model.getAttribute("name"));
        model.getAttribute("name");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam(name="name", required = true) String name,
            @RequestParam(name="password", required = true) String password,
            Model model) {
        User u = userDAO.findByName(name);
        if (u != null) {
            if (u.getHashedPassword().endsWith(BCrypt.hashpw(password, u.getSalt()))) {
                // Password match -> log the user in
                model.addAttribute("logged_in", true);
                model.addAttribute("name", name);

                model.addAttribute("session_name", name);
                model.addAttribute("name", name);
                System.out.println(model.getAttribute("name"));
                model.addAttribute("user", u.toString());
                return "index";
            }
        } else {
            model.addAttribute("error_msg", "Error: name-login doesn't exist.");
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout";
    }
}
