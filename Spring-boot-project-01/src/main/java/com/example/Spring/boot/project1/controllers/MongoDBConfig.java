package com.example.Spring.boot.project1.controllers;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfig {
    @Value("${spring.data.mongodb.uri}")
    private String MONGO_URI;
    public void connectMongoDB() {
        System.out.println("Connecting MongoDB...");
        System.out.println("MONGO_URI: " + MONGO_URI);
        try {
            System.out.println("MONGO_URI: " + MONGO_URI);
            ConnectionString connectionString = new ConnectionString(MONGO_URI);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .serverApi(ServerApi.builder()
                            .version(ServerApiVersion.V1)
                            .build())
                    .build();
            MongoClient mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase("Cluster0");
            System.out.println("MongoDB connected...");
        } catch(Exception e) {
            System.err.println(e.getCause());
        }
    }
}
