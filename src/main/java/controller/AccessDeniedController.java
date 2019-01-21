package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class AccessDeniedController {

        @GetMapping(value = "/403")
        public String accesssDenied() {
            return "403";

        }

}
