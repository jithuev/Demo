package demo.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LsRemoteCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InvalidRemoteException, TransportException, GitAPIException 
    {
        System.out.println( "Hello World!" );
        String repoUrl = "https://github.com/sivassk14/HelloAppEngineApplication";
        String cloneDirectoryPath = "/home/jithu/Desktop/testRepo2"; // Ex.in windows c:\\gitProjects\SpringBootMongoDbCRUD\
        try {
            System.out.println("Cloning "+repoUrl+" into "+repoUrl);
            File file = new File(cloneDirectoryPath);
            Git git1 = Git.open(new File(cloneDirectoryPath));
            final Repository repository = git1.getRepository();
            // To clone the repo to local
//            Git.cloneRepository()
//                    .setURI(repoUrl)
//                    .setDirectory(file)
//                    .setCloneAllBranches(true)
//                    .setBranch("master")
//                .call()
//            .getRepository();
            System.out.println("the brach of the said remote repo  "+ repository.getBranch());
            
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
        }
    }
}
