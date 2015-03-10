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
            signal.setEntrezGeneId(i);
            signalMongo.addSignal(signal);;
        }

        long numRecords = signalMongo.getNumSignalRecords();
        assertEquals(100, numRecords);

        ArrayList<Signal> signalList = signalMongo.getSignalsByEntrezId(10L);
        assertEquals(1, signalList.size());
    }
}