package forkjoin;

import core.FileTypeIdentifier;
import forkjoin.util.IdentifiedFile;
import java.io.File;
import java.util.*;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class CustomRecursionTask extends RecursiveTask<List<IdentifiedFile>> {
    private File file;
    private static final FileTypeIdentifier fileTypeIdentifier =
            new FileTypeIdentifier();
    public CustomRecursionTask(String file) {
        this.file = new File(file);
    }
    public CustomRecursionTask(File file) {
        this.file = file;
    }

    @Override
    protected List<IdentifiedFile> compute() {
        if (file.isDirectory()) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .map(ForkJoinTask::join)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }else if (file.isFile()) {
            return Collections.singletonList(process(file));
        } else
            return null;
    }

    private Collection<CustomRecursionTask> createSubtasks() {
        List<CustomRecursionTask> dividedTasks = new ArrayList<>();
        for (File listFile : file.listFiles()) {
            dividedTasks.add(new CustomRecursionTask(listFile));
        }
        return dividedTasks;
    }

    private IdentifiedFile process(File file) {
        Objects.requireNonNull(file);

        if (file.isFile()) {
            return fileTypeIdentifier.getFileType(file);
        }
        return null;
    }

}
