package org.oncoblocks.data_block.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Misc Utility Methods for Console Applications.
 *
 */
public class ConsoleUtil {
    private static String msg = "";

    /**
     * Outputs Progress Messages to Console.
     *
     * @param pMonitor ProgressMonitor Object.
     */
    public static synchronized void showProgress(ProgressMonitor pMonitor) {
        if (pMonitor.isConsoleMode()) {
            int currentValue = pMonitor.getCurValue();
            if (currentValue % 100 == 0) {
                System.err.print(".");
            }

            if (currentValue % 1000 == 0) {
                NumberFormat format = DecimalFormat.getPercentInstance();
                double percent = pMonitor.getPercentComplete();
                msg = new String("Percentage Complete:  "
                        + format.format(percent));
                System.err.println("\n" + msg);
                Runtime rt = Runtime.getRuntime();
                long used = rt.totalMemory() - rt.freeMemory();
                System.err.println("Mem Allocated:  " + getMegabytes(rt.totalMemory())
                        + ", Mem used:  " + getMegabytes(used) + ", Mem free:  "
                        + getMegabytes(rt.freeMemory()));
            }
            if (currentValue == pMonitor.getMaxValue()) {
                System.err.println();
            }
        }
    }

    public static void showWarnings(ProgressMonitor pMonitor) {
        ArrayList warningList = pMonitor.getWarnings();
        if (warningList.size() > 0) {
            System.err.println("Warnings / Errors:");
            System.err.println("-------------------");
            for (int i = 0; i < warningList.size(); i++) {
                System.err.println(i + ".  " + warningList.get(i));
            }
        }
    }

    public static void writeToConsole(boolean outputToConsole, String msg) {
        if (outputToConsole) {
            System.out.println(msg);
        }
    }

    private static String getMegabytes(long bytes) {
        double mBytes = (bytes / 1024.0) / 1024.0;
        DecimalFormat formatter = new DecimalFormat("#,###,###.###");
        return formatter.format(mBytes) + " MB";
    }
}