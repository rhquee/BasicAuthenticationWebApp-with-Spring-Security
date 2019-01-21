package configuration.securityconfiguration;

import org.junit.Test;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CustomAuthenticationEntryPointTest {

    HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
    HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
    AuthenticationException authenticationException = mock(AuthenticationException.class);

    @Test
    public void commence_whenURIisHomeAndUserIsNotNull_thenRedirectToIndex() throws Exception {
        //given
        when(httpServletRequest.getRequestURI()).thenReturn("/");
        when(httpServletRequest.getAttribute("user")).thenReturn("Joe");
        //when
        CustomAuthenticationEntryPoint customAuthenticationEntryPoint = new CustomAuthenticationEntryPoint();
        customAuthenticationEntryPoint.commence(httpServletRequest, httpServletResponse, authenticationException);
        //then
        verify(httpServletResponse).sendRedirect("index");
    }

    @Test
    public void commence_whenURIisHomeAndUserIsNull_thenRedirectToLogin() throws Exception {
        //given
        when(httpServletRequest.getRequestURI()).thenReturn("/");
        when(httpServletRequest.getAttribute("user")).thenReturn(null);
        //when
        CustomAuthenticationEntryPoint customAuthenticationEntryPoint = new CustomAuthenticationEntryPoint();
        customAuthenticationEntryPoint.commence(httpServletRequest, httpServletResponse, authenticationException);
        //then
        verify(httpServletResponse).sendRedirect("login");
    }

    @Test
    public void commence_whenURIisHomeAndUserIsEmpty_thenRedirectToLogin() throws Exception {
        //given
        when(httpServletRequest.getRequestURI()).thenReturn("/");
        when(httpServletRequest.getAttribute("user")).thenReturn("");
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