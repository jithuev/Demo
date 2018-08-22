package demo.test.controller;

import org.json.simple.JSONArray;        
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import demo.test.service.CommitServiceImpl;

@RestController
public class CommitController {
	
	@Autowired
	CommitServiceImpl commitServiceImpl1;
	
	@RequestMapping(path = "/getcommitHistory", method = RequestMethod.GET)    
	public void getCommitHistory() throws ParseException {
		commitServiceImpl1.getCommitHistory();
	}
	
	@RequestMapping(path="/getCommitHistoryForCommit", method = RequestMethod.POST)    
	public void getCommitHistoryForCommit(@RequestParam("commitID") String commitID) throws ParseException {
		System.out.println(commitID);
	}
}
