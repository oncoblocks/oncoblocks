package org.oncoblocks.data_block.model;

/**
 * Encapsulates Details Regarding a Single MAF Record.
 */
public class MafRecord {
    public final static String NA_STRING = "NA";
    public final static long NA_LONG = -1L;
    private final static String TAB = "\t";
    private final static String KEY_DELIM = "_";

    private String chr;
    private String ncbiBuild;
    private long startPosition;
    private long endPosition;
    private String hugoGeneSymbol;
    private long entrezGeneId;
    private String referenceAllele;
    private String variantClassification; // mutation type
    private String variantType;
    private String center;
    private String strand;
    private String tumorSeqAllele1;
    private String tumorSeqAllele2;
    private String dbSNP_RS;
    private String tumorSampleID;
    private String mutationStatus;
    private String validationStatus;
    private String sequencer;
    private String proteinChange;
    private String oncoGeneSymbol;
    private String oncoVariantClassification;
    private String oncoProteinChange;
    private String oncoCosmicOverlapping;
    private String oncoDbspRs;
    private String oncoUniprotAccession;
    private String oncoUniprotEntryName;
    private String oncoUniprotProteinPositionStart;
    private String oncoUniprotProteinPositionEnd;
    private String oncoReferenceProteinAllele;
    private String oncoObservedProteinAllele;
    private String oncoRefSeqMrnaId;
    private String oncoRefSeqProteinId;

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public String getNcbiBuild() {
        return ncbiBuild;
    }

    public void setNcbiBuild(String ncbiBuild) {
        this.ncbiBuild = ncbiBuild;
    }

    public long getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(long startPosition) {
        this.startPosition = startPosition;
    }

    public long getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(long endPosition) {
        this.endPosition = endPosition;
    }

    public String getHugoGeneSymbol() {
        return hugoGeneSymbol;
    }

    public void setHugoGeneSymbol(String hugoGeneSymbol) {
        this.hugoGeneSymbol = hugoGeneSymbol;
    }

    public long getEntrezGeneId() {
        return entrezGeneId;
    }

    public void setEntrezGeneId(long entrezGeneId) {
        this.entrezGeneId = entrezGeneId;
    }

    public String getReferenceAllele() {
        return referenceAllele;
    }

    public void setReferenceAllele(String referenceAllele) {
        this.referenceAllele = referenceAllele;
    }

    public String getVariantClassification() {
        return variantClassification;
    }

    public void setVariantClassification(String variantClassification) {
        this.variantClassification = variantClassification;
    }

    public String getVariantType() {
        return variantType;
    }

    public void setVariantType(String variantType) {
        this.variantType = variantType;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public String getTumorSeqAllele1() {
        return tumorSeqAllele1;
    }

    public void setTumorSeqAllele1(String tumorSeqAllele1) {
        this.tumorSeqAllele1 = tumorSeqAllele1;
    }

    public String getTumorSeqAllele2() {
        return tumorSeqAllele2;
    }

    public String getTumorAllele() {
        if (!tumorSeqAllele1.equals(referenceAllele)) {
            return tumorSeqAllele1;
        } else {
            return tumorSeqAllele2;
        }
    }

    public void setTumorSeqAllele2(String tumorSeqAllele2) {
        this.tumorSeqAllele2 = tumorSeqAllele2;
    }

    public String getDbSNP_RS() {
        return dbSNP_RS;
    }

    public void setDbSNP_RS(String dbSNP_RS) {
        this.dbSNP_RS = dbSNP_RS;
    }

    public String getTumorSampleID() {
        return tumorSampleID;
    }

    public void setTumorSampleID(String tumorSampleID) {
        this.tumorSampleID = tumorSampleID;
    }

    public String getCaseId() {
        //  Tumor Sample ID:  TCGA-13-1405-01A-01W-0494-09
        //  Case ID:  TCGA-13-1405
        if (tumorSampleID != null) {
            if (tumorSampleID.startsWith("TCGA")) {
                String parts[] = tumorSampleID.split("-");
                if (parts.length > 2) {
                    return parts[0] + "-" + parts[1] + "-" + parts[2];
                } else {
                    return null;
                }
            } else {
                return tumorSampleID;
            }
        } else {
            return null;
        }
    }

    public String getMutationStatus() {
        return mutationStatus;
    }

    public void setMutationStatus(String mutationStatus) {
        this.mutationStatus = mutationStatus;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getSequencer() {
        return sequencer;
    }

    public void setSequencer(String sequencer) {
        this.sequencer = sequencer;
    }

    public String getProteinChange() {
        return proteinChange;
    }

    public void setProteinChange(String proteinChange) {
        this.proteinChange = proteinChange;
    }

    public String getOncoGeneSymbol() {
        return oncoGeneSymbol;
    }

    public void setOncoGeneSymbol(String oncoGeneSymbol) {
        this.oncoGeneSymbol = oncoGeneSymbol;
    }

    public String getOncoVariantClassification() {
        return oncoVariantClassification;
    }

    public void setOncoVariantClassification(String oncoVariantClassification) {
        this.oncoVariantClassification = oncoVariantClassification;
    }

    public String getOncoProteinChange() {
        return oncoProteinChange;
    }

    public void setOncoProteinChange(String oncoProteinChange) {
        this.oncoProteinChange = oncoProteinChange;
    }

    public String getOncoCosmicOverlapping() {
        return oncoCosmicOverlapping;
    }

    public void setOncoCosmicOverlapping(String oncoCosmicOverlapping) {
        this.oncoCosmicOverlapping = oncoCosmicOverlapping;
    }

    public String getOncoDbspRs() {
        return oncoDbspRs;
    }

    public void setOncoDbspRs(String oncoDbspRs) {
        this.oncoDbspRs = oncoDbspRs;
    }

    public String getOncoUniprotAccession() {
        return oncoUniprotAccession;
    }

    public void setOncoUniprotAccession(String oncoUniprotAccession) {
        this.oncoUniprotAccession = oncoUniprotAccession;
    }

    public String getOncoUniprotEntryName() {
        return oncoUniprotEntryName;
    }

    public void setOncoUniprotEntryName(String oncoUniprotEntryName) {
        this.oncoUniprotEntryName = oncoUniprotEntryName;
    }

    public String getOncoUniprotProteinPositionStart() {
        return oncoUniprotProteinPositionStart;
    }

    public void setOncoUniprotProteinPositionStart(String oncoUniprotProteinPositionStart) {
        this.oncoUniprotProteinPositionStart = oncoUniprotProteinPositionStart;
    }

    public String getOncoUniprotProteinPositionEnd() {
        return oncoUniprotProteinPositionEnd;
    }

    public void setOncoUniprotProteinPositionEnd(String oncoUniprotProteinPositionEnd) {
        this.oncoUniprotProteinPositionEnd = oncoUniprotProteinPositionEnd;
    }

    public String getOncoReferenceProteinAllele() {
        return oncoReferenceProteinAllele;
    }

    public void setOncoReferenceProteinAllele(String oncoReferenceProteinAllele) {
        this.oncoReferenceProteinAllele = oncoReferenceProteinAllele;
    }

    public String getOncoObservedProteinAllele() {
        return oncoObservedProteinAllele;
    }

    public void setOncoObservedProteinAllele(String oncoObservedProteinAllele) {
        this.oncoObservedProteinAllele = oncoObservedProteinAllele;
    }

    public String getOncoRefSeqMrnaId() {
        return oncoRefSeqMrnaId;
    }

    public void setOncoRefSeqMrnaId(String oncoRefSeqMrnaId) {
        this.oncoRefSeqMrnaId = oncoRefSeqMrnaId;
    }

    public String getOncoRefSeqProteinId() {
        return oncoRefSeqProteinId;
    }

    public void setOncoRefSeqProteinId(String oncoRefSeqProteinId) {
        this.oncoRefSeqProteinId = oncoRefSeqProteinId;
    }
}