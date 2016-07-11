package com.egen.www;

import static spark.Spark.ipAddress;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Configuration
@ComponentScan(basePackages="com.egen.www")
public class Bootstrap {
	private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null
			? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
	private static final int PORT = System.getenv("OPENSHIFT_DIY_IP") != null
			? Integer.parseInt(System.getenv("OPENSHIFT_DIY_IP")) : 8080;

	public static void main(String[] args) throws Exception {
		ipAddress(IP_ADDRESS);
		port(PORT);
		staticFileLocation("/public");
		new UserResource(new UserService(mongo()));
	}

	private static DB mongo() throws Exception {
		String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
		if (host == null) {
			MongoClient mongoClient = new MongoClient("localhost");
			return mongoClient.getDB("usermanager");
		}
		int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
		String dbname = System.getenv("OPENSHIFT_APP_NAME");
		String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
		String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).build();
		MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
		mongoClient.setWriteConcern(WriteConcern.SAFE);
		DB db = mongoClient.getDB(dbname);
		if (db.authenticate(username, password.toCharArray())) {
			return db;
		} else {
			throw new RuntimeException("Unable to Authneticate with MongoDb!");
		}
	}
}