package org.oncoblocks.data_block.util;

import org.oncoblocks.data_block.model.MafRecord;
import org.oncoblocks.data_block.model.OncotatorAnnotations;

import java.util.ArrayList;

/**
 * Utility Class for Parsing MAF Files.
 * <p/>
 * This utility class handles variable columns and column orderings within MAF Files.
 * (Comments next to data fields indicate corresponding database columns or
 * column headers in the MAF file).
 */
public class MafUtil {
    private int chrIndex = -1; // CHR
    private int ncbiIndex = -1; // NCBI_BUILD
    private int startPositionIndex = -1; // START_POSITION
    private int endPositionIndex = -1; // END_POSITION
    private int hugoGeneSymbolIndex = -1;
    private int entrezGeneIdIndex = -1; // ENTREZ_GENE_ID
    private int referenceAlleleIndex = -1; // REFERENCE_ALLELE
    private int variantClassificationIndex = -1; // MUTATION_TYPE
    private int variantTypeIndex = -1; // VARIANT_TYPE
    private int centerIndex = -1; // CENTER
    private int strandIndex = -1; // STRAND
    private int tumorSeqAllele1Index = -1; // TUMOR_SEQ_ALLELE1
    private int tumorSeqAllele2Index = -1; // TUMOR_SEQ_ALLELE1
    private int dbSNPIndex = -1; // DB_SNP_RS
    private int tumorSampleIndex = -1; //
    private int mutationStatusIndex = -1; // MUTATION_STATUS
    private int validationStatusIndex = -1; // VALIDATION_STATUS
    private int sequencerIndex = -1; // SEQUENCER_INDEX
    private int aminoAcidChangeIndex = -1;
    private int oncoGeneSymbolIndex = -1;
    private int oncoVarianClassificationIndex = -1;
    private int oncoProteinChanceIndex = -1;
    private int oncoCosmicOverlappingIndex = -1;
    private int oncoDdbSnpRsIndex = -1;
    private int oncoUniprotAccessionIndex = -1;
    private int oncoUniprotEntryNameIndex = -1;
    private int oncoUniprotProteinPositionStartIndex = -1;
    private int oncoUniprotProteinPositionEndIndex = -1;
    private int oncoReferenceProteinAlleleIndex = -1;
    private int oncoObservedProteinAlleleIndex = -1;
    private int oncoRefSeqMrnaIdIndex = -1;
    private int oncoRefSeqProteinIdIndex = -1;
    private ArrayList<String> headerList = new ArrayList<String>();

    /**
     * Constructor.
     *
     * @param headerLine Header Line.
     */
    public MafUtil(String headerLine) {
        String parts[] = headerLine.split("\t");
        for (int i = 0; i < parts.length; i++) {
            String header = parts[i];
            headerList.add(header);
            if (header.equalsIgnoreCase("Chromosome")) {
                chrIndex = i;
            } else if (header.equals("NCBI_Build")) {
                ncbiIndex = i;
            } else if (header.equalsIgnoreCase("Start_Position")) {
                startPositionIndex = i;
            } else if (header.equalsIgnoreCase("End_Position")) {
                endPositionIndex = i;
            } else if (header.equalsIgnoreCase("Hugo_Symbol")) {
                hugoGeneSymbolIndex = i;
            } else if (header.equalsIgnoreCase("Entrez_Gene_Id")) {
                entrezGeneIdIndex = i;
            } else if (header.equalsIgnoreCase("Reference_Allele")) {
                referenceAlleleIndex = i;
            } else if (header.equalsIgnoreCase("Variant_Classification")) {
                variantClassificationIndex = i;
            } else if (header.equalsIgnoreCase("Variant_Type")) {
                variantTypeIndex = i;
            } else if (header.equalsIgnoreCase("Center")) {
                centerIndex = i;
            } else if (header.equals("Strand")) {
                strandIndex = i;
            } else if (header.equalsIgnoreCase("Tumor_Seq_Allele1")) {
                tumorSeqAllele1Index = i;
            } else if (header.equalsIgnoreCase("Tumor_Seq_Allele2")) {
                tumorSeqAllele2Index = i;
            } else if (header.equalsIgnoreCase("dbSNP_RS")) {
                dbSNPIndex = i;
            } else if (header.equalsIgnoreCase("Tumor_Sample_Barcode")) {
                tumorSampleIndex = i;
            } else if (header.equalsIgnoreCase("Mutation_Status")) {
                mutationStatusIndex = i;
            } else if (header.equalsIgnoreCase("Validation_Status")) {
                validationStatusIndex = i;
            } else if (header.equalsIgnoreCase("Sequencer")) {
                sequencerIndex = i;
            } else if (header.equalsIgnoreCase("Protein_Change")
                    || header.equalsIgnoreCase("amino_acid_change_wu")
                    || header.equalsIgnoreCase("amino_acid_change")
                    || header.equalsIgnoreCase("AAChange")) {
                aminoAcidChangeIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_GENE_SYMBOL)) {
                this.oncoGeneSymbolIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_VARIANT_CLASSIFICATION)) {
                this.oncoVarianClassificationIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_COSMIC_OVERLAPPING)) {
                this.oncoCosmicOverlappingIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_DBSNP_RS)) {
                this.oncoDdbSnpRsIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_OBSERVED_PROTEIN_ALLELE)) {
                this.oncoObservedProteinAlleleIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_PROTEIN_CHANGE)) {
                this.oncoProteinChanceIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_REF_SEQ_MRNA_ID)) {
                this.oncoRefSeqMrnaIdIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_REF_SEQ_PROTEIN_ID)) {
                this.oncoRefSeqProteinIdIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_REFERENCE_PROTEIN_ALLELE)) {
                this.oncoReferenceProteinAlleleIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_UNIPROT_ACCESSION)) {
                this.oncoUniprotAccessionIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_UNIPROT_ENTRYNAME)) {
                this.oncoUniprotEntryNameIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_UNIPROT_PROTEIN_POSITION_START)) {
                this.oncoUniprotProteinPositionStartIndex = i;
            } else if (header.equalsIgnoreCase(OncotatorAnnotations.ONCOTATOR_UNIPROT_PROTEIN_POSITION_END)) {
                this.oncoUniprotProteinPositionEndIndex = i;
            }
        }
    }

    public boolean hasAminoAcidAndVariantClassificationData() {
        if (aminoAcidChangeIndex > -1 && variantClassificationIndex > -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the Complete Set of Headers.
     *
     * @return Header Set.
     */
    public ArrayList<String> getHeaderList() {
        return headerList;
    }

    public MafRecord parseRecord(String line) {
        String parts[] = line.split("\t");
        MafRecord record = new MafRecord();
        record.setCenter(getPartString(centerIndex, parts));
        record.setChr(getPartString(chrIndex, parts));
        record.setDbSNP_RS(getPartString(dbSNPIndex, parts));
        record.setStartPosition(getPartLong(startPositionIndex, parts));
        record.setEndPosition(getPartLong(endPositionIndex, parts));
        record.setEntrezGeneId(getPartLong(entrezGeneIdIndex, parts));
        record.setHugoGeneSymbol(getPartString(hugoGeneSymbolIndex, parts));
        record.setNcbiBuild(getPartString(ncbiIndex, parts));
        record.setReferenceAllele(getPartString(referenceAlleleIndex, parts));
        record.setStrand(getPartString(strandIndex, parts));
        record.setDbSNP_RS(getPartString(dbSNPIndex, parts));
        record.setTumorSampleID(getPartString(tumorSampleIndex, parts));
        record.setTumorSeqAllele1(getPartString(tumorSeqAllele1Index, parts));
        record.setTumorSeqAllele2(getPartString(tumorSeqAllele2Index, parts));
        record.setVariantClassification(getPartString(variantClassificationIndex, parts));
        record.setVariantType(getPartString(variantTypeIndex, parts));
        record.setMutationStatus(getPartString(mutationStatusIndex, parts));
        record.setValidationStatus(getPartString(validationStatusIndex, parts));
        record.setSequencer(getPartString(sequencerIndex, parts));
        record.setProteinChange(getPartString(aminoAcidChangeIndex, parts));
        record.setOncoVariantClassification(getPartString(oncoVarianClassificationIndex, parts));
        record.setOncoGeneSymbol(getPartString(oncoGeneSymbolIndex, parts));
        record.setOncoCosmicOverlapping(getPartString(oncoCosmicOverlappingIndex, parts));
        record.setOncoDbspRs(getPartString(oncoDdbSnpRsIndex, parts));
        record.setOncoObservedProteinAllele(getPartString(oncoObservedProteinAlleleIndex, parts));
        record.setOncoReferenceProteinAllele(getPartString(oncoReferenceProteinAlleleIndex, parts));
        record.setOncoProteinChange(getPartString(oncoProteinChanceIndex, parts));
        record.setOncoRefSeqMrnaId(getPartString(oncoRefSeqMrnaIdIndex, parts));
        record.setOncoRefSeqProteinId(getPartString(oncoRefSeqProteinIdIndex, parts));
        record.setOncoUniprotAccession(getPartString(oncoUniprotAccessionIndex, parts));
        record.setOncoUniprotEntryName(getPartString(oncoUniprotEntryNameIndex, parts));
        record.setOncoUniprotProteinPositionStart(getPartString(oncoUniprotProteinPositionStartIndex, parts));
        record.setOncoUniprotProteinPositionEnd(getPartString(oncoUniprotProteinPositionEndIndex, parts));
        return record;
    }

    private String getPartString(int index, String[] parts) {
        try {
            return parts[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return MafRecord.NA_STRING;
        }
    }

    private Long getPartLong(int index, String[] parts) {
        try {
            String part = parts[index];
            return Long.parseLong(part);
        } catch (ArrayIndexOutOfBoundsException e) {
            return MafRecord.NA_LONG;
        } catch (NumberFormatException e) {
            return MafRecord.NA_LONG;
        }
    }

    public int getAminoAcidChangeIndex() {
        return aminoAcidChangeIndex;
    }

    public int getVariantClassificationIndex() {
        return variantClassificationIndex;
    }

    public boolean hasOncotatorAnnotation() {
        if (this.oncoVarianClassificationIndex > 0) {
            return true;
        } else {
            return false;
        }
    }
}