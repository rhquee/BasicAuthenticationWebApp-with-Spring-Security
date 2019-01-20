package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @GetMapping(value = {"/admin"})
    private String showAdminPage(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        return "admin";
    }
}
