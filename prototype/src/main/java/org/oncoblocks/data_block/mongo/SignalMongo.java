package org.oncoblocks.data_block.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.mongodb.morphia.Datastore;
import org.oncoblocks.data_block.model.Signal;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

/**
 * Signal Mongo Interface.
 */
public class SignalMongo {
    private static final String SIGNAL_COLLECTION = "signals";
    private DBCollection collection;
    private DB db;
    private Datastore dataStore;

    /**
     * Default constructor.
     * @throws java.net.UnknownHostException Unknown Mongo DB Host.
     */
    public SignalMongo() throws UnknownHostException {
    		DatabaseConnection dbConnection = DatabaseConnection.getInstanceClass();
        this.db = dbConnection.getDatabase();
        this.dataStore = dbConnection.getDataStore();
        this.collection = db.getCollection(SIGNAL_COLLECTION);
    }

    /**
     * Adds a signal record to the database.
     * @param mutation Signal Object.
     */
    public void addSignal(Signal signal) {
        dataStore.save(signal);
    }

    /**
     * Gets total number of signal records in the database.
     * @return number of signal records in the database.
     */
    public long getNumSignalRecords() {
        return this.collection.getCount();
    }

    /**
     * Gets signals by Entrez Gene ID.
     * @return Gene Object.
     */
    public ArrayList<Signal> getSignalsByEntrezId(Long entrezGeneId) {
        QueryBuilder query = new QueryBuilder().start("entrezGeneId").is(entrezGeneId);
        return executeQuery(query);
    }

    /**
     * Gets signals by Case ID.
     * @return Gene Object.
     */
    public ArrayList<Signal> getMutationsByCaseIdId(String caseId) {
        QueryBuilder query = new QueryBuilder().start("caseId").is(caseId);
        return executeQuery(query);
    }

    private ArrayList<Signal> executeQuery(QueryBuilder query) {
        ArrayList<Signal> signalList = new ArrayList<Signal>();
        DBCursor dbCursor = collection.find(query.get());
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            Gson gson = new Gson();
            Signal signal = gson.fromJson(dbObject.toString(), Signal.class);
            signalList.add(signal);
        }
        return signalList;
    }

    /**
     * Deletes all Records in the Database.
     */
    public void deleteAllRecords() {
        collection.drop();
    }
}