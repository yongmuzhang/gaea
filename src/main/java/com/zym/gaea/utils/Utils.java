package com.zym.gaea.utils;

import java.io.*;

/**
 * @author master
 */
public class Utils {

    public static String directoryJoin(String origin, String join) {
        StringBuilder sb = new StringBuilder(origin);
        if (!origin.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        sb.append(join);
        return sb.toString();
    }

    public static void copyPackage(File src, File dest, String basePackageName) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String[] files = src.list();
            for (String file : files) {
                String nextPackageName = basePackageName;
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                if (srcFile.isDirectory()) {
                    nextPackageName = nextPackageName + "." + file;
                }
                copyPackage(srcFile, destFile, nextPackageName);
            }
        } else {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dest));
            String lineStr;
            while((lineStr = bufferedReader.readLine()) != null) {
                lineStr = lineStr.replace("#{package}", basePackageName);
                bufferedWriter.write(lineStr);
                bufferedWriter.write("\n");
            }
            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
