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
import java.util.HashSet;
import java.util.List;

/**
 * Command Line Tool to Import Mutation Data.
 */
public class ImportMutationData {
    private static HashSet<String> caseSet = new HashSet<String>();
    private static int numMutationStored = 0;

    public static void main(String args[]) throws IOException {
        try {
            if (args.length < 1) {
                System.out.println("Usage:  importMaf.sh <dir_name or file_name>");
                System.exit(-1);
            }
            GeneMongo geneMongo = new GeneMongo();
            if (geneMongo.getNumGenes() == 0) {
                System.out.println("Gene data is not in the database.  Load first.");
                System.exit(-1);
            }
            File mafFile = new File(args[0]);
            if (mafFile.isDirectory()) {
                String fileNames[] = mafFile.list();
                for (String fileName : fileNames) {
                    importMaf(new File(mafFile, fileName));
                }
            } else {
                importMaf(mafFile);
            }

            System.out.println("---------------------");
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(-1);
        }
    }

    private static CancerStudy getCancerStudy(File file) throws UnknownHostException {
        //  File name looks like this:  blca_tcga.maf.txt
        String parts[] = file.getName().split("\\.");
        String cancerNameKey = parts[0].split("_")[0];
        CancerStudyMongo cancerStudyMongo = new CancerStudyMongo();
        CancerStudy cancerStudy = cancerStudyMongo.getCancerStudyByKey(cancerNameKey);
        if (cancerStudy == null) {
            throw new NullPointerException("Cancer Study is not known:  " + cancerNameKey);
        } else {
            return cancerStudy;
        }
    }

    private static void importMaf(File mafFile) throws IOException {
        CancerStudy cancerStudy = getCancerStudy(mafFile);

        System.out.println("Reading mutation data from:  " + mafFile.getAbsolutePath());
        FileReader reader = new FileReader(mafFile);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String metaDataLine = bufferedReader.readLine();

        if (!metaDataLine.startsWith("!num_sequenced_cases")) {
            throw new IllegalArgumentException("File is missing !num_sequenced_cases meta deta.  Aborting!");
        }
        int numSequencedCases = getNumSequencedCases(metaDataLine);

        String headerLine = bufferedReader.readLine();
        MafUtil mafUtil = new MafUtil(headerLine);
        if (!mafUtil.hasOncotatorAnnotation()) {
            throw new IllegalArgumentException("File is missing Oncotator Annotations.  Aborting!");
        }

        String line = bufferedReader.readLine();
        while (line != null) {
            MafRecord mafRecord = mafUtil.parseRecord(line);
            storeMutationRecord(mafRecord, cancerStudy);
            line = bufferedReader.readLine();
        }
        reader.close();
        System.out.println("Cancer Study:  " + cancerStudy.getKey());
        System.out.println("Total number of sequenced cases:  " + numSequencedCases);
        System.out.println("Total number of mutations stored:  " + numMutationStored);
    }

    private static int getNumSequencedCases(String metaDataLine) {
        String parts[] = metaDataLine.split("=");
        return Integer.parseInt(parts[1]);
    }

    private static void storeMutationRecord(MafRecord mafRecord, CancerStudy cancerStudy) throws IOException {
        Mutation mutation = new Mutation();
        GeneMongo mongoGene = new GeneMongo();
        MutationMongo mutationMongo = new MutationMongo();
        List<Gene> geneList = mongoGene.getGeneBySymbol(mafRecord.getOncoGeneSymbol());
        if (geneList.size() == 1) {
            caseSet.add(mafRecord.getCaseId());
            if (acceptMutation(mafRecord)) {
                mutation.setCancerStudyKey(cancerStudy.getKey());
                mutation.setEntrezGeneId(geneList.get(0).getEntrezGeneId());
                mutation.setAaChange(mafRecord.getOncoProteinChange());
                mutation.setCaseId(mafRecord.getCaseId());
                mutation.setChromosome(mafRecord.getChr());
//                mutation.setDnaStartPosition(mafRecord.getStartPosition());
//                mutation.setDnaStopPosition(mafRecord.getEndPosition());
//                mutation.setMutationStatus(mafRecord.getMutationStatus());
//                mutation.setVariantClassification(mafRecord.getOncoVariantClassification());
//                mutation.setValidationStatus(mafRecord.getValidationStatus());
//                mutation.setUniprotEntryName(mafRecord.getOncoUniprotEntryName());
//                mutation.setUniprotProteinPositionStart(mafRecord.getOncoUniprotProteinPositionStart());
//                mutation.setUniprotProteinPositionEnd(mafRecord.getOncoUniprotProteinPositionEnd());
//                mutation.setUniprotReferenceProteinAllele(mafRecord.getOncoReferenceProteinAllele());
//                mutation.setUniprotObservedProteinAllele(mafRecord.getOncoObservedProteinAllele());
                mutationMongo.addMutation(mutation);
                numMutationStored++;
            }
        } else {
            System.out.println("Gene not recognized:  " + mafRecord.getOncoGeneSymbol());
        }
    }

    private static boolean acceptMutation(MafRecord mafRecord) {
        //  Do not accept the following types of mutations.
        String variantClassification = mafRecord.getOncoVariantClassification();
        if (variantClassification.equalsIgnoreCase(VariantClassificationMap.SILENT)
                || variantClassification.equalsIgnoreCase(VariantClassificationMap.INTRON)
                || variantClassification.equalsIgnoreCase(VariantClassificationMap.THREE_PRIME_UTR)
                || variantClassification.equalsIgnoreCase(VariantClassificationMap.FIVE_PRIME_UTR)
                || variantClassification.equalsIgnoreCase(VariantClassificationMap.THREE_PRIME_FLANK)
                || variantClassification.equalsIgnoreCase(VariantClassificationMap.FIVE_PRIME_FLANK)
                || variantClassification.equalsIgnoreCase(VariantClassificationMap.IGR)
                || variantClassification.equalsIgnoreCase(VariantClassificationMap.RNA)
                || variantClassification.equalsIgnoreCase(VariantClassificationMap.TRANSLATION_START_SITE)
                || variantClassification.equalsIgnoreCase(VariantClassificationMap.NA)) {
            return false;
            //  Do not accept Wildtype, LOH, or Germline
        } else if (mafRecord.getMutationStatus().equalsIgnoreCase("Wildtype")
                || mafRecord.getMutationStatus().equalsIgnoreCase("LOH")
                || mafRecord.getMutationStatus().equalsIgnoreCase("Germline")) {
            return false;
            //  Do not accept mutations that failed validation
        } else if (mafRecord.getValidationStatus().equalsIgnoreCase("Wildtype")) {
            return false;
        } else {
            return true;
        }
    }
}
