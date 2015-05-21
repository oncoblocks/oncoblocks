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

        Gson gson = new Gson();
        String json = gson.toJson(gene);
        assertEquals("{\"entrezGeneId\":675,\"standardGeneSymbol\":\"BRCA1\"}",
                json);
    }
}