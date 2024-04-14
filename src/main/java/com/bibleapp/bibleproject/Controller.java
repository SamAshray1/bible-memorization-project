//package com.bibleapp.bibleproject;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//public class Controller {
//	
//	@Autowired
//	ReferenceRepository referenceRepository;
//	
//	@Autowired
//	ReadJson readjson; 
//
//	@PostMapping(path="/add") // Map ONLY POST Requests
//	public @ResponseBody String addNewUser (@RequestParam String name
//			, @RequestParam String email) {
//		// @ResponseBody means the returned String is the response, not a view name
//		// @RequestParam means it is a parameter from the GET or POST request
//
//		User n = new User();
//		n.setName(name);
//		n.setEmail(email);
////		userRepository.save(n);
//		return "Saved";
//	}
//
////	@GetMapping(path="/all")
////	public @ResponseBody Iterable<User> getAllUsers() {
//		// This returns a JSON or XML with the users
////		return userRepository.findAll();
////	}
//	
//	private List<Reference> referenceList = new ArrayList<>();
////	Set<Reference> referenceList = new HashSet<>();
//	
//	@GetMapping("/hello")
//	public String helloWorld() {
////		String[] arr = {"genesis", "1", "1"};
//		readjson.main(null);
//		return "hello";
////		return "Hello World!";
//	}
//	
//	@GetMapping(path="/get-verse/{book}/{chapter}/{verse}")
//	public String getVerseWithBCV(
//			@PathVariable String book, 
//			@PathVariable String chapter, 
//			@PathVariable String verse) {
//		String[] arr = {book, chapter, verse};
//		return readjson.returnVerses(arr);
//		}
//	
//	@GetMapping(path="/get-verse")
//	public String getVerseWithBCVReqParams(
//			@RequestParam(name = "book", defaultValue="Genesis") String book,
//			@RequestParam(name = "chapter", defaultValue="0") String chapter,
//			@RequestParam(name = "verse", defaultValue="0") String verse) {
//		String[] arr = {book, chapter, verse};
//		System.out.println(arr[0]);
//		return readjson.returnVerses(arr);
//		}
//	
//	@PostMapping(path="/add-verse")
//	public ResponseEntity getVerseWithBCV( @RequestBody Reference reference) {
//		Reference n = new Reference();
//		n.setBook(reference.getBook());
//		n.setChapter(reference.getChapter());
//		n.setVerse(reference.getVerse());
//
//	    // Check if the reference already exists in the database
//	    Optional<Reference> existingReference = referenceRepository.findByBookAndChapterAndVerse(
//	        reference.getBook(), reference.getChapter(), reference.getVerse());
//
//	    if (existingReference.isPresent()) {
//	        // If a duplicate is found, return a response indicating the conflict
//	        return ResponseEntity.status(HttpStatus.CONFLICT).body("{response: Reference already exists}");
//	    } else {
//	        // If no duplicate is found, save the new reference
//	        referenceRepository.save(reference);
//	        return ResponseEntity.ok("Reference added successfully");
//	    }
//		
////		boolean found = false;
////		for (Reference item : referenceList) {
////		    if ((item.getBook().equals(reference.getBook())) && 
////		    		(item.getChapter().equals(reference.getChapter())) && 
////		    		(item.getVerse().equals(reference.getVerse()))) {
////		    	System.out.print("dupe");
////		        found = true;
////		        break;
////		    }
////		}
////		
////		if (!found) {
////		    referenceList.add(reference);
////		    return new ResponseEntity<>("Added Verse", HttpStatus.CREATED);
////		}
////		else {
////			return new ResponseEntity<>("Verse already exists!", HttpStatus.CONFLICT);
////		}
//	}
//	
//	@GetMapping(path="/get-verse-list")
////	public List<Reference> getVerseWithBCV() {
//	public String getVerseWithBCV() {
//		referenceList = (List<Reference>) referenceRepository.findAll();
////		System.out.println(referenceList);
//		
//		String response = "[";
//		for(int i=0 ; i<referenceList.size() ; i++) {
//			Reference listItem = referenceList.get(i);
//			String book = listItem.getBook();
//			String chapter = listItem.getChapter();
//			String verse = listItem.getVerse();
//			String[] arr = {book, chapter, verse};
//			
//			response += "{\"" + listItem.toString() + "\":";
//			response +=  readjson.returnVerses(arr) + "}";
////			System.out.println(response);
//			
//			if(referenceList.size() > 1 & i != referenceList.size()-1) {
//				response += ",";
//			}
//		}
//		response += "]";
//		
//		System.out.println(response);
//		return response;
//		}
//	
//	@GetMapping(path="/get-verse-list-sql")
////	public List<Reference> getVerseWithBCV() {
//	public Iterable<Reference> getVerseWithBCVsql() {
//		
//		return referenceRepository.findAll();
//		
//		}
//
//	@PostMapping(path = "/add-verse-creds")
//	public ResponseEntity<String> addVerseWithCreds(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Reference reference) {
//	    // Check if the authorization header is present
//	    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid authorization header");
//	    }
//
//	    String key = authorizationHeader.substring(7); // Extract the key from "Bearer " prefix
//	    // Implement your logic to validate the key (e.g., check against a database of valid keys)
//
//	    // Validate the key (replace with your actual validation logic)
//	    if (!isValidKey(key)) {
//	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"Invalid API key\"}");
//	    }
//
//	    // Check if the reference already exists in the database
//	    Optional<Reference> existingReference = referenceRepository.findByBookAndChapterAndVerse(
//	            reference.getBook(), reference.getChapter(), reference.getVerse());
//
//	    if (existingReference.isPresent()) {
//	        // If a duplicate is found, return a response indicating the conflict
//	        return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\": \"Reference already exists\"}");
//	    } else {
//	        // If no duplicate is found, save the new reference
//	        referenceRepository.save(reference);
//	        return ResponseEntity.ok("{\"message\": \"Reference added successfully\"}");
//
//	    }
//	}
//
//	// Add a method to validate the API key (replace with your implementation)
//	private boolean isValidKey(String key) {
//	    // Implement your key validation logic here (e.g., check against a database)
//	    // This is a placeholder for your actual validation
//	    return key.equals("xAsqw12"); // Replace with your actual validation logic
//	}
//
//	
//	private List<Blog> blogList = new ArrayList<>();
//	
//	@PostMapping(path="/write")
//	public ResponseEntity<String> createBlogPost(@RequestBody Blog blog) {
//		String title = blog.getTitle();
//		String description = blog.getDescription();
//		
//		blogList.add(blog);
//		
//		String response = String.format("Blog created with title: %s, description: %s", title, description);
//		return new ResponseEntity<>(response, HttpStatus.CREATED);
//		
//	}
//	
//	@GetMapping(path="/get-posts")
//	public List<Blog> returnPosts(){
//		return blogList;
//	}
//	@DeleteMapping(path="/delete-verse")
//	public String deleteVerse() {
//		referenceRepository.deleteByBookAndChapterAndVerse("john", "", "12");
//		return "deleted";
//	}
//
//}
