package org.oncoblocks.data_block.scripts;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.oncoblocks.data_block.model.CancerStudy;
import org.oncoblocks.data_block.mongo.CancerStudyMongo;

import java.io.*;
import java.net.UnknownHostException;
import java.util.HashSet;

/**
 * Command Line to Import Cancer Studies.
 */
public class ImportCancerStudies {
    private static final String TCGA = "TCGA";
    private static HashSet<String> tcgaCancerTypesToIgnore = new HashSet<String>();

    public static Namespace init (String[] args) {
        try {
            ArgumentParser parser = ArgumentParsers.newArgumentParser("importCancerStudies.sh")
                    .description("Import Cancer Studies from Tab Delim Text File.");
            parser.addArgument("cancer_studies")
                    .type(String.class)
                    .help("file containing cancer studies");
            parser.addArgument("--tcga")
                    .dest("tcga")
                    .action(Arguments.storeTrue())
                    .setDefault(Boolean.FALSE)
                    .help("indicates that the specified file contains TCGA cancer studies");
            return parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            System.out.println("Command line error:  " + e.getMessage() + ".");
            System.exit(1);
            return null;
        }
    }

    public static void importCancerStudies(File file, boolean tcgaFlag)
            throws IOException {
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            if (!line.startsWith("#") && line.trim().length() > 0 &&
                    !line.toUpperCase().startsWith("STUDY ABBREVIATION")) {
                String parts[] = line.split("\t");
                String cancerStudyNameKey = parts[0];
                String cancerType = parts[1];
                String projectName = null;
                String pmid = null;
                if (tcgaFlag) {
                    cancerStudyNameKey = cancerStudyNameKey + "_" + TCGA;
                    projectName = TCGA;
                } else {
                    try {
                        projectName = parts[2];
                        pmid = parts[3];
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                processCancerStudy(cancerStudyNameKey, cancerType, projectName, pmid);
            }
            line = bufferedReader.readLine();
        }
    }

    private static void processCancerStudy(String cancerStudyNameKey, String cancerType,
        String projectName, String pmid) throws UnknownHostException {
        addNewCancerStudy(cancerStudyNameKey, cancerType, projectName, pmid);
    }

    private static void addNewCancerStudy (String cancerStudyNameKey, String cancerType, String projectName,
        String pmid) throws UnknownHostException {
        if (tcgaCancerTypesToIgnore.contains(cancerStudyNameKey)) {
            return;
        }
        System.out.print(String.format("Cancer study:  %-20s" , cancerStudyNameKey));
        System.out.println(String.format("%-20s" , "new --> Adding to database."));
        CancerStudy cancerStudy = new CancerStudy(cancerStudyNameKey);
        cancerStudy.setCancerType(cancerType);
        cancerStudy.setProjectName(projectName);
        cancerStudy.setPmid(pmid);
        CancerStudyMongo cancerStudyMongo = new CancerStudyMongo();
        cancerStudyMongo.addCancerStudy(cancerStudy);
    }

    /**
     * Main Method.
     *
     * @param args Command Line Arguments.
     */
    public static void main(String[] args) throws IOException {
        Namespace res = init(args);
        tcgaCancerTypesToIgnore.add("CNTL_TCGA"); // Ignore TCGA Controls
        tcgaCancerTypesToIgnore.add("MISC_TCGA");  // Ignore TCGA Misc
        File file = new File(res.getString("cancer_studies"));
        boolean tcgaFlag =  res.getBoolean("tcga");

        System.out.println("Importing / Update Cancer Studies");
        System.out.println("File:  " + file.getAbsolutePath());
        System.out.println("TCGA Flag:  " + tcgaFlag);
        importCancerStudies(file, tcgaFlag);
    }
}
