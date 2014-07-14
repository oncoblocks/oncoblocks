package org.oncoblocks.data_block.mongo;

import com.google.gson.Gson;
import com.mongodb.*;
import org.oncoblocks.data_block.model.Mutation;

import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Mutation Mongo Interface.
 */
public class MutationMongo {
    private static final String MUTATION_COLLECTION = "mutations";
    private DBCollection collection;

    /**
     * Default constructor.
     * @throws java.net.UnknownHostException Unknown Mongo DB Host.
     */
    public MutationMongo() throws UnknownHostException {
        DB db = DatabaseConnection.getInstanceClass().getDatabaseConnection();
        this.collection = db.getCollection(MUTATION_COLLECTION);
    }

    /**
     * Adds a mutation record to the database.
     * @param mutation Mutation Object.
     */
    public void addMutation(Mutation mutation) {
        Gson gson = new Gson();
        String json = gson.toJson(mutation);
        System.out.println("Saving:  " + json);
        DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
        this.collection.save(dbObject);
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