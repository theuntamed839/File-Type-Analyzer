package jsonParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import utility.Utility;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelperParser {
    private Gson gson;

    public HelperParser() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void process(String filename) throws IOException {
        JsonElement jsonElement = JsonParser.parseReader(new FileReader(filename));
        Map<String, String> asdasd = new HashMap<>();
        HashMap hashMap = (HashMap) gson.fromJson(jsonElement, asdasd.getClass());
        List<Magic> list = new ArrayList<>();
        for (Object key : hashMap.keySet()) {
            String kk = (String) key;
            Map value = (Map) hashMap.get(key);
            String Mime = null;
            List<String> ll = null;
            for (Object o : value.keySet()) {
                String s = (String) o;
                if (s.equalsIgnoreCase("mime")) {
                    Mime = (String) value.get(o);
                } else if (s.equalsIgnoreCase("signs")) {
                    ll = (List) value.get(o);
                } else {
                    System.out.println("either ************************");
                }
            }
            list.add(new Magic((String) key, new Value(ll, Mime)));
        }
        List<Structure> structureList = new ArrayList<>();
        for (Magic magic : list) {
            List<String> sign = magic.value().sign();
            List<Signature> newCreated = new ArrayList<>();
            for (int i = 0; i < sign.size(); i++) {
                String[] split = sign.get(i).trim().split(",");
                newCreated.add(new Signature(Integer.parseInt(split[0]),
                        Utility.getBytefromHex(split[1])));
            }
            var struct = new Structure(magic.name(), magic.value().mime(),
                    newCreated);
            structureList.add(struct);
        }
        FileWriter fileWriter = new FileWriter("magicBytesNew.json");
        gson.toJson(structureList,
               fileWriter );
        fileWriter.close();
    }
}
