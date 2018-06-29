package demo.test.controller;

import java.net.URI;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

import org.springframework.web.bind.annotation.RequestParam;

import demo.test.service.CommitServiceImpl;

@RestController
public class CommitController {
	
	@Autowired
	CommitServiceImpl commitServiceImpl1;
	
	@RequestMapping(path = "/getcommitHistory", method = RequestMethod.POST)    
	public void getCommitHistory(@RequestParam("owner") String owner, @RequestParam("repository") String repository, 
			@RequestParam("branchName") String branchName) throws ParseException {
		commitServiceImpl1.getCommitHistory(owner, repository, branchName);
	}
	
	/*@RequestMapping(path="/getCommitHistoryForCommit", method = RequestMethod.POST)    
	public void getCommitHistoryForCommit(@RequestParam("commitID") String commitID) throws ParseException {
		System.out.println(commitID);
	}*/
}
