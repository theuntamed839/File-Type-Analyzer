package analyzer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.System;

public class Main {
    public static void main(String[] args) {
        String dirName = args[0]; // directory 
        String pattern = args[1]; // patterns file
        Manager manager = new Manager(FileManager.getCodes(pattern), dirName);
        manager.execute();
    }
}
