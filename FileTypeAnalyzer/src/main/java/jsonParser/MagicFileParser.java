package jsonParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import structure.Structure;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MagicFileParser {

    public List<Structure> magicList;
    private Gson gson;
    private static String MAGIC_FILE = "magicBytes.json";
    private static Type listType;
    private static MagicFileParser set = null;
    private MagicFileParser() {
        gson = new Gson();
        listType = new TypeToken<ArrayList<Structure>>(){}.getType();
    }

    public static List<Structure> getMagicList() {
        if (set == null) {
            set = new MagicFileParser();
        }
        try {
            return set.gson.fromJson(new FileReader(MAGIC_FILE), listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
