package analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileManager {
    private final String directory;

    public FileManager(String directory) {
        this.directory = directory;
    }

    public static String[] getFileNames(String directory) {
        File file = new File(directory);
        File[] files = file.listFiles();
        ArrayList<String> fileNames = new ArrayList<>(files.length);
        for(File single : files){
            fileNames.add(single.getAbsolutePath());
        }
        return fileNames.toArray(new String[0]);
    }

    public static Map<String, String> getCodes(String fileName){
        LinkedHashMap<String, String> codes = new LinkedHashMap<>();
        String[] pattern = new String[100];
        String[] description = new String[100];
        int k = 0;
        try {
            Scanner s = new Scanner(new File(fileName));
            while (s.hasNext()){
                String[] piece = s.nextLine().trim().split(";");
                pattern[k] = piece[1].replace("\"","");
                description[k++] = piece[2].replace("\"","");
            }
            for (int i = k - 1; i >= 0 ; i--) {
                codes.put(pattern[i], description[i]);
            }
            return codes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return codes;
    }
}
