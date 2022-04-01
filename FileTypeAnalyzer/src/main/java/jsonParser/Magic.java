package jsonParser;

import java.util.List;

public record Magic(String name, Value value) {
}
record Value(List<String> sign, String mime) {}