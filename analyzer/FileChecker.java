package analyzer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class FileChecker implements Runnable {
    private final int BUFFER_SIZE = 1024;
    private Map<String, String> patterns;
    private String FileName;

    public FileChecker(Map<String, String> code, String list) {
        this.patterns = code;
        FileName = list;
    }

    private static String getStringValue(byte[] buffer) {
        StringBuilder str = new StringBuilder();
        for (byte each : buffer) {
            str.append((char) each);
        }
        return str.toString();
    }

    private void manager() {
        int i = 0;
        for (String pattern : patterns.keySet()) {
            if (checker(FileName, pattern)) {
                String[] f = FileName.split("/");
                System.out.println(f[f.length - 1] + ": " + patterns.get(pattern));
                return;
            }
        }
        String[] f = FileName.split("/");
        System.out.println(f[f.length - 1] + ": Unknown file type");
    }

    private boolean checker(String fileName, String pattern) {
        byte[] buffer = new byte[BUFFER_SIZE];
        try (InputStream in = new FileInputStream(fileName)) {
            while (in.read(buffer) != -1) {
                String convertedString = getStringValue(buffer);
                if (kmp(pattern, convertedString)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean kmp(String pattern, String convertedString) {
        int[] prefix = prefixOf(pattern);
        ArrayList<Integer> occurences = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < convertedString.length(); i++) {
            while (j > 0 && convertedString.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (convertedString.charAt(i) == pattern.charAt(j)) {
                j += 1;
            }
            if (j == pattern.length()) {
                occurences.add(i - j + 1);
                j = prefix[j - 1];
            }
        }
        return !occurences.isEmpty();
    }

    private int[] prefixOf(String pattern) {
        int[] prefix = new int[pattern.length()];
        for (int i = 1; i < pattern.length(); i++) {
            int j = prefix[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j += 1;
            }
            prefix[i] = j;
        }
        return prefix;
    }

    @Override
    public void run() {
        manager();
    }
}
