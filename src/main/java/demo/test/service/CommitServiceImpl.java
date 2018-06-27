package demo.test.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import demo.test.entity.Author;
import demo.test.entity.CommitEntity;
import demo.test.entity.Committer;
import demo.test.repository.CommitRepo;

@Service
public class CommitServiceImpl implements CommitService {

	@Autowired
	Author author;
	
	@Autowired
	Committer committer;
	
	String branchName;
	
	@Autowired
    DataSource dataSource;
	
	@Autowired
	private CommitRepo commitRepo;
	
	@Override
	public void getCommitHistory(String brachName) {
		this.branchName = brachName;
		
		final String uri = "https://api.github.com/repos/jithuev/Demo/commits?sha="+branchName;
		System.out.println("banchName >>>>> "+branchName);
		System.out.println("dataSource >>>>> "+dataSource);
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		Gson gson = new GsonBuilder().create();
		JSONParser parse = new JSONParser();
		JSONObject josnObj = new JSONObject();
		JSONObject jsonObj1 = new JSONObject();
		
		JSONArray jsonArray;
		List<CommitEntity> commitDetailsList = new ArrayList<>();
		
		try {
			jsonArray = (JSONArray) parse.parse(result);
			for (int i = 0; i < jsonArray.size(); i++) {
				CommitEntity commit = new CommitEntity();
				josnObj = (JSONObject) jsonArray.get(i);
				jsonObj1 = (JSONObject) josnObj.get("commit");
				commit.setCommitMessage(jsonObj1.get("message").toString());
				commit.setSha(josnObj.get("sha").toString());
				author = gson.fromJson(jsonObj1.get("author").toString(), Author.class);
				committer = gson.fromJson(jsonObj1.get("committer").toString(), Committer.class);
				commit.setAuthor(author);
				commit.setCommitter(committer);
				System.out.println("\nmessage from json parsing: " + jsonObj1.get("message"));
				commitDetailsList.add(commit);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		commitRepo.saveAll(commitDetailsList);
		commitRepo.flush();
		
		System.out.println("commitdetails list : "+commitDetailsList.size());
		System.out.println(result);
	}
}
