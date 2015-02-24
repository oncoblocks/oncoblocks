package org.oncoblocks.data_block.mongo;

import com.google.gson.Gson;
import com.mongodb.*;
import org.oncoblocks.data_block.model.CancerStudy;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * CancerStudy Mongo Interface.
 */
public class CancerStudyMongo {
    private static final String CANCER_STUDY_COLLECTION = "cancer_studies";
    private DBCollection collection;

    /**
     * Default constructor.
     * @throws java.net.UnknownHostException Unknown Mongo DB Host.
     */
    public CancerStudyMongo() throws UnknownHostException {
        DB db = DatabaseConnection.getInstanceClass().getDatabase();
        this.collection = db.getCollection(CANCER_STUDY_COLLECTION);
    }

    /**
     * Adds a new cancer study to the database.
     * @param cancerStudy Cancer Study Object..
     */
    public void addCancerStudy(CancerStudy cancerStudy) {
        Gson gson = new Gson();
        String json = gson.toJson(cancerStudy);
        DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
        this.collection.save(dbObject);
    }

    /**
     * Gets total number of cancer studies in the database.
     * @return number of genes in the database.
     */
    public long getNumCancerStudies() {
        return this.collection.getCount();
    }

    /**
     * Gets all cancer studies in the database.
     * @return ArrayList of Cancer Study Objects.
     */
    public ArrayList<CancerStudy> getAllCancerStudies() {
        DBCursor dbCursor = collection.find();
        ArrayList<CancerStudy> cancerStudyList = new ArrayList<CancerStudy>();
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            Gson gson = new Gson();
            CancerStudy cancerStudy = gson.fromJson(dbObject.toString(), CancerStudy.class);
            cancerStudyList.add(cancerStudy);
        }
        return cancerStudyList;
    }

    /**
     * Gets all cancer studies in the database.
     * @return ArrayList of Cancer Study Objects.
     */
    public HashMap<String, CancerStudy> getAllCancerStudiesMap() {
        HashMap<String, CancerStudy> cancerStudyHashMap = new HashMap<String, CancerStudy>();
        ArrayList<CancerStudy> cancerStudyList = getAllCancerStudies();
        for (CancerStudy cancerStudy:  cancerStudyList) {
            cancerStudyHashMap.put(cancerStudy.getKey(), cancerStudy);
        }
        return cancerStudyHashMap;
    }

    /**
     * Gets a specific cancer study by key.
     * @return Cancer Study Object.
     */
    public CancerStudy getCancerStudyByKey(String cancerStudyKey) {
        HashMap<String, CancerStudy> cancerStudyHashMap = getAllCancerStudiesMap();
        return cancerStudyHashMap.get(cancerStudyKey.toUpperCase());
    }

    /**
     * Delete all Gene Records in the Database.
     */
    public void deleteAllRecords() {
        collection.drop();
    }
}