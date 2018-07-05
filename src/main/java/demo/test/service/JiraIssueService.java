package demo.test.service;

public interface JiraIssueService {
    void getIssues(String serverUrl, String username,
                   String password, String projectName);
}
