package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

//    @Autowired
    //UserService userService;

    @GetMapping(value = "/login")
    public String showLoginPage(Model model, HttpServletRequest request) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//        String login;
//        if (principal instanceof UserDetails) {
//            login = ((UserDetails) principal).getUsername();
//            model.addAttribute("user", login);
//            return "index";
//        }
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/login-expired")
    public String loginExpired(Model model) {
        model.addAttribute("sessionExpired", true);
        return "login";
    }
}
