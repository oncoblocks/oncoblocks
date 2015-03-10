package org.oncoblocks.data_block.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Encapsulates information regarding a Single Genomic Profile
 *
 */
public class GenomicProfile {
	private Date dateOfBiopsy;
	private String tumorType;
	private String tissueType;
	private ArrayList<Mutation> mutationList = new ArrayList<Mutation>();
	
	/**
	 * Gets the date of biopsy.
	 * @return date of biopsy.
	 */
	public Date getDateOfBiopsy() {
		return dateOfBiopsy;
	}
	
	/**
	 * Sets the date of biopsy.
	 * @param dateOfBiopsy date of biopsy.
	 */
	public void setDateOfBiopsy(Date dateOfBiopsy) {
		this.dateOfBiopsy = dateOfBiopsy;
	}
	
	/**
	 * Gets the tumor type, e.g. primary or metastatic.
	 * @return tumor type, e.g. primary or metastatic.
	 */
	public String getTumorType() {
		return tumorType;
	}
	
	/**
	 * Sets the tumor type, e.g. primary or metastatic.
	 * @param tumorType tumor type, e.g. primary or metastatic.
	 */
	public void setTumorType(String tumorType) {
		this.tumorType = tumorType;
	}
	
	/**
	 * Gets the tissue type, e.g. breast, lung, etc.
	 * @return tissue type, e.g. breast, lung, etc.
	 */
	public String getTissueType() {
		return tissueType;
	}
	
	/**
	 * Sets the tissue type, e.g. breast, lung, etc.
	 * @param tissueType tissue type, e.g. breast, lung, etc.
	 */
	public void setTissueType(String tissueType) {
		this.tissueType = tissueType;
	}
	
	/**
	 * Gets the List of Mutations Identified.
	 * @return List of Mutations.
	 */
	public ArrayList<Mutation> getMutationList() {
		return mutationList;
	}
	
	/**
	 * Sets the List of Mutations Identified.
	 * @param mutationList List of Mutations.
	 */
	public void setMutationList(ArrayList<Mutation> mutationList) {
		this.mutationList = mutationList;
	}
}
