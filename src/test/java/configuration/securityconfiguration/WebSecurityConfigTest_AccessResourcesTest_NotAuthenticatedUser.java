package configuration.securityconfiguration;

import configuration.SpringRootConfig;
import configuration.SpringWebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebConfig.class, SpringRootConfig.class, WebSecurityConfig.class})
public class WebSecurityConfigTest_AccessResourcesTest_NotAuthenticatedUser {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Filter springSecurityFilterChain;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void whenAccessPublicResource_thenOk() throws Exception {
        mockMvc.perform(get("/css/web.css"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenHome_ThenRedirect() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("login"));
    }

    @Test
    public void whenLoginPage_ThenStatusOK() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenAdmin_thenStatus403() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().is(403));
    }
}
