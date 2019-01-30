package configuration.securityconfiguration;

import configuration.SpringRootConfig;
import configuration.SpringWebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebConfig.class, SpringRootConfig.class, WebSecurityConfig.class})
public class WebSecurityConfigTest_ActionsTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private Filter springSecurityFilterChain;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }


    @Test
    public void whenLoginWithValidUser_thenAuthenticationFailed() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("joe");
        mockMvc
                .perform(formLogin("/login").user(userDetails.getUsername()))
                .andExpect(status().isOk())
                .andExpect(authenticated().withAuthenticationPrincipal(userDetails));
    }

    @Test (expected = UsernameNotFoundException.class)
    public void whenLoginWithInvalidUser_thenAuthenticationFailed() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("invalid");
        mockMvc
                .perform(formLogin("/login").user(userDetails.getUsername()))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("login-error"))
                .andExpect(unauthenticated());
    }

    @Test
    public void whenLogout_ThenRedirectAndUnauthenticatedAndRemoveSession() throws Exception {
        mockMvc.perform(logout())
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("login?logout"))
                .andExpect(cookie().doesNotExist("JSESSIONID"))
                .andExpect(unauthenticated());
    }
}
