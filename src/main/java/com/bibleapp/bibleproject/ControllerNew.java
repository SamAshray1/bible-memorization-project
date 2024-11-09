package com.bibleapp.bibleproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/references")
public class ControllerNew {
	
	private List<Reference> referenceList = new ArrayList<>();
	
	@Autowired
	private ReadJson readjson;

	
	@PostMapping(path = "/add-verse")
	public ResponseEntity<String> addVerse(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Reference reference) {
        
		// Check if the authorization header is present
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid authorization header");
        }
		
		String key = authorizationHeader.substring(7); // Extract the key from "Bearer " prefix
        // Implement your logic to validate the key (e.g., check against a database of valid keys)

        // Validate the key (replace with your actual validation logic)
        if (!isValidKey(key)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"Invalid API key\"}");
        }
        
        
        if(isExisting(reference)) {
        	return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\": \"Reference already exists.\"}");
        }else {
        	referenceList.add(reference);
        	
        	return ResponseEntity.ok("{\"message\": \" Reference added successfully.\" }");
        }
        
	}
	
	// Add a method to validate the API key (replace with your implementation)
	private boolean isValidKey(String key) {
	    // Implement your key validation logic here (e.g., check against a database)
	    // This is a placeholder for your actual validation
	    return key.equals("xAsqw12"); // Replace with your actual validation logic
	}
	
	private boolean isExisting(Reference reference) {
		boolean foundRef = false;
		for (Reference item : referenceList) {
		    if ((item.getBook().equals(reference.getBook())) && 
		    		(item.getChapter().equals(reference.getChapter())) && 
		    		(item.getVerse().equals(reference.getVerse()))) {
		    	System.out.print("dupe");
		        foundRef = true;
		        break;
		    }
		}
		
		return foundRef;
	}
	
	@GetMapping(path="/get-verse-list")
//	public List<Reference> getVerseWithBCV() {
	public String getVerseWithBCV() {
//		referenceList = (List<Reference>) referenceRepository.findAll();
//		System.out.println(referenceList);
		
		String response = "[";
		for(int i=0 ; i<referenceList.size() ; i++) {
			Reference listItem = referenceList.get(i);
			String book = listItem.getBook();
			String chapter = listItem.getChapter();
			String verse = listItem.getVerse();
			String[] arr = {book, chapter, verse};
			
			response += "{\"" + listItem.toString() + "\":";
			response +=  readjson.returnVerses(arr) + "}";
//			System.out.println(response);
			
			if(referenceList.size() > 1 & i != referenceList.size()-1) {
				response += ",";
			}
		}
		response += "]";
		
		System.out.println(response);
		return response;
	}

	@PostMapping("/add-multiple")
	public ResponseEntity<String> addMultipleReferences(@RequestHeader("Authorization") String authorizationHeader, @RequestBody List<Reference> references){
		
		// Check if the authorization header is present
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid authorization header");
        }
		
		String key = authorizationHeader.substring(7); // Extract the key from "Bearer " prefix
        // Implement your logic to validate the key (e.g., check against a database of valid keys)

        // Validate the key (replace with your actual validation logic)
        if (!isValidKey(key)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"Invalid API key\"}");
        }
        
		List<Reference> newReferences = new ArrayList<>();
		
		for(Reference item: references) {
			if(isExisting(item)) {
				continue;
			}else {
				referenceList.add(item);
				newReferences.add(item);
			}
		}
		
		if(newReferences.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicates found");
        }else {
        	return ResponseEntity.ok("References added successfully!" + newReferences.toString());
        }
		
        
	}
}
