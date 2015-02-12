package org.oncoblocks.data_block.scripts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.oncoblocks.data_block.model.Mutation;
import org.oncoblocks.data_block.mongo.GeneMongo;
import org.oncoblocks.data_block.mongo.MutationMongo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Command Line Tool to Execute Sample Queries.
 */
public class ExecuteQuery {

    public static void main(String args[]) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage:  executeQuery.sh <query_name> <id>");
        } else {
            Date start = new Date();
            String queryName = args[0];
            String id = args[1];
            Date createConnection = null;
            MutationMongo mutationMongo = new MutationMongo();
            createConnection = new Date();
            if (queryName.equalsIgnoreCase("get_mutations")) {
                try {
                    Long entrezGeneId = Long.parseLong(id);
                    long interval = createConnection.getTime() - start.getTime();
                    System.out.println("Total time to create MongoDB Connection:  " + interval + " ms");
                    ArrayList<Mutation> mutationList =
                        mutationMongo.getMutationsByEntrezId(entrezGeneId);
                    System.out.println("Total number of hits:  " + mutationList.size());
                    //writeJson(mutationList);
                } catch (NumberFormatException e) {
                    ArrayList<Mutation> mutationList = mutationMongo.getMutationsByCaseIdId(id);
                    System.out.println("Total number of hits:  " + mutationList.size());
                    //writeJson(mutationList);
                }
            } else {
                System.out.println("Invalid query name:  " + queryName);
                System.exit(1);
            }
            Date stop = new Date();
            long interval = stop.getTime() - createConnection.getTime();
            System.out.println("Total time to execute query:  " + interval + " ms");
        }
    }

    private static void writeJson(Object obj) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(obj);
        System.out.println(json);
    }

}
