package org.oncoblocks.data_block.scripts;

import java.io.IOException;
import java.util.Date;

import org.oncoblocks.data_block.mongo.CancerStudyMongo;
import org.oncoblocks.data_block.mongo.GeneMongo;
import org.oncoblocks.data_block.mongo.MutationMongo;
import org.oncoblocks.data_block.mongo.SignalMongo;

/**
 * Resets the Mongo DB Collections.
 */
public class ResetDatabase {

    public static void main(String args[]) throws IOException {
    		Date start = new Date();
        GeneMongo geneMongo = new GeneMongo();
        CancerStudyMongo cancerStudyMongo = new CancerStudyMongo();
        MutationMongo mutationMongo = new MutationMongo();
        SignalMongo signalMongo = new SignalMongo();

        System.out.println("Deleting Gene Records.");
        geneMongo.deleteAllRecords();
        System.out.println("Deleting Cancer Studies.");
        cancerStudyMongo.deleteAllRecords();
        System.out.println("Deleting Mutation Records.");
        mutationMongo.deleteAllRecords();

        System.out.println("Deleting Signal Records.");
        signalMongo.deleteAllRecords();

        Date stop = new Date();
        long duration = stop.getTime() - start.getTime();
        System.out.println("Total time:  " + duration + " ms");
    }
}
