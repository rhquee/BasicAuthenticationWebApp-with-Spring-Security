package configuration.securityconfiguration;

import configuration.SpringRootConfig;
import configuration.SpringWebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebConfig.class, SpringRootConfig.class, WebSecurityConfig.class})
public class WebSecurityConfigTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private UserDetailsService userDetailsService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void configure_whenAccessPublicResource_thenOk() throws Exception {
        mockMvc.perform(get("/css/web.css"))
                .andExpect(status().isOk());
    }

    @Test
    public void configure_whenNotAuthenticated_thenStatus403() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().is(403));
    }

    @Test
    @WithMockUser
    public void configure_whenRoleUser_ThenStatus403() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().is(403));
    }

    @Test
    @WithMockUser(roles="ADMIN")
    public void configure_whenRoleAdmin_ThenStatusOK() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }

    @Test
    public void configure_whenNotAuthenticated_ThenRedirect() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("login"));

    }

    @Test
    @WithMockUser
    public void configure_whenAuthenticatedToIndex_ThenStatusOK() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void configure_whenAuthenticatedToOtherPage_ThenStatus403() throws Exception {
        mockMvc.perform(get("/qwe"))
                .andExpect(status().is(403))
                .andExpect(redirectedUrl(null));
    }

    @Test
    public void configure_whenLoginPage_ThenStatusOK() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void configure_whenLoginPage_ThenRedirectToIndex() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().is(302))
        .andExpect(redirectedUrl("/index"));
    }

    @Test
    public void configure_whenLoginWithValidUser_thenAuthenticationSuccess() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("joe");
        mockMvc
                .perform(get("/").with(user(userDetails)))
                .andExpect(status().isOk())
                .andExpect(authenticated().withAuthenticationPrincipal(userDetails));

    }

    @Test //expected UsernameNotFoundException?
    public void configure_whenLoginWithValidUser_thenAuthenticationFailed() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("invalid");
        mockMvc
                .perform(formLogin().user(userDetails.getUsername()))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("login-error"))
                .andExpect(unauthenticated());
    }

    @Test
    public void configure_whenLogout_ThenStatusOKey() throws Exception {
        mockMvc.perform(post("/logout"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("login?logout"))
                .andExpect(cookie().doesNotExist("JSESSIONID"))
                .andExpect(unauthenticated());
    }
}