package configuration.securityconfiguration;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomAuthenticationSuccessHandlerTest {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private Authentication authentication;
//
//    @Test
//    public void onAuthenticationSuccess() throws Exception {
//        Set<String> expectedParams = new HashSet(Arrays.asList("first", "second"));
//
//        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler.class;
//        customAuthenticationSuccessHandler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
//
//        verify(httpServletResponse).sendRedirect("/admin");
//    }
}