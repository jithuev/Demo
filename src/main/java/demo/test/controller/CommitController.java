package demo.test.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import demo.test.service.CommitServiceImpl;

import java.util.Calendar;

@RestController
public class CommitController {
	
	@Autowired
	CommitServiceImpl commitServiceImpl1;


    @GetMapping("/")
    public String hello() {
        return "Hello Spring Boot!";
    }

    @GetMapping("/time")
    public String time() {
        return Calendar.getInstance().getTime().toString();
    }

	@RequestMapping(path = "/getcommitHistory", method = RequestMethod.POST)    
	public void getCommitHistory(@RequestParam("branchName") String branchName) throws ParseException {
		commitServiceImpl1.getCommitHistory(branchName);
	}
	
	/*@RequestMapping(path="/getCommitHistoryForCommit", method = RequestMethod.POST)    
	public void getCommitHistoryForCommit(@RequestParam("commitID") String commitID) throws ParseException {
		System.out.println(commitID);
	}*/
}
