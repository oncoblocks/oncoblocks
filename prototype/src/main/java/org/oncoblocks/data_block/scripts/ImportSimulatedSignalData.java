package org.oncoblocks.data_block.scripts;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.oncoblocks.data_block.model.Mutation;
import org.oncoblocks.data_block.model.Signal;
import org.oncoblocks.data_block.mongo.MutationMongo;
import org.oncoblocks.data_block.mongo.SignalMongo;

/**
 * Command Line Tool to Import Large Sets of Simulated Signal Data.
 */
public class ImportSimulatedSignalData {
	private static SignalMongo signalMongo;
	private static Random randomGenerator = new Random();
	private static final int NUM_GENES = 20000;
	
    public static void main(String args[]) throws IOException {
    		signalMongo = new SignalMongo();
        try {
            Integer numParticipants = 0;
            if (args.length < 1) {
                System.out.println("Usage:  importSimulatedSignalData.sh <num_participants>");
                System.exit(-1);
            } else {
                numParticipants = new Integer(args[0]);
            }
            Date start = new Date();
            importSimulatedData(numParticipants);
            System.out.println("---------------------");
            Date stop = new Date();
            long interval = stop.getTime() - start.getTime();
            System.out.println("Total time to load:  " + interval + " ms");
            System.out.println("---------------------");
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(-1);
        }
    }

    private static void importSimulatedData(int numParticipants) throws IOException {
        for (int i = 0; i< numParticipants; i++) {
            System.out.println("Adding " + NUM_GENES + " signal records for participant #" + i);
            for (int j = 0; j < NUM_GENES; j++) {
                storeSignalRecord(i, j);
            }
        }
    }

    /**
     * Stores a single Signal Record with Simulated, Random Data.
     */
    private static void storeSignalRecord(int partipantIndex, int geneIndex)
        throws IOException {
        Signal signal = new Signal();
        signal.setCancerStudyKey("SIMULATED_CANCER_STUDY_1");
        signal.setEntrezGeneId(geneIndex);
        signal.setValue(randomGenerator.nextInt(100));
        signalMongo.addSignal(signal);
    }
}