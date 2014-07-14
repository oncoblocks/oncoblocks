package org.oncoblocks.data_block.model;

/**
 * Encapsulates Details regarding a Single Mutation Record.
 */
public class Mutation {
    private static final String GERMLINE = "germline";
    private String cancerStudyKey;
    private String caseId;
    private long entrezGeneId;
    private String mutationStatus;
    private String validationStatus;
    private String chr;
    private long startPosition;
    private long endPosition;
    private String aminoAcidChange;
    private String variantClassification;
    private String uniprotEntryName;
    private String uniprotProteinPositionStart;
    private String uniprotProteinPositionEnd;
    private String uniprotReferenceProteinAllele;
    private String uniprotObservedProteinAllele;

    public Mutation() {
    }

    /**
     * Constructor.
     *
     * @param entrezGeneId     Entrez Gene ID.
     * @param validationStatus Validation Status,  e.g. Valid or Unknown.
     * @param mutationStatus   Mutation Status, e.g. Somatic or Germline.
     * @param mutationType     Mutation Type, e.g. Nonsense_Mutation, Frame_Shift_Del, etc.
     */
    public Mutation(long entrezGeneId, String validationStatus, String mutationStatus, String mutationType) {
        this.entrezGeneId = entrezGeneId;
        this.mutationStatus = mutationStatus;
        this.validationStatus = validationStatus;
        this.variantClassification = mutationType;
    }

    /**
     * Gets the Mutations Status, e.g. Somatic or Germline.
     *
     * @return mutation status, e.g. Somatic or Germline.
     */
    public String getMutationStatus() {
        return mutationStatus;
    }

    public String getKey() {
        return caseId + ":" + this.entrezGeneId + ":" + aminoAcidChange;
    }

    /**
     * Sets the Mutation Status, e.g. Somatic or Germline.
     *
     * @param mutationStatus mutation status, e.g. Somatic or Germline.
     */
    public void setMutationStatus(String mutationStatus) {
        this.mutationStatus = mutationStatus;
    }

    /**
     * Sets the Validation Status, e.g. Valid or Unknown.
     *
     * @param validationStatus validation status, e.g. Valid or Unknown.
     */
    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    /**
     * Gets the Validation Status, e.g. Valid or Unknown.
     *
     * @return validation status, e.g. Valid or Unknown.
     */
    public String getValidationStatus() {
        return validationStatus;
    }

    /**
     * Sets the Variant Classification, e.g. Nonsense_Mutation, Frame_Shift_Del, etc.
     *
     * @param variantClassification mutation type, e.g. Nonsense_Mutation, Frame_Shift_Del, etc.
     */
    public void setVariantClassification(String variantClassification) {
        this.variantClassification = variantClassification;
    }

    /**
     * Gets the Mutation Type, e.g. Nonsense_Mutation, Frame_Shift_Del, etc.
     *
     * @return mutation type, e.g. Nonsense_Mutation, Frame_Shift_Del, etc.
     */
    public String getVariantClassification() {
        return variantClassification;
    }

    public String getCancerStudyId() {
        return cancerStudyKey;
    }

    public void setCancerStudyKey(String cancerStudyKey) {
        this.cancerStudyKey = cancerStudyKey;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
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

    public String getAminoAcidChange() {
        return aminoAcidChange;
    }

    public void setAminoAcidChange(String aminoAcidChange) {
        this.aminoAcidChange = aminoAcidChange;
    }

    public int getAminoAcidPosition() {
        String aaChange = getAminoAcidChange();
        if (aaChange != null) {
            aaChange = aaChange.replace(".", "");

            //  Remove letters
            String aaPosition = aaChange.replaceAll("[^\\d.]", "");

            //  Then, try to parse the integer position
            try {
                return Integer.parseInt(aaPosition);
            } catch (NumberFormatException e) {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public void setEntrezId(long entrezGeneId) {
        this.entrezGeneId = entrezGeneId;
    }

    public long getEntrezGeneId() {
        return entrezGeneId;
    }

    public String getUniprotEntryName() {
        return uniprotEntryName;
    }

    public void setUniprotEntryName(String uniprotEntryName) {
        this.uniprotEntryName = uniprotEntryName;
    }

    public String getUniprotProteinPositionStart() {
        return uniprotProteinPositionStart;
    }

    public int getUniprotProteinAAPosition() {
        try {
            return Integer.parseInt(uniprotProteinPositionStart);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void setUniprotProteinPositionStart(String uniprotProteinPositionStart) {
        this.uniprotProteinPositionStart = uniprotProteinPositionStart;
    }

    public String getUniprotProteinPositionEnd() {
        return uniprotProteinPositionEnd;
    }

    public void setUniprotProteinPositionEnd(String uniprotProteinPositionEnd) {
        this.uniprotProteinPositionEnd = uniprotProteinPositionEnd;
    }

    public String getUniprotReferenceProteinAllele() {
        return uniprotReferenceProteinAllele;
    }

    public void setUniprotReferenceProteinAllele(String uniprotReferenceProteinAllele) {
        this.uniprotReferenceProteinAllele = uniprotReferenceProteinAllele;
    }

    public String getUniprotObservedProteinAllele() {
        return uniprotObservedProteinAllele;
    }

    public void setUniprotObservedProteinAllele(String uniprotObservedProteinAllele) {
        this.uniprotObservedProteinAllele = uniprotObservedProteinAllele;
    }
}