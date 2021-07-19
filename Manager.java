package analyzer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Manager {
    private final Map<String, String> codes;
    private final String directoryName;

    public Manager(Map<String, String> codes, String directory) {
        this.codes = codes;
        this.directoryName = directory;
    }

    public void execute() {
        String[] listOfFiles = FileManager.getFileNames(directoryName);
        ExecutorService service = Executors.newFixedThreadPool(10);
//        ExecutorService service = Executors.newSingleThreadExecutor();
        for (String each : listOfFiles){
            service.execute(new FileChecker(codes, each));
        }
        service.shutdown();
        try {
            service.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
