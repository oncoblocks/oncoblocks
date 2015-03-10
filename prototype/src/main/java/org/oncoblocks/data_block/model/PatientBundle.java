package org.oncoblocks.data_block.model;

import java.util.ArrayList;

/**
 * Encapsulates a "Patient Bundle".
 * This currently includes de-identified patient data, course of treament and 0 or more genomic profiles.
 * This model will evolve over time.
 */
public class PatientBundle {
	private Patient patient;
	private ArrayList<Treatment> treatmentList;
	private ArrayList<GenomicProfile> genomicProfileList;
	
	/**
	 * Gets the De-Identified Patient Data.
	 * @return de-identified patient data.
	 */
	public Patient getPatient() {
		return patient;
	}
	
	/**
	 * Sets the De-Identified Patient Data.
	 * @param patient de-identified patient data.
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	/**
	 * Gets the List of Treatments.
	 * @return list of treatments.
	 */
	public ArrayList<Treatment> getTreatmentList() {
		return treatmentList;
	}
	
	/**
	 * Sets the List of Treatments.
	 * @param treatmentList list of treatments.
	 */
	public void setTreatmentList(ArrayList<Treatment> treatmentList) {
		this.treatmentList = treatmentList;
	}
	
	/**
	 * Gets the List of Genomic Profiles.
	 * @return list of genomic profiles.
	 */
	public ArrayList<GenomicProfile> getGenomicProfileList() {
		return genomicProfileList;
	}
	
	/**
	 * Sest the List of Genomic Profiles.
	 * @param genomicProfileList list of genomic profiles.
	 */
	public void setGenomicProfileList(ArrayList<GenomicProfile> genomicProfileList) {
		this.genomicProfileList = genomicProfileList;
	}
}
