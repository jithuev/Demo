package demo.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LsRemoteCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.Transport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import demo.test.repository.CommitRepo;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses= {CommitRepo.class})
public class App 
{
    public static void main( String[] args ) throws InvalidRemoteException, TransportException, GitAPIException 
    {
    	
        SpringApplication.run(App.class, args);
       /* System.out.println( "Hello World!" );
        String repoUrl = "https://github.com/sivassk14/HelloAppEngineApplication";
        String cloneDirectoryPath = "/home/jithu/Desktop/testRepo2"; // Ex.in windows c:\\gitProjects\SpringBootMongoDbCRUD\
        try {
        	
        	FileRepositoryBuilder builder = new FileRepositoryBuilder();
        	final Repository repository = builder.setGitDir(new File(repoUrl)).setMustExist(true).build();
        	System.out.println("Cloning "+repoUrl+" into "+repoUrl);
//            File file = new File(cloneDirectoryPath);
//            Git git1 = Git.open(new File(cloneDirectoryPath));
//            final Repository repository = git1.getRepository();
            // To clone the repo to local
            
//            Git git = git.branchList().
//            Git.cloneRepository()
//                    .setURI(repoUrl)
//                    .setDirectory(file)
//                    .setCloneAllBranches(true)
//                    .setBranch("master")
//                .call()
//            .getRepository();
            
            List<String> logMessages = new ArrayList<>();
            Git git = new Git(repository);
            Iterable<RevCommit> log = git.log().call();
            for (Iterator<RevCommit> iterator = log.iterator(); iterator.hasNext();) {
              RevCommit rev = iterator.next();
              logMessages.add(rev.getFullMessage());
            }
            logMessages.forEach(System.out::println);
            
            System.out.println("Completed Cloning");
        } catch (IOException e) {
            System.out.println("Exception occurred while cloning repo");
            e.printStackTrace();
        }*/
    }
}
