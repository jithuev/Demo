package demo.test;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import demo.test.repository.CommitRepo;

/**
 * Hello world example!
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses= {CommitRepo.class})
public class App 
{
    public static void main( String[] args ) throws InvalidRemoteException, TransportException, GitAPIException 
    {
    	
        SpringApplication.run(App.class, args);
    }
}
