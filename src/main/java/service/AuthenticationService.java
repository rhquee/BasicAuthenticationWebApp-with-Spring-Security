//package service;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class AuthenticationService {
//
//
//    public boolean isAuthenticated(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//        String login;
//        if (principal instanceof UserDetails) {
//            login = ((UserDetails) principal).getUsername();
//    }
//}
