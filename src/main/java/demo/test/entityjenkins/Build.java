package demo.test.entityjenkins;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name = "Builds")
@Table(name = "Builds")
public class Build {

    @EmbeddedId
    @Getter @Setter private BuildKey buildKey;

    @Column(name = "url")
    @Getter @Setter String url;

    @Column(name = "duration")
    @Getter @Setter int duration;

    @Column(name = "buildTimestamp")
    @Getter @Setter long buildTimestamp;

    @Column(name = "result")
    @Getter @Setter String result;

    @Column(name = "userId")
    @Getter @Setter String userId;

    @Column(name = "userName")
    @Getter @Setter String userName;

//    @ManyToOne
//    public JenkinsJob job;
}