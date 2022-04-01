package forkjoin;

import core.FileTypeIdentifier;

import java.io.File;
import java.util.*;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class CustomRecursiveAction extends RecursiveAction {
    private File file;
    private static final FileTypeIdentifier fileTypeIdentifier =
            new FileTypeIdentifier();
    public CustomRecursiveAction(String file) {
        this.file = new File(file);
    }
    public CustomRecursiveAction(File file) {
        this.file = file;
    }

    @Override
    protected void compute() {
        if (file.isDirectory()) {
             ForkJoinTask.invokeAll(createSubtasks());
        }else if (file.isFile()) {
            process(file);
        } else {
            System.out.println("file not dir nor proper file");
        }
    }

    private Collection<CustomRecursiveAction> createSubtasks() {
        List<CustomRecursiveAction> dividedTasks = new ArrayList<>();
        for (File listFile : file.listFiles()) {
            dividedTasks.add(new CustomRecursiveAction(listFile));
        }
        return dividedTasks;
    }

    private void process(File file) {
        Objects.requireNonNull(file);
        System.out.println(fileTypeIdentifier.getFileType(file));
        System.out.println(Thread.currentThread());
    }
}