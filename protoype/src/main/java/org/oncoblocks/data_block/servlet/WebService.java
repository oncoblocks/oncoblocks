package org.oncoblocks.data_block.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.oncoblocks.data_block.model.CancerStudy;
import org.oncoblocks.data_block.model.Gene;
import org.oncoblocks.data_block.model.Mutation;
import org.oncoblocks.data_block.mongo.CancerStudyMongo;
import org.oncoblocks.data_block.mongo.GeneMongo;
import org.oncoblocks.data_block.mongo.MutationMongo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Main Web Service.
 */
public class WebService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        String query = req.getParameter("query");
        if (query.equalsIgnoreCase("get_gene")) {
            String geneSymbol = req.getParameter("gene_symbol");
            GeneMongo geneMongo = new GeneMongo();
            ArrayList<Gene> geneList = geneMongo.getGeneBySymbol(geneSymbol);
            writeJson(resp, geneList);
        } else if (query.equalsIgnoreCase("get_cancer_studies")) {
            CancerStudyMongo cancerStudyMongo = new CancerStudyMongo();
            ArrayList<CancerStudy> cancerStudyList = cancerStudyMongo.getAllCancerStudies();
            writeJson(resp, cancerStudyList);
        } else if (query.equalsIgnoreCase("get_mutations")) {
            String entrezId = req.getParameter("entrez_id");
            String geneSymbol = req.getParameter("gene_symbol");
            if (entrezId != null && entrezId.length() > 0) {
                Long entrezIdLong = Long.parseLong(req.getParameter("entrez_id"));
                MutationMongo mutationMongo = new MutationMongo();
                ArrayList<Mutation> mutationList = mutationMongo.getMutationsByEntrezId(entrezIdLong);
                writeJson(resp, mutationList);
            } else if (geneSymbol != null) {
                System.out.println("Got gene symbol:  " + geneSymbol);
                GeneMongo geneMongo = new GeneMongo();
                ArrayList<Gene> geneList = geneMongo.getGeneBySymbol(geneSymbol);
                System.out.println("Got gene list:  " + geneList.size());
                if (geneList != null && geneList.size() > 0) {
                    Gene gene = geneList.get(0);
                    System.out.println("Got gene:  " + gene);
                    MutationMongo mutationMongo = new MutationMongo();
                    ArrayList<Mutation> mutationList = mutationMongo.getMutationsByEntrezId(gene.getEntrezGeneId());
                    writeJson(resp, mutationList);
                }
            } else {
                String caseId = req.getParameter("case_id");
                MutationMongo mutationMongo = new MutationMongo();
                ArrayList<Mutation> mutationList = mutationMongo.getMutationsByCaseIdId(caseId);
                writeJson(resp, mutationList);
            }
        }
    }

    private void writeJson(HttpServletResponse resp, Object obj) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(obj);
        PrintWriter writer = resp.getWriter();
        writer.write(json);
    }
}
