package demo.test.service;

import demo.test.entityjira.JiraIssue;
import demo.test.repository.JiraIssueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.IssueRestClient;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class JiraIssueServiceImpl implements JiraIssueService {

    @Autowired
    private JiraIssueRepo jiraIssueRepo;

    public void getIssues(String serverUrl, String username,
                          String password, String projectName){

        List<JiraIssue> issuesList = new ArrayList<>();

        try{

            JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();

            final JiraRestClient jiraClient = factory.createWithBasicHttpAuthentication(
                    new URI(serverUrl), username, password);

            SearchResult result = jiraClient.getSearchClient().searchJql(
                    "project = " + projectName + "  ORDER BY key",
                    new NullProgressMonitor());
            Iterable<BasicIssue> issues = result.getIssues();

            Issue issue = null;
            IssueRestClient issueClient = jiraClient.getIssueClient();

            for(BasicIssue basicIssue: issues){

                issue = issueClient.getIssue( basicIssue.getKey(), new NullProgressMonitor() );
                JiraIssue newIssue = new JiraIssue();

                newIssue.setKey( issue.getKey() );
                newIssue.setProjectName( issue.getProject().getName() );
                newIssue.setAssigneeName( issue.getAssignee() != null ? issue.getAssignee().getName() : "" );
                newIssue.setAssigneeEmail( issue.getAssignee() != null ? issue.getAssignee().getDisplayName() : "" );
                newIssue.setReporterName( issue.getReporter().getName() );
                newIssue.setReporterEmail( issue.getReporter().getDisplayName() );
                newIssue.setCreationDate( issue.getCreationDate().toDate() );
                newIssue.setDueDate( issue.getDueDate() != null? issue.getDueDate().toDate() : null );
                newIssue.setUpdateDate( issue.getUpdateDate().toDate() );
                newIssue.setSummary( issue.getSummary() );
                newIssue.setDescription( issue.getDescription() != null ? issue.getDescription().split("\\r?\\n")[0] : "" );
                newIssue.setPriority( issue.getPriority().getName() );
                newIssue.setStatus( issue.getStatus().getName() );

                issuesList.add( newIssue );
            }
            jiraIssueRepo.saveAll( issuesList );
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
