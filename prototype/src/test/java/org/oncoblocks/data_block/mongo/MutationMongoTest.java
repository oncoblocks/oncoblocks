package org.oncoblocks.data_block.mongo;

import junit.framework.TestCase;
import org.oncoblocks.data_block.model.Mutation;

import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Tests the Mutation Mongo Interface.
 */
public class MutationMongoTest extends TestCase {

    public void testMutationMongo() throws UnknownHostException {
        MutationMongo mutationMongo = new MutationMongo();
        mutationMongo.deleteAllRecords();
        for (int i=0; i<100; i++) {
            Mutation mutation = new Mutation();
            mutation.setCancerStudyKey("brca_tcga");
            mutation.setEntrezGeneId(675);
            mutation.setAaChange("V600E");
            mutationMongo.addMutation(mutation);
        }

        long numMutationRecords = mutationMongo.getNumMutations();
        assertEquals(100, numMutationRecords);

        ArrayList<Mutation> mutationList = mutationMongo.getMutationsByEntrezId(675L);
        assertEquals(100, mutationList.size());
    }
}