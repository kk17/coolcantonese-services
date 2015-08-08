package net.kk17.spring.config;
 
import org.springframework.core.env.Environment;
 
/**
 * 
 * @author kk17
 */
public class AdvancedEnvironment {
	
	private final Environment env;
	private String envMode;
	
    private static final String ENVIRONMENT_NAME = "env.mode";

	public AdvancedEnvironment(Environment env) {
		super();
		this.env = env;
		String envMode = env.getProperty(ENVIRONMENT_NAME);
        if (envMode != null && envMode.length() > 0) {
            this.envMode = envMode;
        }
	}

	private String resolvePropertyKeyInCurrentEnvMode(String key) {
		if (envMode == null) {
			return key;
		}
		return String.format("%s.%s", envMode, key);
	}
	
	public <T> T getProperty(String arg0, Class<T> arg1, T arg2) {
		String propertyKeyInCurrentEnvMode = resolvePropertyKeyInCurrentEnvMode(arg0);
		if (env.containsProperty(propertyKeyInCurrentEnvMode)) {
			return env.getProperty(propertyKeyInCurrentEnvMode, arg1, arg2);
		}
		return env.getProperty(arg0, arg1, arg2);
	}

	public <T> T getProperty(String arg0, Class<T> arg1) {
		String propertyKeyInCurrentEnvMode = resolvePropertyKeyInCurrentEnvMode(arg0);
		if (env.containsProperty(propertyKeyInCurrentEnvMode)) {
			return env.getProperty(propertyKeyInCurrentEnvMode, arg1);
		}
		return env.getProperty(arg0, arg1);
	}

	public String getProperty(String arg0, String arg1) {
		String propertyKeyInCurrentEnvMode = resolvePropertyKeyInCurrentEnvMode(arg0);
		if (env.containsProperty(propertyKeyInCurrentEnvMode)) {
			return env.getProperty(propertyKeyInCurrentEnvMode, arg1);
		}
		return env.getProperty(arg0, arg1);
	}

	public String getProperty(String arg0) {
		String propertyKeyInCurrentEnvMode = resolvePropertyKeyInCurrentEnvMode(arg0);
		if (env.containsProperty(propertyKeyInCurrentEnvMode)) {
			return env.getProperty(propertyKeyInCurrentEnvMode);
		}
		return env.getProperty(arg0);
	}

    

}