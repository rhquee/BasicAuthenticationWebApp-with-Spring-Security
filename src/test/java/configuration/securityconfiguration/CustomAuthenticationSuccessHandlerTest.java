package configuration.securityconfiguration;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Collection;
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
//        Set<GrantedAuthority> roles = new HashSet<>();
//        roles.add(new SimpleGrantedAuthority("ADMIN"));//
//        when(authentication.getAuthorities()).thenReturn(roles);

        Collection<GrantedAuthority> roles = new HashSet<>();
        roles.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_ADMIN";
            }
        });
        when(authentication.getAuthorities()).thenAnswer(
                new Answer<Collection<? extends GrantedAuthority>>() {
            @Override
            public Collection<? extends GrantedAuthority> answer(InvocationOnMock x) throws Throwable {
                return roles;
            }
        });

        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
        customAuthenticationSuccessHandler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        verify(httpServletResponse).sendRedirect("/admin");
    }

    @Test
    public void onAuthenticationSuccess_whenSetContainUser_thenRedirectToIndex() throws Exception {
        Collection<GrantedAuthority> roles = new HashSet<>();
        roles.add((GrantedAuthority)() -> "ROLE_USER");
        when(authentication.getAuthorities()).thenAnswer(x->roles);

        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
        customAuthenticationSuccessHandler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        verify(httpServletResponse).sendRedirect("/index");
    }
}