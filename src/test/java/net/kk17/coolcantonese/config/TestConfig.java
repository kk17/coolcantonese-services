package net.kk17.coolcantonese.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import net.kk17.coolcantonese.record.RecordService;
import net.kk17.coolcantonese.record.RecordServiceImpl;
import net.kk17.spring.config.AdvancedEnvironment;

@Configuration
@ComponentScan(basePackages = {"net.kk17.coolcantonese"})
@Import({MongoConfig.class})
@PropertySource("classpath:/application.properties")
public class TestConfig {
	
    @Bean
    @Autowired
    public AdvancedEnvironment advancedEnvironment(Environment springEnv){
    	return new AdvancedEnvironment(springEnv);
    }
    
    @Bean
    @Autowired
    public RecordService recordService(){
    	return new RecordServiceImpl();
    }
    
	
	

}
