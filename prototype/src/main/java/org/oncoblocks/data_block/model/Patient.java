package org.oncoblocks.data_block.model;

/**
 * Encapsulates information regarding a single patient.
 * This class will evolve over time.
 *
 */
public class Patient {
	private String patientId;
	private int age;
	private String cancerType;
	private String histologicalSubtype;
	private String tumorStage;
	private String tumorGrade;
	
	/**
	 * Gets the de-identified Patient ID.
	 * @return de-identified patient ID.
	 */
	public String getPatientId() {
		return patientId;
	}
	
	/**
	 * Sets the de-identified Patient ID.
	 * @param patientId de-identified Patient ID.
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	/**
	 * Gets age in years.
	 * @return age in years.
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Sets age in years.
	 * @param age age in years.
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * Gets the cancer type, e.g. lung, breast, colorectal.
	 * @return cancer type.
	 */
	public String getCancerType() {
		return cancerType;
	}
	
	/**
	 * Sets the cancer type, e.g. lung, breast, colorectal.
	 * @param cancerType cancer type.
	 */
	public void setCancerType(String cancerType) {
		this.cancerType = cancerType;
	}
	
	/**
	 * Gets the histological subtype, e.g. serous, mucious, endometrial, clear-cell.
	 * @return histological subtype. 
	 */
	public String getHistologicalSubtype() {
		return histologicalSubtype;
	}
	
	/**
	 * Sets the histological subtype, e.g. serous, mucious, endometrial, clear-cell.
	 * @param histologicalSubtype histological subtype.
	 */
	public void setHistologicalSubtype(String histologicalSubtype) {
		this.histologicalSubtype = histologicalSubtype;
	}

	/**
	 * Gets the tumor stage, e.g. Stage III.
	 * @return tumor stage.
	 */
	public String getTumorStage() {
		return tumorStage;
	}
	
	/**
	 * Sets the tumor stage, e.g. Stage III.
	 * @param tumorStage tumor stage.
	 */
	public void setTumorStage(String tumorStage) {
		this.tumorStage = tumorStage;
	}
	
	/**
	 * Gets the tumor grade, e.g. Grade 2.
	 * @return tumor grade.
	 */
	public String getTumorGrade() {
		return tumorGrade;
	}
	
	/**
	 * Sets the tumor grade, e.g. Grade 2.
	 * @param tumorGrade tumor grade.
	 */
	public void setTumorGrade(String tumorGrade) {
		this.tumorGrade = tumorGrade;
	}
}