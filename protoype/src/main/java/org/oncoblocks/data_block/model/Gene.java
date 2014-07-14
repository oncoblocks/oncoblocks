package org.oncoblocks.data_block.model;

import java.util.*;

/**
 * Class to encapsulate a Gene.
 */
public class Gene {
    private long entrezGeneId;
    private String standardGeneSymbol;
    private Set<String> aliases;
    private static final String NA = "N/A";

    public long getEntrezGeneId() {
        return entrezGeneId;
    }

    public void setEntrezGeneId(long entrezGeneId) {
        this.entrezGeneId = entrezGeneId;
    }

    public String getStandardGeneSymbol() {
        return standardGeneSymbol;
    }

    public void setStandardGeneSymbol(String standardGeneSymbol) {
        this.standardGeneSymbol = standardGeneSymbol;
    }

    public Set<String> getAliases() {
        if (aliases == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(aliases);
    }

    public void setAliases(Set<String> aliases) {
        Map<String, String> map = new HashMap<String, String>(aliases.size());
        for (String alias : aliases) {
            map.put(alias.toUpperCase(), alias);
        }

        this.aliases = new HashSet<String>(map.values());
    }

    @Override
    public int hashCode() {
        return (int) entrezGeneId;
    }
}