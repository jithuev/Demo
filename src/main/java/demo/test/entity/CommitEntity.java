package demo.test.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Commits_History")
public class CommitEntity {
	@Id
	@Column(name = "Commit_id")
	private String commitId;

	@Column(name = "Commit_message")
	private String commitMessage;
	private Author author;
	private Committer committer;

	@Column(name = "Created_date" , nullable=false, updatable = false) 
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name = "Last_updated_date" )
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDateTime;

	public String getCommitId() {
		return commitId;
	}

	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

	public Committer getCommitter() {
		return committer;
	}

	public void setCommitter(Committer committer) {
		this.committer = committer;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getCommitMessage() {
		return commitMessage;
	}

	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}
}
