package org.oncoblocks.data_block.mongo;

import com.mongodb.*;

import java.net.UnknownHostException;

public class DatabaseConnection {
    public static final String DB_NAME = "cgds";
    private static DatabaseConnection con = null;
    private DB db=null;
    private MongoClient mongoClient;

    private DatabaseConnection() throws UnknownHostException {
        MongoOptions options = new MongoOptions();
        options.connectionsPerHost = 100;
        options.maxWaitTime = 2000;
        options.socketKeepAlive = true;
        options.threadsAllowedToBlockForConnectionMultiplier = 50;

        this.mongoClient = new MongoClient();
        this.db = mongoClient.getDB(DB_NAME);
    }

    public static synchronized DatabaseConnection getInstanceClass() throws UnknownHostException {
        if(con==null) {
            con=new DatabaseConnection();
        }
        return con;
    }

    public DB getDatabaseConnection(){
        return db;
    }
    
    public MongoClient getMongoClient() {
    	return mongoClient;
    }
}