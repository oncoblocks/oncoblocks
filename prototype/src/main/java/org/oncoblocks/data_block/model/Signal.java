package org.oncoblocks.data_block.model;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
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
	@Indexed private String geneticProfileKey;
	@Indexed private long entrezGeneId;
	private int signalType;
	
	private Map<String, Double> valueMap = new HashMap<String, Double>();
	
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

	public long getEntrezGeneId() {
		return entrezGeneId;
	}

	public void setEntrezGeneId(long entrezGeneId) {
		this.entrezGeneId = entrezGeneId;
	}

	public String getGeneticProfileKey() {
		return geneticProfileKey;
	}

	public void setGeneticProfileKey(String geneticProfileId) {
		this.geneticProfileKey = geneticProfileId;
	}

	public Map<String, Double> getValueMap() {
		return valueMap;
	}

	public void setValueMap(HashMap<String, Double> valueMap) {
		this.valueMap = valueMap;
	}

	public int getSignalType() {
		return signalType;
	}

	public void setSignalType(int signalType) {
		this.signalType = signalType;
	}
}