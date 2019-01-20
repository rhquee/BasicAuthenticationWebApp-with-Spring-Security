package configuration.appconfiguration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SpringRootConfig.class, WebSecurityConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringWebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

}
