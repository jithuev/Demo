package demo.test.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import demo.test.entityjenkins.Build;
import demo.test.entityjenkins.BuildKey;
import demo.test.entityjenkins.JenkinsJob;
import demo.test.repository.BuildRepo;
import demo.test.repository.JenkinsJobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JenkinsJobServiceImpl implements JenkinsJobService{

    @Autowired
    private JenkinsJobRepo jenkinsJobRepo;

    @Autowired
    private BuildRepo   buildRepo;

    private static final String jenkinsServerUri= "http://localhost:8080";
    private static final String jenkinsServerUname= "sarath";
    private static final String jenkinsServerPswd= "sarath";

    private static int getBuildId( com.offbytwo.jenkins.model.Build build ){
        int id = -1;
        try {
            if( build.details().getId() instanceof String ){
                id=  Integer.parseInt(build.details().getId());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }


    private static JenkinsJob mapJob(JobWithDetails jobDetails){

        JenkinsJob j = new JenkinsJob();
        j.setDescription( jobDetails.getDescription() );
        j.setDisplayName( jobDetails.getDisplayName() );
        j.setBuildable( jobDetails.isBuildable() );
        j.setNextBuildNumber( jobDetails.getNextBuildNumber() );
        j.setInQueue( jobDetails.isInQueue() );

            j.setFirstBuildId( getBuildId( jobDetails.getFirstBuild() ) );
            j.setLastBuildId( getBuildId( jobDetails.getLastBuild() ));
            j.setLastCompletedBuildId( getBuildId( jobDetails.getLastCompletedBuild() ));
            j.setLastFailedBuildId( getBuildId( jobDetails.getLastFailedBuild() ));
            j.setLastStableBuildId( getBuildId( jobDetails.getLastStableBuild() ));
            j.setLastSuccessfulBuildId( getBuildId( jobDetails.getLastSuccessfulBuild() ));
            j.setLastUnstableBuildId( getBuildId( jobDetails.getLastUnstableBuild() ));
            j.setLastUnsuccessfulBuildId( getBuildId( jobDetails.getLastUnsuccessfulBuild() ));



//        List<JenkinsJobAbstract> dJobsNew= new ArrayList<JenkinsJobAbstract>();
            // map List<offbytwo.jenkins.model.Job> to List<entityjenkins.JenkinsJobAbstract> :
//        BeanUtils.copyProperties(dJobsNew, jobDetails.getDownstreamProjects());
//        j.setDownstreamProjects( dJobsNew );

        return j;
    }


    private static List<Build> getBuilds(JobWithDetails jobDetails){
        List<Build> newBuilds= new ArrayList<>();
        List<com.offbytwo.jenkins.model.Build> oldBuilds = jobDetails.getBuilds();

        for( com.offbytwo.jenkins.model.Build oldBuild : oldBuilds ) {
            com.offbytwo.jenkins.model.BuildWithDetails buildDet = null;
            try {
                buildDet = oldBuild.details();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Build newBuild = new Build();

            newBuild.setBuildKey(new BuildKey(jobDetails.getDisplayName(), Integer.parseInt(buildDet.getId())));
            newBuild.setDuration((int) (buildDet.getDuration() / 1000));
            newBuild.setBuildTimestamp(buildDet.getTimestamp());
            newBuild.setResult(buildDet.getResult().name());
            newBuild.setUrl(buildDet.getUrl());

            List<com.offbytwo.jenkins.model.BuildCause> causeList = buildDet.getCauses();
            if (causeList.isEmpty())
                System.out.println("cause list empty.");
            else {
                newBuild.setUserId(causeList.get(0).getUserId());
                newBuild.setUserName(causeList.get(0).getUserName());
            }

            newBuilds.add(newBuild);
        }

        for(Build b : newBuilds){
            System.out.println("------------------");
            System.out.println(b.getBuildKey().getBuildId());
            System.out.println(b.getBuildKey().getJobName());
            System.out.println(b.getDuration());
            System.out.println(b.getResult());
            System.out.println(b.getBuildTimestamp());
            System.out.println(b.getUrl());
            System.out.println(b.getUserId());
            System.out.println(b.getUserName());
        }
        return newBuilds;
    }



    @Override
    public void getJobs() {

        List<JenkinsJob> jenkinsJobs = new ArrayList<>();
        List<Build> builds = new ArrayList<>();

        Map<String, Job> jobs;
        try {
            JenkinsServer jenkins = new JenkinsServer(new URI(jenkinsServerUri), jenkinsServerUname, jenkinsServerPswd);
            System.out.println( jenkins.isRunning()? "The specified server is up & running" : "**The specified server is not available**" );
            jobs = jenkins.getJobs();

            System.out.println("Jobs:");

            for (Map.Entry<String, Job> job : jobs.entrySet()) {
                System.out.println( "* " + job.getKey() );
                Job jobVal = job.getValue();
                String jobName = jobVal.getName();
                JobWithDetails jobDetails = jenkins.getJob(jobName).details();

                jenkinsJobs.add( mapJob(jobDetails) );
                builds.addAll( getBuilds(jobDetails) );
            }

            jenkinsJobRepo.saveAll(jenkinsJobs);
            buildRepo.saveAll( builds );
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getJob(String jobName) {
        JobWithDetails job = null;
        try {
            JenkinsServer jenkins = new JenkinsServer(new URI(jenkinsServerUri), jenkinsServerUname, jenkinsServerPswd);
            System.out.println( jenkins.isRunning()? "The specified server is up & running" : "**The specified server is not available**" );
            job = jenkins.getJob(jobName).details();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        jenkinsJobRepo.saveAndFlush( mapJob(job) );

        List<Build> builds = getBuilds(job);
        buildRepo.saveAll( builds );
    }
}
