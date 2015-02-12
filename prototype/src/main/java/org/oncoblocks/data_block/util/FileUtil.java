package org.oncoblocks.data_block.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * File Utilities.
 */
public class FileUtil {

    /**
     * Gets content from the specified file and stores to an array list of strings.
     *
     * @param file File.
     * @return ArrayList of String objects.
     * @throws IOException IO Error.
     */
    public static ArrayList<String> getContent(File file)
            throws IOException {
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        ArrayList<String> contentList = new ArrayList<String>();
        while (line != null) {
            contentList.add(line);
            line = bufferedReader.readLine();
        }
        return contentList;
    }

    /**
     * Gets Number of Lines in Specified File.
     *
     * @param file File.
     * @return number of lines.
     * @throws java.io.IOException Error Reading File.
     */
    public static int getNumLines(File file) throws IOException {
        int numLines = 0;
        FileReader reader = new FileReader(file);
        BufferedReader buffered = new BufferedReader(reader);
        String line = buffered.readLine();
        while (line != null) {
            if (!line.startsWith("#") && line.trim().length() > 0) {
                numLines++;
            }
            line = buffered.readLine();
        }
        reader.close();
        return numLines;
    }

    /**
     * If the specified file exists, delete it.
     *
     * @param file File to delete.
     */
    public static void conditionallyDeleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }
}
