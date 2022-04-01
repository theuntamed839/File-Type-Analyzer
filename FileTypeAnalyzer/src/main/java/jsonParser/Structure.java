package jsonParser;

import java.util.List;

/**
[string ext] : {
        signs: [sign]
        mime: string
        }
 */
public record Structure(String fileType, String mime, List<Signature> signature) {
}
record Signature(int offSet, byte[] code){};