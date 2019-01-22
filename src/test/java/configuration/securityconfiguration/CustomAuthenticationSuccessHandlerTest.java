package configuration.securityconfiguration;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomAuthenticationSuccessHandlerTest {

    private HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
    private HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
    private Authentication authentication = mock(Authentication.class);

    @Test
    public void onAuthenticationSuccess_whenSetContainsAdmin_thenRedirectToAdminPage() throws Exception {
        Set<String> roles = new HashSet();
        roles.add("ROLE_ADMIN");

        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
        customAuthenticationSuccessHandler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        verify(httpServletResponse).sendRedirect("/admin");
    }

    @Test
    public void onAuthenticationSuccess_whenSetContainUser_thenRedirectToIndex() throws Exception {
        Set<String> roles = new HashSet();
        roles.add("ROLE_USER");


        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
        customAuthenticationSuccessHandler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        verify(httpServletResponse).sendRedirect("/index");
    }
}