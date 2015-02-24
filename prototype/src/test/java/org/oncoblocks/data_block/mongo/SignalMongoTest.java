package org.oncoblocks.data_block.mongo;

import junit.framework.TestCase;

import org.oncoblocks.data_block.model.Mutation;
import org.oncoblocks.data_block.model.Signal;

import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Tests the Signal Mongo Interface.
 */
public class SignalMongoTest extends TestCase {

    public void testMutationMongo() throws UnknownHostException {
        SignalMongo signalMongo = new SignalMongo();
        signalMongo.deleteAllRecords();
        for (int i=0; i<100; i++) {
            Signal signal = new Signal();
            signal.setCaseId("123XYZ");
            signal.setEntrezGeneId(i);
            signal.setValue(i);
            signalMongo.addSignal(signal);;
        }

        long numRecords = signalMongo.getNumSignalRecords();
        assertEquals(100, numRecords);

        ArrayList<Signal> signalList = signalMongo.getMutationsByCaseIdId("123XYZ");
        assertEquals(100, signalList.size());
    }
}