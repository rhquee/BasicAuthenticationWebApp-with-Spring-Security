package controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class AdminController {

    @GetMapping(value = {"/admin"})
    private String showAdminPage(Model model, HttpServletRequest request){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String login = "unknown";
        if(principal instanceof UserDetails){
            login = ((UserDetails) principal).getUsername();
            System.out.println(login);
        }
        model.addAttribute("user", login);
        return "admin";
    }
}
