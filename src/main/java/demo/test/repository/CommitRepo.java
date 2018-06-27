package demo.test.repository;

import org.springframework.data.repository.CrudRepository;

import demo.test.entity.CommitEntity;

public interface CommitRepo extends CrudRepository<CommitEntity, Long>{

}
