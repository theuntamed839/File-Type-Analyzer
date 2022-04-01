package core;

import forkjoin.util.IdentifiedFile;
import jsonParser.MagicFileParser;
import structure.Signature;
import structure.Structure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FileTypeIdentifier {
    List<Structure> magicList;
    public FileTypeIdentifier() {
        magicList = MagicFileParser.getMagicList();
    }

    public IdentifiedFile getFileType(File file) {
        var arr = new byte[10];
        try {
            int read = new FileInputStream(file).read(arr);
            if (read == -1) {
                System.out.println("*****************************");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        for (Structure structure : magicList) {
            for (Signature signature : structure.getSignature()) {
                int i = signature.getOffset();
                byte[] code = signature.getCode();
                int limit = Math.min(code.length, arr.length);
                boolean flag = true;
                for (int j = 0; i < limit; i++, j++) {
                    if (arr[i] != code[j]) { // i and j because the offset applys to only one
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return new IdentifiedFile(file.getName(), structure.getMime());
                }
            }
        }
        return new IdentifiedFile(file.getName(), "UNKOWN");
    }
}
