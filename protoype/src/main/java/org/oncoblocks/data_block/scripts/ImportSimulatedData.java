package org.oncoblocks.data_block.scripts;

import org.oncoblocks.data_block.model.*;
import org.oncoblocks.data_block.mongo.CancerStudyMongo;
import org.oncoblocks.data_block.mongo.GeneMongo;
import org.oncoblocks.data_block.mongo.MutationMongo;
import org.oncoblocks.data_block.util.MafUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Command Line Tool to Import Large Sets of Simulated Data.
 */
public class ImportSimulatedData {

    public static void main(String args[]) throws IOException {
        try {
            Integer numParticipants = 100;
            if (args.length < 1) {
                System.out.println("Usage:  importSimulatedData.sh <num_participants>");
                System.exit(-1);
            } else {
                numParticipants = new Integer(args[0]);
            }
            Date start = new Date();
            GeneMongo geneMongo = new GeneMongo();
            if (geneMongo.getNumGenes() == 0) {
                System.out.println("Gene data is not in the database.  Load first.");
                System.exit(-1);
            }
            importSimulatedData(numParticipants);
            System.out.println("---------------------");
            Date stop = new Date();
            long interval = stop.getTime() - start.getTime();
            System.out.println("Total time to load:  " + interval + " ms");
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(-1);
        }
    }

    private static void importSimulatedData(int numParticipants) throws IOException {
        for (int i = 0; i< numParticipants; i++) {
            System.out.println("Adding data for participant #" + i);
            for (int j = 0; j < 200; j++) {
                storeMutationRecord(i, j);
            }
        }
    }

    private static void storeMutationRecord(int partipantIndex, int geneIndex)
        throws UnknownHostException {
        Mutation mutation = new Mutation();
        MutationMongo mutationMongo = new MutationMongo();
        mutation.setCancerStudyKey("SIMULATED_CANCER_STUDY_1");
        mutation.setEntrezId(geneIndex);
        mutation.setAminoAcidChange("V600E");
        mutation.setCaseId("SIMULATED_" +  + partipantIndex);
        mutation.setChr("1");
        mutation.setStartPosition(600);
        mutation.setEndPosition(600);
        mutation.setMutationStatus("SOMATIC");
        mutation.setVariantClassification("MISSENSE");
        mutation.setValidationStatus("VALIDATED");
        mutation.setUniprotEntryName("PROTEIN_A");
        mutation.setUniprotProteinPositionStart("600");
        mutation.setUniprotProteinPositionEnd("600");
        mutation.setUniprotReferenceProteinAllele("V");
        mutation.setUniprotObservedProteinAllele("E");
        mutationMongo.addMutation(mutation);
    }
}
