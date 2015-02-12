package org.oncoblocks.data_block.mongo;

import junit.framework.TestCase;
import org.oncoblocks.data_block.model.Gene;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Tests the Gene Mongo Interface.
 */
public class GeneMongoTest extends TestCase {

    public void testGeneMongo() throws UnknownHostException {
        Gene gene = new Gene();
        gene.setStandardGeneSymbol("BRCA1");
        gene.setEntrezGeneId(675);
        Set<String> aliasSet = new HashSet<String>();
        aliasSet.add("blah1");
        aliasSet.add("blah2");
        gene.setAliases(aliasSet);

        GeneMongo geneMongo = new GeneMongo();
        geneMongo.deleteAllRecords();
        geneMongo.addGene(gene);

        assertEquals(1, geneMongo.getNumGenes());

        ArrayList<Gene> geneList = geneMongo.getAllGenes();
        assertEquals(1, geneList.size());
        Gene gene0 = geneList.get(0);
        assertEquals("BRCA1", gene0.getStandardGeneSymbol());
        assertEquals(675, gene0.getEntrezGeneId());
        Set<String> aliasSet0 = gene0.getAliases();
        assertEquals(2, aliasSet0.size());
        assertTrue(aliasSet0.contains("blah1"));
        assertTrue(aliasSet0.contains("blah2"));

        geneList = geneMongo.getGeneBySymbol("BRCA1");
        assertEquals(1, geneList.size());

        geneList = geneMongo.getGeneBySymbol("BRCA2");
        assertEquals(0, geneList.size());

        geneList = geneMongo.getGeneByEntrezId(675L);
        assertEquals(1, geneList.size());

        geneList = geneMongo.getGeneByEntrezId(677L);
        assertEquals(0, geneList.size());

        geneList = geneMongo.getGeneByAlias("blah1");
        assertEquals(1, geneList.size());

        geneList = geneMongo.getGeneByAlias("blah2");
        assertEquals(1, geneList.size());

        geneList = geneMongo.getGeneByAlias("blah3");
        assertEquals(0, geneList.size());
    }
}