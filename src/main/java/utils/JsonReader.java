package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonReader {

    public static String getSearchKeyword() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = JsonReader.class
                    .getClassLoader()
                    .getResourceAsStream("searchText.json");

            JsonNode root = mapper.readTree(is);
            return root.get("keyword").asText();

        } catch (Exception e) {
            throw new RuntimeException("Search keyword could not be read from JSON", e);
        }
    }
}