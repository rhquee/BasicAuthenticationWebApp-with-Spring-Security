package configuration.securityconfiguration;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CustomAuthenticationSuccessHandlerTest {

    HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
    HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
    Authentication authentication = mock(Authentication.class);

    @Test
    public void onAuthenticationSuccess() throws Exception {

    }

}