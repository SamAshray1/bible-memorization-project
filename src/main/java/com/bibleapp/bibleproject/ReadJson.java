package com.bibleapp.bibleproject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.springframework.stereotype.Component;

@Component
public class ReadJson {
    // Specify the path to your JSON file
    public static String filePath = "src/main/resources/static/en_kjv.json";
//    public static String filePath = "/app/en_kjv.json";
    

	    public static String main(String[] args) {
	    	String finalVerse = null;
	    	
	    	for (String i : args) {
	    		System.out.println(i);
	    	}


	        try {
	            // Create ObjectMapper instance
	            ObjectMapper objectMapper = new ObjectMapper();

	            // Read JSON file and parse it into a JsonNode object
	            JsonNode jsonNode = objectMapper.readTree(new File(filePath));

//	             Iterate through each object in the array
	            for (JsonNode jsonArrayNode : jsonNode) {
	                // Get the field names (keys) of the current JSON object
	                Iterator<String> fieldNames = jsonArrayNode.fieldNames();

//	                // Print each field name
//	                while (fieldNames.hasNext()) {
//	                    String fieldName = fieldNames.next();
//	                    System.out.println("Field Name: " + fieldName);
//	                }
	                
	                if (jsonArrayNode.has("abbrev") && jsonArrayNode.get("abbrev").asText().equals("gn")) {
	                    // Print the entire JSON object
	                    JsonNode twoDArray = jsonArrayNode.get("chapters");
	                    System.out.println("JSON Object:"+ twoDArray.get(0).get(1));
	                    finalVerse =  twoDArray.get(0).get(1).toPrettyString();
	                }
	            }
	    

	            // Now you can work with the JSON data using the JsonNode
	            // For example, print the entire JSON structure
//	            System.out.println(jsonNode.toPrettyString());

	            // You can also access specific elements in the JSON
	            // For example, if the JSON has a field called "name"
//	            if (nameNode != null) {
//	                String name = nameNode.asText();
//	                System.out.println("Name: " + name);
//	            }

	            // Handle the rest of your JSON processing here...

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return finalVerse;
	    }
	    
	    public String returnVerses(String[] args) {
	    	String finalVerse = null;
	    	
	    	try {
	            // Create ObjectMapper instance
	            ObjectMapper objectMapper = new ObjectMapper();

	            // Read JSON file and parse it into a JsonNode object
	            JsonNode jsonNode = objectMapper.readTree(new File(filePath));
	            
	            // Iterate through each object in the array
	            for (JsonNode jsonArrayNode : jsonNode) {
	                if (jsonArrayNode.has("name") && jsonArrayNode.get("name").asText().toLowerCase().equals(args[0].toLowerCase())) {
	                	// Print the entire JSON object
	                    JsonNode twoDArray = jsonArrayNode.get("chapters");
	                    int chapter = Integer.parseInt(args[1]) - 1;
	                    int verse = Integer.parseInt(args[2]) - 1;
//	                    System.out.println("JSON Object:"+ twoDArray.get(chapter).get(verse));
	                    finalVerse = twoDArray.get(chapter).get(verse).toPrettyString();
	                    break;
	                }
	            }
	    
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
//	    	System.out.println(finalVerse);
	    	return finalVerse;
	    }
}

