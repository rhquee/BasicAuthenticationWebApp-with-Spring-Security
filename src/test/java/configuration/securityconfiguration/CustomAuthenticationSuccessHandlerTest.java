package configuration.securityconfiguration;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomAuthenticationSuccessHandlerTest {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private Authentication authentication;
//    HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
//    HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
//    Authentication authentication = mock(Authentication.class);

    @Test
    public void onAuthenticationSuccess() throws Exception {
//        Set<> roles = mock(Set.class);
//        when(roles.contains("ROLE_ADMIN")).thenReturn(true);
//
//        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler.class;
//        customAuthenticationSuccessHandler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
//
//        verify(httpServletResponse).sendRedirect("/admin");
    }
}