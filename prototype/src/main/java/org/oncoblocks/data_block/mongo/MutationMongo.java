package org.oncoblocks.data_block.mongo;

import com.google.gson.Gson;
import com.mongodb.*;

import org.mongodb.morphia.Datastore;
import org.oncoblocks.data_block.model.Mutation;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Mutation Mongo Interface.
 */
public class MutationMongo {
    private static final String MUTATION_COLLECTION = "mutations";
    private DBCollection collection;
    private BulkWriteOperation bulk;
    private DB db;
    private MongoClient mongoClient;
    private Datastore dataStore;

    /**
     * Default constructor.
     * @throws java.net.UnknownHostException Unknown Mongo DB Host.
     */
    public MutationMongo() throws UnknownHostException {
    		DatabaseConnection dbConnection = DatabaseConnection.getInstanceClass();
    		this.mongoClient = dbConnection.getMongoClient();
        this.db = dbConnection.getDatabase();
        this.dataStore = dbConnection.getDataStore();
        this.collection = db.getCollection(MUTATION_COLLECTION);
        this.bulk = collection.initializeUnorderedBulkOperation();
    }

    /**
     * Adds a mutation record to the database.
     * @param mutation Mutation Object.
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonGenerationException 
     */
    public void addMutation(Mutation mutation) {
//        Gson gson = new Gson();
//        String json = gson.toJson(mutation);
//        DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
//    	  ObjectMapper mapper = new ObjectMapper();
//    	  String json = mapper.writeValueAsString(mutation);
//    	  DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
        dataStore.save(mutation);
    }
    
    public void commitInsertions() {
//    		dataStore.save(mutationList);
//	    	bulk.execute(WriteConcern.UNACKNOWLEDGED);
//	    	mongoClient.close();
    }

    /**
     * Gets total number of mutations in the database.
     * @return number of mutations in the database.
     */
    public long getNumMutations() {
        return this.collection.getCount();
    }

    /**
     * Gets mutations by Entrez Gene ID.
     * @return Gene Object.
     */
    public ArrayList<Mutation> getMutationsByEntrezId(Long entrezGeneId) {
        QueryBuilder query = new QueryBuilder().start("entrezGeneId").is(entrezGeneId);
        return executeQuery(query);
    }

    /**
     * Gets mutations by Case ID.
     * @return Gene Object.
     */
    public ArrayList<Mutation> getMutationsByCaseIdId(String caseId) {
        QueryBuilder query = new QueryBuilder().start("caseId").is(caseId);
        return executeQuery(query);
    }


    private ArrayList<Mutation> executeQuery(QueryBuilder query) {
        ArrayList<Mutation> mutationList = new ArrayList<Mutation>();
        DBCursor dbCursor = collection.find(query.get());
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            Gson gson = new Gson();
            Mutation mutation = gson.fromJson(dbObject.toString(), Mutation.class);
            mutationList.add(mutation);
        }
        return mutationList;
    }

    /**
     * Delete all Gene Records in the Database.
     */
    public void deleteAllRecords() {
        collection.drop();
    }
}
