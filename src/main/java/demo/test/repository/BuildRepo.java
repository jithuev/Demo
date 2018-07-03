package demo.test.repository;

import demo.test.entityjenkins.Build;
import demo.test.entityjenkins.BuildKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildRepo extends JpaRepository<Build, BuildKey> {
}

