package org.oncoblocks.data_block.mongo;

import com.mongodb.*;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.oncoblocks.data_block.model.Mutation;

public class DatabaseConnection {
    public static final String DB_NAME = "cgds";
    private static DatabaseConnection con = null;
    private DB db=null;
    private MongoClient mongoClient;
    private Morphia morphia;
    private Datastore ds;

    /**
     * Private Constructor, to ensure Singleton pattern.
     * @throws UnknownHostException
     */
    private DatabaseConnection() throws UnknownHostException {
        this.mongoClient = new MongoClient();
        this.db = mongoClient.getDB(DB_NAME);
        this.morphia = new Morphia();
        this.morphia.map(Mutation.class);
        this.ds = morphia.createDatastore(mongoClient, DB_NAME);
        this.ds.setDefaultWriteConcern(WriteConcern.UNACKNOWLEDGED);
    }

    	/**
    	 * Returns Singleton Instance.
    	 * @return DatabaseConnection.
    	 * @throws UnknownHostException
    	 */
    public static synchronized DatabaseConnection getInstanceClass() throws UnknownHostException {
        if(con==null) {
            con=new DatabaseConnection();
        }
        return con;
    }

    public DB getDatabase(){
        return db;
    }
    
    public MongoClient getMongoClient() {
    		return mongoClient;
    }
    
    public Datastore getDataStore() {
    		return ds;
    }
}