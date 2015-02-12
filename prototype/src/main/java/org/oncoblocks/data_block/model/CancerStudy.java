package org.oncoblocks.data_block.model;

/**
 * Encapsulates a Single Cancer Study.
 */
public class CancerStudy {
    private String key;
    private String cancerType;
    private String projectName;
    private String pmid;
    private int numCasesSequenced;

    /**
     * No Arg Constructor.
     */
    public CancerStudy() {
    }

    /**
     * Constructor.
     *
     * @param key Cancer Study Key.
     */
    public CancerStudy(String key) {
        super();
        this.key = key.toUpperCase();
    }

    /**
     * Gets the Cancer Study Key.
     *
     * @return cancer study key.
     */
    public String getKey() {
        return key.toUpperCase();
    }

    /**
     * Sets the Cancer Study Name Key.
     *
     * @param key cancer study key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    public int getNumCasesSequenced() {
        return numCasesSequenced;
    }

    public void setNumCasesSequenced(int numCasesSequenced) {
        this.numCasesSequenced = numCasesSequenced;
    }

    public String getCancerType() {
        return cancerType;
    }

    public void setCancerType(String cancerType) {
        this.cancerType = cancerType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getLabel() {
        if (cancerType != null && projectName != null) {
            return this.getCancerType() + ":  " + this.getProjectName();
        } else {
            return this.key;
        }
    }

    /**
     * toString() Override.
     *
     * @return string summary of cancer study.
     */
    @Override
    public String toString() {
        return "CancerStudy [key=" + key + ", cancer_type=" + cancerType + "]";
    }
}