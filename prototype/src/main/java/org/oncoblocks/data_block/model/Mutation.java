package org.oncoblocks.data_block.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Encapsulates Details regarding a Single Mutation Record.
 */
@Entity("mutations")
public class Mutation {
	@Id private ObjectId id;
	@Indexed private String cancerStudyKey;
	@Indexed private String caseId;
	@Indexed private long entrezGeneId;
	private String geneSymbol;
	private String referenceGenome;
	private String chromosome;
	private Integer dnaStartPosition;
	private Integer dnaEndPosition;
	private String strand;
	private String variantClassification;
	private String variantType;
	private String referenceAllele;
	private String variantAllele;
	private Integer alternativeAlleleReads;
	private Integer referenceAlleleReads;
	private String dbSnpRsId;
	private String dbSnpRsValStatus;
	private String annotationTranscript;
	private String transcriptStrand;
	private String cDnaChange;
	private String codonChange;
	private String aaChange;
	private String otherTranscript;
	private String refseqMrnaId;
	private String refseqProtId;
	private String swissprotAccession;
	private String swissprotEntry;
	private String uniprotAaPosition;
	private String uniprotRegion;
	private String uniprotSite;
	private String vertebrateAaAlignment;
	private String tier;
	private String annotationNotes;

    public Mutation() {
    }

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getCancerStudyKey() {
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

	public long getEntrezGeneId() {
		return entrezGeneId;
	}

	public void setEntrezGeneId(long entrezGeneId) {
		this.entrezGeneId = entrezGeneId;
	}

	public String getGeneSymbol() {
		return geneSymbol;
	}

	public void setGeneSymbol(String geneSymbol) {
		this.geneSymbol = geneSymbol;
	}

	public String getReferenceGenome() {
		return referenceGenome;
	}

	public void setReferenceGenome(String referenceGenome) {
		this.referenceGenome = referenceGenome;
	}

	public String getChromosome() {
		return chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public Integer getDnaStartPosition() {
		return dnaStartPosition;
	}

	public void setDnaStartPosition(Integer dnaStartPosition) {
		this.dnaStartPosition = dnaStartPosition;
	}

	public Integer getDnaEndPosition() {
		return dnaEndPosition;
	}

	public void setDnaEndPosition(Integer dnaEndPosition) {
		this.dnaEndPosition = dnaEndPosition;
	}

	public String getStrand() {
		return strand;
	}

	public void setStrand(String strand) {
		this.strand = strand;
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

	public String getReferenceAllele() {
		return referenceAllele;
	}

	public void setReferenceAllele(String referenceAllele) {
		this.referenceAllele = referenceAllele;
	}

	public String getVariantAllele() {
		return variantAllele;
	}

	public void setVariantAllele(String variantAllele) {
		this.variantAllele = variantAllele;
	}

	public Integer getAlternativeAlleleReads() {
		return alternativeAlleleReads;
	}

	public void setAlternativeAlleleReads(Integer alternativeAlleleReads) {
		this.alternativeAlleleReads = alternativeAlleleReads;
	}

	public Integer getReferenceAlleleReads() {
		return referenceAlleleReads;
	}

	public void setReferenceAlleleReads(Integer referenceAlleleReads) {
		this.referenceAlleleReads = referenceAlleleReads;
	}

	public String getDbSnpRsId() {
		return dbSnpRsId;
	}

	public void setDbSnpRsId(String dbSnpRsId) {
		this.dbSnpRsId = dbSnpRsId;
	}

	public String getDbSnpRsValStatus() {
		return dbSnpRsValStatus;
	}

	public void setDbSnpRsValStatus(String dbSnpRsValStatus) {
		this.dbSnpRsValStatus = dbSnpRsValStatus;
	}

	public String getAnnotationTranscript() {
		return annotationTranscript;
	}

	public void setAnnotationTranscript(String annotationTranscript) {
		this.annotationTranscript = annotationTranscript;
	}

	public String getTranscriptStrand() {
		return transcriptStrand;
	}

	public void setTranscriptStrand(String transcriptStrand) {
		this.transcriptStrand = transcriptStrand;
	}

	public String getcDnaChange() {
		return cDnaChange;
	}

	public void setcDnaChange(String cDnaChange) {
		this.cDnaChange = cDnaChange;
	}

	public String getCodonChange() {
		return codonChange;
	}

	public void setCodonChange(String codonChange) {
		this.codonChange = codonChange;
	}

	public String getAaChange() {
		return aaChange;
	}

	public void setAaChange(String aaChange) {
		this.aaChange = aaChange;
	}

	public String getOtherTranscript() {
		return otherTranscript;
	}

	public void setOtherTranscript(String otherTranscript) {
		this.otherTranscript = otherTranscript;
	}

	public String getRefseqMrnaId() {
		return refseqMrnaId;
	}

	public void setRefseqMrnaId(String refseqMrnaId) {
		this.refseqMrnaId = refseqMrnaId;
	}

	public String getRefseqProtId() {
		return refseqProtId;
	}

	public void setRefseqProtId(String refseqProtId) {
		this.refseqProtId = refseqProtId;
	}

	public String getSwissprotAccession() {
		return swissprotAccession;
	}

	public void setSwissprotAccession(String swissprotAccession) {
		this.swissprotAccession = swissprotAccession;
	}

	public String getSwissprotEntry() {
		return swissprotEntry;
	}

	public void setSwissprotEntry(String swissprotEntry) {
		this.swissprotEntry = swissprotEntry;
	}

	public String getUniprotAaPosition() {
		return uniprotAaPosition;
	}

	public void setUniprotAaPosition(String uniprotAaPosition) {
		this.uniprotAaPosition = uniprotAaPosition;
	}

	public String getUniprotRegion() {
		return uniprotRegion;
	}

	public void setUniprotRegion(String uniprotRegion) {
		this.uniprotRegion = uniprotRegion;
	}

	public String getUniprotSite() {
		return uniprotSite;
	}

	public void setUniprotSite(String uniprotSite) {
		this.uniprotSite = uniprotSite;
	}

	public String getVertebrateAaAlignment() {
		return vertebrateAaAlignment;
	}

	public void setVertebrateAaAlignment(String vertebrateAaAlignment) {
		this.vertebrateAaAlignment = vertebrateAaAlignment;
	}

	/**
	 * Gets the Tier of this mutation.
	 * Tier 1 = Clinically actionable.
	 * Tier 4 = Variant of Unknown Significance.
	 * @return annotation tier.
	 */
	public String getTier() {
		return tier;
	}

	/**
	 * Sets the Tier of this mutation.
	 * Tier 1 = Clinically actionable.
	 * Tier 4 = Variant of Unknown Significance.
	 * @param tier annotation tier.
	 */
	public void setTier(String tier) {
		this.tier = tier;
	}

	/**
	 * Gets Annotation Notes.
	 */
	public String getAnnotationNotes() {
		return annotationNotes;
	}

	/**
	 * Sets Annotation Notes.
	 * @param annotationNotes
	 */
	public void setAnnotationNotes(String annotationNotes) {
		this.annotationNotes = annotationNotes;
	}
	
}