package org.oncoblocks.data_block.scripts;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.oncoblocks.data_block.model.Signal;
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
        for (int i = 0; i< NUM_GENES; i++) {
            System.out.println("Adding signal records for gene #" + i);
        		Signal signal = new Signal();
        		signal.setEntrezGeneId(i);
        		HashMap<String, Double> valueMap = new HashMap<String, Double> ();
        		for (int j=0; j < numParticipants; j++) {
        			valueMap.put("CASE_ID_" + j, new Double(randomGenerator.nextInt(100)));
        		}
        		signal.setValueMap(valueMap);
            signal.setCancerStudyKey("SIMULATED_CANCER_STUDY_1");
            signal.setGeneticProfileKey("SIMULATED_GENETIC_PROFILE_1");
            signal.setSignalType(1);
            signalMongo.addSignal(signal);
        }
    }
}