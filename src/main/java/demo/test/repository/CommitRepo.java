package demo.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.test.entity.CommitEntity;

@Repository
public interface CommitRepo extends JpaRepository<CommitEntity, Long>{
}
