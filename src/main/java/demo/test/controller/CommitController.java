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
	    
	    JSONArray jsonArray = (JSONArray)parse.parse(result);
	    JSONObject jsonArray1 = (JSONObject) jsonArray.get(0);
	    
//	    JSONObject jobj = (JSONObject) jsonArray1.get(0);
	    
	    JSONObject jsonarr_1 = (JSONObject) jsonArray1.get("commit");
	  //Get data for Results array

	    for(int i=0;i<jsonarr_1.size();i++)

	    {

	    //Store the JSON objects in an array

	    //Get the index of the JSON object and print the values as per the index

	    JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);

	    System.out.println("Elements under results array");

	    System.out.println("\nPlace id: " +jsonarr_1.get("message"));

//	    System.out.println(“Types: ” +jsonobj_1.get(“types”));

	    }
	    
	    System.out.println(result);
	}
}
