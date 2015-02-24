package org.oncoblocks.data_block.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Encapsulates Details regarding a "Signal" event, e.g. mRNA expression
 * or copy number alteration.
 */
@Entity("signals")
public class Signal {
	@Id private ObjectId id;
	@Indexed private String cancerStudyKey;
	@Indexed private String caseId;
	@Indexed private long entrezGeneId;
	private int signalType;
	private double value;
	
    public Signal() {
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getSignalType() {
		return signalType;
	}

	public void setSignalType(int signalType) {
		this.signalType = signalType;
	}
}