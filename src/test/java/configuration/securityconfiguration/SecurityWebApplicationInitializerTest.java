package configuration.securityconfiguration;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrak on 22.01.2019.
 */
public class SecurityWebApplicationInitializerTest {
    @Test
    public void enableHttpSessionEventPublisher_whenCallMethod_thenReturnTrue() throws Exception {
        SecurityWebApplicationInitializer securityWebApplicationInitializer = new SecurityWebApplicationInitializer();
        Assert.assertTrue(securityWebApplicationInitializer.enableHttpSessionEventPublisher());
    }}