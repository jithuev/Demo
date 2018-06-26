package demo.test.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonRootName;

@Component
//@XmlRootElement(name="sha")
public class CommitEntity {
	String sha;
	String node_id;
	String commitMessage;
	Author author;
	Committer committer;
	
	
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

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public String getNode_id() {
		return node_id;
	}

	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}

	public String getCommitMessage() {
		return commitMessage;
	}

	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}
}
