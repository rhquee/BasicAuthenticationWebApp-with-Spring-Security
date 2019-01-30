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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebConfig.class, SpringRootConfig.class, WebSecurityConfig.class})
public class WebSecurityConfigTest_AccessResourcesTest_AuthenticatedUserRoleUser {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    UserDetailsService userDetailsService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void whenAdmin_thenStatus403() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("joe");
        mockMvc.perform(get("/admin"))
                .andExpect(status().is(403));
    }

    @Test
    public void whenLogin_ThenStatusOK() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("joe");
        mockMvc
                .perform(get("/").with(user(userDetails)))
                .andExpect(status().isOk())
                .andExpect(authenticated().withAuthenticationPrincipal(userDetails));
    }

    @Test
    public void whenOtherPage_ThenStatus404() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("joe");
        mockMvc
                .perform(get("/qwef").with(user(userDetails)))
                .andExpect(status().is(404))
                .andExpect(redirectedUrl(null))
                .andExpect(authenticated().withAuthenticationPrincipal(userDetails));
    }


    @Test
    public void whenHome_thenAuthenticationSuccess() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("joe");
        mockMvc
                .perform(get("/").with(user(userDetails)))
                .andExpect(status().isOk())
                .andExpect(authenticated().withAuthenticationPrincipal(userDetails));

    }


}
