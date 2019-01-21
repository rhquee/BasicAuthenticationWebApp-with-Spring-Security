package configuration.securityconfiguration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        if (isRequestURIHome(httpServletRequest) && isUserExists(httpServletRequest)) {
            httpServletResponse.sendRedirect("index");
            return;
        }
        if (isRequestURIHome(httpServletRequest) && !isUserExists(httpServletRequest)) {
            httpServletResponse.sendRedirect("login");
            return;
        }else{
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized to see that");
        }
    }

    private boolean isRequestURIHome(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURI().equals("/");
    }

    private boolean isUserExists(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getAttribute("user") != null && httpServletRequest.getAttribute("user") != "";
    }
}
