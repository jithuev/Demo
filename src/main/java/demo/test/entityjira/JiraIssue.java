package demo.test.entityjira;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Entity(name = "JiraIssues")
@Table(name = "JiraIssues")
public class JiraIssue {

    @Id
    @Getter @Setter private String key;

    @Column(name = "projectName")
    @Getter @Setter String projectName;

    @Column(name = "reporterName")
    @Getter @Setter String reporterName;

    @Column(name = "reporterEmail")
    @Getter @Setter String reporterEmail;

    @Column(name = "assigneeName")
    @Getter @Setter String assigneeName;

    @Column(name = "assigneeEmail")
    @Getter @Setter String assigneeEmail;

    @Column(name = "priority")
    @Getter @Setter String priority;

    @Column(name = "status")
    @Getter @Setter String status;

    @Column(name = "summary")
    @Getter @Setter String summary;

    @Column(name = "description")
    @Getter @Setter String description;

    @Column(name = "creationDate")
    @Getter @Setter Date creationDate;

    @Column(name = "dueDate")
    @Getter @Setter Date dueDate;

    @Column(name = "updateDate")
    @Getter @Setter Date updateDate;
}