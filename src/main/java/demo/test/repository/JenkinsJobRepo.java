package demo.test.repository;

import demo.test.entityjenkins.JenkinsJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenkinsJobRepo extends JpaRepository<JenkinsJob, String> {
}
