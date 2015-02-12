package org.oncoblocks.data_block.scripts;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.oncoblocks.data_block.model.Gene;
import org.oncoblocks.data_block.mongo.GeneMongo;
import org.oncoblocks.data_block.util.ConsoleUtil;
import org.oncoblocks.data_block.util.FileUtil;
import org.oncoblocks.data_block.util.ProgressMonitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Command Line Tool to Import Background Gene Data.
 */
public class ImportGeneData {
    private GeneMongo geneMongo;
    private ProgressMonitor pMonitor;
    private File geneFile;

    public ImportGeneData(File geneFile, GeneMongo geneMongo, ProgressMonitor pMonitor) {
        this.geneFile = geneFile;
        this.geneMongo = geneMongo;
        this.pMonitor = pMonitor;
    }

    public void importData() throws IOException {
        FileReader reader = new FileReader(geneFile);
        BufferedReader buf = new BufferedReader(reader);
        String line = buf.readLine();
        int numGenesAdded = 0;
        while (line != null) {
            if (!line.startsWith("#")) {
                String parts[] = line.split("\t");
                int entrezGeneId = Integer.parseInt(parts[1]);
                String geneSymbol = parts[4];
                if (geneSymbol.equals("-")) {
                    geneSymbol = null;
                }
                Set<String> aliases = new HashSet<String>();
                if (!parts[3].equals("-")) {
                    aliases.addAll(Arrays.asList(parts[3].split("\\|")));
                }
                String entrezGeneSymbol = parts[2];
                if (!entrezGeneSymbol.equals("-")) {
                    aliases.add(entrezGeneSymbol);
                }
                Gene gene = new Gene();
                gene.setEntrezGeneId(entrezGeneId);
                gene.setStandardGeneSymbol(geneSymbol);
                gene.setAliases(aliases);
                geneMongo.addGene(gene);
                numGenesAdded++;
            }
            line = buf.readLine();
        }
        System.out.println("Number of genes added:  " + numGenesAdded);
        System.out.println("Verifying database...");
        System.out.println("Number of genes verified:  " + geneMongo.getNumGenes());
    }

    public static void main(String[] args) throws Exception {
        MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("cgds");
        GeneMongo geneMongo = new GeneMongo();
        geneMongo.deleteAllRecords();
        if (args.length == 0) {
            System.out.println("command line usage:  importGenes.sh <ncbi_genes.txt>");
            System.exit(-1);
        }
        ProgressMonitor pMonitor = new ProgressMonitor();
        pMonitor.setConsoleMode(true);

        File geneFile = new File(args[0]);
        System.out.println("[BEGIN] Reading gene data from:  " + geneFile.getAbsolutePath());
        int numLines = FileUtil.getNumLines(geneFile);
        System.out.println(" --> total number of lines:  " + numLines);
        pMonitor.setMaxValue(numLines);
        ImportGeneData parser = new ImportGeneData(geneFile, geneMongo, pMonitor);
        parser.importData();
        ConsoleUtil.showWarnings(pMonitor);
    }
}