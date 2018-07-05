package demo.test.controller;

import demo.test.service.JiraIssueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JiraIssueController {

    @Autowired
    JiraIssueServiceImpl jiraIssueServiceImpl;

    @RequestMapping(path = "/getJiraIssues", method = RequestMethod.POST)
    public void getJiraIssues( @RequestParam(required=true) String serverUrl,
                               @RequestParam(required=true) String username,
                               @RequestParam(required=true) String password,
                               @RequestParam(required=true) String projectName) {

        jiraIssueServiceImpl.getIssues(serverUrl, username, password, projectName);
    }
}

