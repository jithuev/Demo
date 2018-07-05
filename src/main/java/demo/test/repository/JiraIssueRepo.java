package demo.test.repository;

import demo.test.entityjira.JiraIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraIssueRepo extends JpaRepository<JiraIssue, String>{
}
