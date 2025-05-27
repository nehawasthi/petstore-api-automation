package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static JsonNode configRoot;
    private static JsonNode userRoot;

    static {
        try {
            // Load config.json
            configRoot = MAPPER.readTree(Paths.get("src/test/resources/config.json").toFile());

            // Load userdata.json
            userRoot = MAPPER.readTree(Paths.get("src/test/resources/userdata.json").toFile());

        } catch (IOException e) {
            System.err.println("Error loading JSON files: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON files", e);
        }
    }

    /**
     * Fetch value from config.json using dot notation (e.g. "Urls.qa")
     */
    public static JsonNode fetchUrl(String keyPath) {
        String[] keys = keyPath.split("\\.");
        JsonNode current = configRoot;

        for (String key : keys) {
            if (current == null || current.get(key) == null) {
                throw new IllegalArgumentException("Key not found in config.json: " + keyPath);
            }
            current = current.get(key);
        }

        return current;
    }

    /**
     * Get full user data node by userType from userdata.json (e.g. "user", "invalidUser")
     */
    public static JsonNode getUser(String userType) {
        JsonNode node = userRoot.get(userType);
        if (node == null) {
            throw new IllegalArgumentException("User type not found in userdata.json: " + userType);
        }
        return node;
    }

    /**
     * Get specific field from top-level of userdata.json
     */
    public static JsonNode getField(String key) {
        JsonNode node = userRoot.get(key);
        if (node == null) {
            throw new IllegalArgumentException("Key not found in userdata.json: " + key);
        }
        return node;
    }
}