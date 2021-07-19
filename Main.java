package analyzer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.System;

public class Main {
    public static void main(String[] args) {
        String FILE_NAME = args[0];
        String pattern = args[1];
        Manager manager = new Manager(FileManager.getCodes(pattern), FILE_NAME);
        manager.execute();
//        FileChecker checker = new FileChecker(codes, FileManager.getFileNames(FILE_NAME));
    }
}
//        int BUFFER_SIZE = 1024;
//        byte[] buffer = new byte[BUFFER_SIZE];
//        HashMap<String,String> dictionary = new HashMap<>();
//
//        dictionary.put(pattern,patternName);
//        int flag = 0;
//        double timeRequired = 0;
//        try (InputStream in = new FileInputStream(FILE_NAME)) {
//            while (in.read(buffer) != -1){
//                String convertedString = getStringValue(buffer);
//
//                long startTime = System.nanoTime();
//                boolean found = isKMP ? kmp(pattern, convertedString) : matchPattern(pattern, convertedString);
//                long stopTime = System.nanoTime();
//                timeRequired = (stopTime - startTime * 1.0) / 1000000000;
//                if(found){
//                    flag = 1;
//                    System.out.println(dictionary.get(pattern));
//                    break;
//                }
//            }
//            if (flag == 0){
//                System.out.println("Unknown file type");
//            }
//            System.out.printf("It took %f seconds", timeRequired);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static boolean kmp(String pattern, String convertedString) {
//        int[] prefix = prefixOf(pattern);
//        ArrayList<Integer> occurences = new ArrayList<>();
//        int j = 0;
//
//        for (int i = 1; i < convertedString.length(); i++) {
//
//            while (j > 0 && convertedString.charAt(i) != pattern.charAt(j)){
//                j = prefix[j - 1];
//            }
//
//            if (convertedString.charAt(i) == pattern.charAt(j)) {
//                j += 1;
//            }
//
//            if (j == pattern.length()){
//                occurences.add(i - j + 1);
//                j = prefix[j - 1];
//            }
//        }
//        return !occurences.isEmpty();
//    }
//
//    private static int[] prefixOf(String pattern) {
//        int[] prefix = new int[pattern.length()];
//
//        for (int i = 1; i < pattern.length(); i++) {
//            int j = prefix[i - 1];
//
//            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)){
//                j = prefix[j - 1];
//            }
//
//            if (pattern.charAt(i) == pattern.charAt(j)){
//                j += 1;
//            }
//
//            prefix[i] = j;
//        }
//        return prefix;
//    }
//
//    private static boolean matchPattern(String pattern, String value) {
//        return value.contains(pattern);
//    }
//
//    private static String getStringValue(byte[] buffer) {
//        StringBuilder str = new StringBuilder();
//        for(byte each : buffer){
//            str.append((char) each);
//        }
//        return str.toString();
//    }
//}
