package net.kk17.spring.config;
 
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
 
/**
 * {@link http://stackoverflow.com/questions/18241791/spring-environment-specific-configuration} 
 * @author t777
 * @author kk17
 */
public class EnvironmentPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final String ENVIRONMENT_NAME = "env.mode";

    private String environment;

    public EnvironmentPropertyPlaceholderConfigurer() {
        super();
        String env = resolveSystemProperty(ENVIRONMENT_NAME);
        if (env != null && env.length() > 0) {
            environment = env;
        }
    }

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props) {
        if (environment != null) {
        	String propertyKey = String.format("%s.%s", environment, placeholder);
        	String value = resolvePlaceholderWithSystemPropertyFirst(propertyKey,props);
        	if (value != null) {
        		return value;
        	}
        }
        return resolvePlaceholderWithSystemPropertyFirst(placeholder, props);
    }

	private String resolvePlaceholderWithSystemPropertyFirst(String placeholder, Properties props) {
		String value = resolveSystemProperty(placeholder);
        if (value != null) {
        	return value;
        }
        return super.resolvePlaceholder(placeholder, props);
	}

}