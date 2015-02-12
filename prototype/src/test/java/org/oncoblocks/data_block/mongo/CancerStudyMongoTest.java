package org.oncoblocks.data_block.mongo;

import junit.framework.TestCase;
import org.oncoblocks.data_block.model.CancerStudy;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Tests the Cancer Study Mongo Interface.
 */
public class CancerStudyMongoTest extends TestCase {

    public void testCancerStudyMongo() throws UnknownHostException {
        CancerStudy cancerStudy = new CancerStudy();
        cancerStudy.setCancerType("brca");
        cancerStudy.setKey("tcga_brca");
        cancerStudy.setNumCasesSequenced(316);
        cancerStudy.setProjectName("TCGA");
        cancerStudy.setPmid("123141");
        CancerStudyMongo cancerStudyMongo = new CancerStudyMongo();
        cancerStudyMongo.deleteAllRecords();
        cancerStudyMongo.addCancerStudy(cancerStudy);

        long numRecords = cancerStudyMongo.getNumCancerStudies();
        assertEquals(1, numRecords);

        ArrayList<CancerStudy> cancerStudyList = cancerStudyMongo.getAllCancerStudies();
        assertEquals(1, cancerStudyList.size());

        HashMap<String, CancerStudy> cancerStudyMap = cancerStudyMongo.getAllCancerStudiesMap();
        assertEquals(1, cancerStudyMap.size());

        cancerStudy = cancerStudyMongo.getCancerStudyByKey("tcga_brca");
        assertEquals("brca", cancerStudy.getCancerType());
    }
}