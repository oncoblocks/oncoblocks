package org.oncoblocks.data_block.mongo;

import com.google.gson.Gson;
import com.mongodb.*;
import org.oncoblocks.data_block.model.Gene;

import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Gene Mongo Interface.
 */
public class GeneMongo {
    private static final String GENE_COLLECTION = "genes";
    private DBCollection collection;

    /**
     * Default constructor.
     * @throws UnknownHostException Unknown Mongo DB Host.
     */
    public GeneMongo() throws UnknownHostException {
        DB db = DatabaseConnection.getInstanceClass().getDatabase();
        this.collection = db.getCollection(GENE_COLLECTION);
    }

    /**
     * Adds a new gene object to the database.
     * @param gene Gene Object.
     */
    public void addGene(Gene gene) {
        Gson gson = new Gson();
        String json = gson.toJson(gene);
        DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
        this.collection.save(dbObject);
    }

    /**
     * Gets total number of genes in the database.
     * @return number of genes in the database.
     */
    public long getNumGenes() {
        return this.collection.getCount();
    }

    /**
     * Gets all genes in the database.
     * @return ArrayList of Gene Objects.
     */
    public ArrayList<Gene> getAllGenes() {
        DBCursor dbCursor = collection.find();
        ArrayList<Gene> geneList = new ArrayList<Gene>();
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            Gson gson = new Gson();
            Gene gene = gson.fromJson(dbObject.toString(), Gene.class);
            geneList.add(gene);
        }
        return geneList;
    }

    /**
     * Gets the gene by symbol.
     * @return Gene Object.
     */
    public ArrayList<Gene> getGeneBySymbol(String geneSymbol) {
        QueryBuilder query = new QueryBuilder().start("standardGeneSymbol").is(geneSymbol);
        return executeQuery(query);
    }

    /**
     * Gets the gene by entrez gene ID.
     * @return Gene Object.
     */
    public ArrayList<Gene> getGeneByEntrezId(Long entrezGeneId) {
        QueryBuilder query = new QueryBuilder().start("entrezGeneId").is(entrezGeneId);
        return executeQuery(query);
    }

    /**
     * Gets the gene by alias.
     * @return Gene Object.
     */
    public ArrayList<Gene> getGeneByAlias(String alias) {
        QueryBuilder query = new QueryBuilder().start("aliases").is(alias);
        return executeQuery(query);
    }

    private ArrayList<Gene> executeQuery(QueryBuilder query) {
        ArrayList<Gene> geneList = new ArrayList<Gene>();
        DBCursor dbCursor = collection.find(query.get());
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            Gson gson = new Gson();
            Gene gene = gson.fromJson(dbObject.toString(), Gene.class);
            geneList.add(gene);
        }
        return geneList;
    }

    /**
     * Delete all Gene Records in the Database.
     */
    public void deleteAllRecords() {
        collection.drop();
    }
}