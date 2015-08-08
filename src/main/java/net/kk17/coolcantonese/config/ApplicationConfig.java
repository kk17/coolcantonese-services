package net.kk17.coolcantonese.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.alibaba.dubbo.config.annotation.Service;

import net.kk17.spring.config.AdvancedEnvironment;

@Configuration
@ComponentScan(basePackages = {"net.kk17.coolcantonese"}, includeFilters = {@ComponentScan.Filter(value = Service.class)})
@ImportResource("classpath:META-INF/spring/dubbo-service-provider.xml")
@Import({MongoConfig.class})
@PropertySource("classpath:/application.properties")
public class ApplicationConfig {
	
    @Bean
    @Autowired
    public AdvancedEnvironment advancedEnvironment(Environment springEnv){
    	return new AdvancedEnvironment(springEnv);
    }
    
	
	

}
