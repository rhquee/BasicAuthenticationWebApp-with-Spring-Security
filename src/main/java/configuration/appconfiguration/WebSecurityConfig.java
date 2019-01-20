package configuration.appconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //do sterowania springiem security


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); //encoder od springa 5
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("Joe").password(encoder.encode("123")).roles("USER").build());
        inMemoryUserDetailsManager.createUser(User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN").build());
        return inMemoryUserDetailsManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ((HttpSecurity)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http
                .authorizeRequests().anyRequest().hasAnyRole()
                .antMatchers("/css/*").permitAll()
                    .anyRequest())
                    .authenticated()
                .and())
                .formLogin()
                .and())
                .httpBasic();
    }
}
