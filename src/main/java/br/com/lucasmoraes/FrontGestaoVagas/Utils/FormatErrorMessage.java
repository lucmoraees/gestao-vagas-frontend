package br.com.lucasmoraes.FrontGestaoVagas.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormatErrorMessage {
    public static String formatErrorMessage(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(message);

            if (rootNode.isArray()) {
                return formatArrayErrorMessage(rootNode);
            }

            return rootNode.asText();
        } catch (Exception e) {
            return message;
        }
    }

    public static String formatArrayErrorMessage(JsonNode arrayNode) {
        StringBuilder formattedMessager = new StringBuilder();

        for (JsonNode node: arrayNode) {
            formattedMessager.append("- ").append(node.get("message").asText()).append("\n");
        }

        return formattedMessager.toString();
    }
}
