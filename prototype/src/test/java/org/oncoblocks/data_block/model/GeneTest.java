package org.oncoblocks.data_block.model;

import com.google.gson.Gson;
import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

/**
 * Tests the Gene Model and JSON Representation.
 */
public class GeneTest extends TestCase {

    public void testGeneModel() {
        Gene gene = new Gene();
        gene.setStandardGeneSymbol("BRCA1");
        gene.setEntrezGeneId(675);
        Set<String> aliasSet = new HashSet<String>();
        aliasSet.add("blah1");
        aliasSet.add("blah2");
        gene.setAliases(aliasSet);

        Gson gson = new Gson();
        String json = gson.toJson(gene);
        assertEquals("{\"entrezGeneId\":675,\"standardGeneSymbol\":\"BRCA1\",\"aliases\":[\"blah1\",\"blah2\"]}",
                json);
    }
}