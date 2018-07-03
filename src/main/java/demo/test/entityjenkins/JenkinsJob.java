package demo.test.entityjenkins;

import lombok.Setter;
import lombok.Getter;

import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Component
@Entity
@Table(name = "JenkinsJobs")
public class JenkinsJob {

    @Id
    @Column(name="jobName")
    @Getter @Setter private String displayName;

    @Column(name="description")
    @Getter @Setter private String description;

    @Column(name="buildable")
    @Getter @Setter private boolean buildable;

    @Column(name="nextBuildNumber")
    @Getter @Setter private int nextBuildNumber;

    @Column(name="inQueue")
    @Getter @Setter private boolean inQueue;

//    @OneToMany(mappedBy="job")
//    public Collection<Build> builds;

    @Getter @Setter private int firstBuildId;
    @Getter @Setter private int lastBuildId;
    @Getter @Setter private int lastCompletedBuildId;
    @Getter @Setter private int lastFailedBuildId;
    @Getter @Setter private int lastStableBuildId;
    @Getter @Setter private int lastSuccessfulBuildId;
    @Getter @Setter private int lastUnstableBuildId;
    @Getter @Setter private int lastUnsuccessfulBuildId;

//    @Column
//    private QueueItem queueItem;
//
//    @Column
//    @ElementCollection(targetClass=Job.class)
//    private List<Job> downstreamProjects;
//
//    @Column
//    @ElementCollection(targetClass=Job.class)
//    private List<Job> upstreamProjects;

}
