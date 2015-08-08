package net.kk17.coolcantonese.config;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import javabot.dao.util.LocalDateTimeConverter;
import net.kk17.coolcantonese.record.Record;
import net.kk17.spring.config.AdvancedEnvironment;

@Configuration
public class MongoConfig {
	@Autowired
	private AdvancedEnvironment env;
	
	@Bean
	public MongoClient mongoClient(){
		String host = env.getProperty("mongo.host");
		int port = env.getProperty("mongo.port",int.class);
		String username = env.getProperty("mongo.username");
		String password = env.getProperty("mongo.password");
		String database = env.getProperty("mongo.database");
		ServerAddress addr = new ServerAddress(host, port);
	    List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
	    MongoCredential credentia = MongoCredential.createCredential(
	        username, database, password.toCharArray());
	    credentialsList.add(credentia);
	    MongoClient client = new MongoClient(addr, credentialsList);
		return client;
	}
	
	@Bean
	public Morphia  morphia(){
		Morphia morphia = new Morphia();

		// tell Morphia where to find your classes
		// can be called multiple times with different packages or classes
		morphia.mapPackage(Record.class.getPackage().getName());
		morphia.getMapper().getConverters().addConverter(LocalDateTimeConverter.class);
		return morphia;
	}
	
	@Bean
	@Autowired
	public Datastore  datastore(Morphia  morphia, MongoClient mongoClient){
		String database = env.getProperty("mongo.database");
		Datastore datastore = morphia.createDatastore(mongoClient, database);
		datastore.ensureIndexes();
		return datastore;
	}
	
}
