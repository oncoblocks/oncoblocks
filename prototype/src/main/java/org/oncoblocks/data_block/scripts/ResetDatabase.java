package org.oncoblocks.data_block.scripts;

import org.oncoblocks.data_block.mongo.CancerStudyMongo;
import org.oncoblocks.data_block.mongo.GeneMongo;
import org.oncoblocks.data_block.mongo.MutationMongo;

import java.io.IOException;

/**
 * Resets the Mongo DB Collections.
 */
public class ResetDatabase {

    public static void main(String args[]) throws IOException {
        GeneMongo geneMongo = new GeneMongo();
        CancerStudyMongo cancerStudyMongo = new CancerStudyMongo();
        MutationMongo mutationMongo = new MutationMongo();

        System.out.println("Deleting Gene Records.");
        geneMongo.deleteAllRecords();
        System.out.println("Deleting Cancer Studies.");
        cancerStudyMongo.deleteAllRecords();
        System.out.println("Deleting Mutation Records.");
        mutationMongo.deleteAllRecords();
    }
}
