package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class AccessDeniedController {


        // for 403 access denied page
        @GetMapping(value = "/403")
        public String accesssDenied(Principal user, Model model) {
            if (user != null) {
                model.addAttribute("msg", "Hi " + user.getName()
                        + ", you do not have permission to access this page!");
            } else {
                model.addAttribute("msg",
                        "You do not have permission to access this page!");
            }
            return "403";

        }

}
