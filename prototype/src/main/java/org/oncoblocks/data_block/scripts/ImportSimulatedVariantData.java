package org.oncoblocks.data_block.scripts;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.oncoblocks.data_block.model.Mutation;
import org.oncoblocks.data_block.mongo.MutationMongo;

/**
 * Command Line Tool to Import Large Sets of Simulated Variant Data.
 */
public class ImportSimulatedVariantData {
	private static MutationMongo mutationMongo;
	private static Random randomGenerator = new Random();
	
	/**
	 * Source:  Elizier M. Van Allen, et. al, "Whole-exome sequencing and clinical intepretation
	 * of formalin-fixed, parrafix-embedded tumor samples to guide precision cancer medicine.
	 * Nature Medicine, June 2014.
	 * 
	 * Based on 511 patient cases from six WES studies;  total number of mutations:  258,226
	 * (includes synonymous and non-synonymous mutations).
	 */
	private static final int NUM_MUTATIONS_PER_TUMOR = 506;

    public static void main(String args[]) throws IOException {
    		mutationMongo = new MutationMongo();
        try {
            Integer numParticipants = 0;
            if (args.length < 1) {
                System.out.println("Usage:  importSimulatedVariantData.sh <num_participants>");
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
            System.out.println("Adding data for participant #" + i);
            for (int j = 0; j < NUM_MUTATIONS_PER_TUMOR; j++) {
                storeMutationRecord(i, j);
            }
        }
    }

    /**
     * Stores a single Mutation Record with Simulated, Random Data.
     */
    private static void storeMutationRecord(int partipantIndex, int geneIndex)
        throws IOException {
        Mutation mutation = new Mutation();
        mutation.setCancerStudyKey("SIMULATED_CANCER_STUDY_1");
        mutation.setEntrezGeneId(randomGenerator.nextInt(20000));
        mutation.setAaChange("V600_" + randomGenerator.nextInt(20000));
        mutation.setCaseId("SIMULATED_" +  + partipantIndex);
        mutation.setAlternativeAlleleReads(randomGenerator.nextInt(300));
        mutation.setAnnotationTranscript("transcript_"+ randomGenerator.nextInt(20000));
        mutation.setcDnaChange("dna_change_" + randomGenerator.nextInt(20000));
        mutation.setChromosome("chr" + randomGenerator.nextInt(26));
        mutation.setCodonChange("codon_change_" + randomGenerator.nextInt(20000));
        mutation.setDbSnpRsId("db_snp_id" + randomGenerator.nextInt(20000));
        mutation.setDbSnpRsValStatus("db_rs_" + randomGenerator.nextInt(20000));
        mutation.setDbSnpRsValStatus("db_snp_val_" + randomGenerator.nextInt(20000));
        mutation.setDnaEndPosition(randomGenerator.nextInt(20000));
        mutation.setDnaStartPosition(randomGenerator.nextInt(20000));
        mutation.setGeneSymbol("gene_" + randomGenerator.nextInt(20000));
        mutation.setOtherTranscript("other_" + randomGenerator.nextInt(20000));
        mutation.setReferenceAllele("ref_" + randomGenerator.nextInt(20000));
        mutation.setReferenceAlleleReads(randomGenerator.nextInt(300));
        mutation.setReferenceGenome("hg_" + randomGenerator.nextInt(12));
        mutation.setRefseqMrnaId("mrna_" + randomGenerator.nextInt(20000));
        mutation.setRefseqProtId("ref_seq_" + randomGenerator.nextInt(20000));
        mutation.setStrand("strand_" + randomGenerator.nextInt(2));
        mutation.setSwissprotAccession("swiss_prot_" + randomGenerator.nextInt(20000));
        mutation.setSwissprotEntry("swiss_prot_entry_" + randomGenerator.nextInt(20000));
        mutation.setTranscriptStrand("transcript_strand_" + randomGenerator.nextInt(2));
        mutation.setUniprotAaPosition("uniprot_aa_" + randomGenerator.nextInt(20000));
        mutation.setUniprotRegion("region_" + randomGenerator.nextInt(20000));
        mutation.setUniprotSite("site_" + randomGenerator.nextInt(20000));
        mutation.setVariantAllele("variant_allele_"+ randomGenerator.nextInt(20000));
        mutation.setVariantClassification("variant_classification_" + randomGenerator.nextInt(25));
        mutation.setVertebrateAaAlignment("align_" + randomGenerator.nextInt(20000));
        mutationMongo.addMutation(mutation);
    }
    
    
}
