package configuration.appconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@ComponentScan({"service", "repository"})
public class SpringRootConfig {
}
