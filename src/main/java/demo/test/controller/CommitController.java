package demo.test.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CommitController {
	
	@RequestMapping("/getcommitHistory")    
	public void getCommitHistory() throws ParseException {
		
		final String uri = "https://api.github.com/repos/jithuev/Demo/commits";

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    
	    JSONParser parse = new JSONParser();
	    JSONObject jsonArray1 = new JSONObject();
	    JSONObject jsonarr_1  = new JSONObject();
	    
	    JSONArray jsonArray = (JSONArray)parse.parse(result);
	    for(int i=0; i<jsonArray.size(); i++) {
	    	jsonArray1 = (JSONObject) jsonArray.get(i);
	    	jsonarr_1 = (JSONObject) jsonArray1.get("commit");
	    	System.out.println("\nmessage: " +jsonarr_1.get("message"));
	    }

	   /* for(int i=0;i<jsonarr_1.size();i++)

	    {


	    System.out.println("\nPlace id: " +jsonarr_1.get("message"));


	    }*/
	    
	    System.out.println(result);
	}
}
