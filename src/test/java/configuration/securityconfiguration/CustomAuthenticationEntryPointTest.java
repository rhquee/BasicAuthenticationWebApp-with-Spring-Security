package configuration.securityconfiguration;

import org.junit.Test;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CustomAuthenticationEntryPointTest {

    private HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
    private HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
    private AuthenticationException authenticationException = mock(AuthenticationException.class);

    @Test
    public void commence_whenURIisHome_thenRedirectToLogin() throws Exception {
        //given
        when(httpServletRequest.getRequestURI()).thenReturn("/");
        //when
        CustomAuthenticationEntryPoint customAuthenticationEntryPoint = new CustomAuthenticationEntryPoint();
        customAuthenticationEntryPoint.commence(httpServletRequest, httpServletResponse, authenticationException);
        //then
        verify(httpServletResponse).sendRedirect("login");
    }

    @Test
    public void commence_whenURIisNotHome_thenSendError() throws Exception {
        //given
        when(httpServletRequest.getRequestURI()).thenReturn("/qwer");
        //when
        CustomAuthenticationEntryPoint customAuthenticationEntryPoint = new CustomAuthenticationEntryPoint();
        customAuthenticationEntryPoint.commence(httpServletRequest, httpServletResponse, authenticationException);
        //then
        verify(httpServletResponse).sendError(403, "Unauthorized to see that");
    }
}