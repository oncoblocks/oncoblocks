package org.oncoblocks.data_block.model;

import java.util.Date;

/**
 * Encapsulates information regarding a single treatment, given to a single patient.
 * This object will evolve over time to include additional details.
 *
 */
public class Treatment {
	private String treatmentName;
	private String treatmentDescription;
	private Date startDate;
	private Date endDate;
	
	/**
	 * Gets the treatment name.
	 * @return treatment name.
	 */
	public String getTreatmentName() {
		return treatmentName;
	}
	
	/**
	 * Sets the treatment name.
	 * @param treatmentName treatment name.
	 */
	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}
	
	/**
	 * Gets the treatment description.
	 * @return treatment description.
	 */
	public String getTreatmentDescription() {
		return treatmentDescription;
	}
	
	/**
	 * Sets the treatment description.
	 * @param treatmentDescription
	 */
	public void setTreatmentDescription(String treatmentDescription) {
		this.treatmentDescription = treatmentDescription;
	}
	
	/**
	 * Gets the start date of treatment.
	 * @return start date of treatment.
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the start date of treatment.
	 * @param startDate start data of treatment.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Gets the end date of treatment.
	 * @return end date of treatment.
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the end data of treatment.
	 * @param endDate end date of treatment.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
