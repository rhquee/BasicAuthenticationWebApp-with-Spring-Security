package configuration.securityconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); //encoder od springa 5
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("joe").password(encoder.encode("joe")).roles("USER").build());
        inMemoryUserDetailsManager.createUser(User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN").build());
        return inMemoryUserDetailsManager;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                    .anyRequest().authenticated().and()

                .formLogin()
                    .loginPage("/login").permitAll()
                .failureUrl("/login-error")
                    .successHandler(authenticationSuccessHandler()).and()
                .logout().permitAll().invalidateHttpSession(true).deleteCookies("JSESSIONID").and()
                .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint())
//                    .accessDeniedPage("/403");

        .and()
                .httpBasic()
                .and()
                .sessionManagement().maximumSessions(1) //rozpoznawanie po loginie
        .maxSessionsPreventsLogin(false).expiredUrl("/login?logout") //true = niemożliwe jest zalogowanie się drugi raz
        .sessionRegistry(sessionRegistry());
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }
}
