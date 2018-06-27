package demo.test.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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

@Service
public class CommitServiceImpl implements CommitService {

	@Autowired
	Author author;
	
	@Autowired
	Committer committer;
	
	String branchName;
	
	@Override
	public void getCommitHistory(String brachName) {
		this.branchName = brachName;
		
		final String uri = "https://api.github.com/repos/jithuev/Demo/commits?sha="+branchName;
		System.out.println("banchName >>>>> "+branchName);
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
//		String result = result1.replaceAll("\\[", "").replaceAll("\\]","");
		/*System.out.println(result);
		// Create a JaxBContext
				JAXBContext jc;
				try {
					jc = JAXBContext.newInstance(CommitEntity.class);
				

				// Create the Unmarshaller Object using the JaxB Context
				Unmarshaller unmarshaller = jc.createUnmarshaller();

				// Set the Unmarshaller media type to JSON or XML
				unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE,	"application/json");

				// Set it to true if you need to include the JSON root element in the
				// JSON input
				unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);

				// Create the StreamSource by creating StringReader using the JSON input
				StreamSource json = new StreamSource(new StringReader("[{\"sha\":\"e2d66cf37ed1b22e331772e5db5bc9a48cf79fc9\",\"node_id\":\"MDY6Q29tbWl0MTM4NTY2NTc2OmUyZDY2Y2YzN2VkMWIyMmUzMzE3NzJlNWRiNWJjOWE0OGNmNzlmYzk=\",\"commit\":{\"author\":{\"name\":\"jithuev\",\"email\":\"jithuev@gmail.com\",\"date\":\"2018-06-25T13:40:02Z\"},\"committer\":{\"name\":\"jithuev\",\"email\":\"jithuev@gmail.com\",\"date\":\"2018-06-25T13:40:02Z\"},\"message\":\"test commit 4\",\"tree\":{\"sha\":\"ea287900522604bbdac036f7597b80c775b5a302\",\"url\":\"https://api.github.com/repos/jithuev/Demo/git/trees/ea287900522604bbdac036f7597b80c775b5a302\"},\"url\":\"https://api.github.com/repos/jithuev/Demo/git/commits/e2d66cf37ed1b22e331772e5db5bc9a48cf79fc9\",\"comment_count\":0,\"verification\":{\"verified\":false,\"reason\":\"unsigned\",\"signature\":null,\"payload\":null}},\"url\":\"https://api.github.com/repos/jithuev/Demo/commits/e2d66cf37ed1b22e331772e5db5bc9a48cf79fc9\",\"html_url\":\"https://github.com/jithuev/Demo/commit/e2d66cf37ed1b22e331772e5db5bc9a48cf79fc9\",\"comments_url\":\"https://api.github.com/repos/jithuev/Demo/commits/e2d66cf37ed1b22e331772e5db5bc9a48cf79fc9/comments\",\"author\":{\"login\":\"jithuev\",\"id\":40561805,\"node_id\":\"MDQ6VXNlcjQwNTYxODA1\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/40561805?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/jithuev\",\"html_url\":\"https://github.com/jithuev\",\"followers_url\":\"https://api.github.com/users/jithuev/followers\",\"following_url\":\"https://api.github.com/users/jithuev/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/jithuev/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/jithuev/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/jithuev/subscriptions\",\"organizations_url\":\"https://api.github.com/users/jithuev/orgs\",\"repos_url\":\"https://api.github.com/users/jithuev/repos\",\"events_url\":\"https://api.github.com/users/jithuev/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/jithuev/received_events\",\"type\":\"User\",\"site_admin\":false},\"committer\":{\"login\":\"jithuev\",\"id\":40561805,\"node_id\":\"MDQ6VXNlcjQwNTYxODA1\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/40561805?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/jithuev\",\"html_url\":\"https://github.com/jithuev\",\"followers_url\":\"https://api.github.com/users/jithuev/followers\",\"following_url\":\"https://api.github.com/users/jithuev/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/jithuev/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/jithuev/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/jithuev/subscriptions\",\"organizations_url\":\"https://api.github.com/users/jithuev/orgs\",\"repos_url\":\"https://api.github.com/users/jithuev/repos\",\"events_url\":\"https://api.github.com/users/jithuev/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/jithuev/received_events\",\"type\":\"User\",\"site_admin\":false},\"parents\":[{\"sha\":\"03d155d43c1f6e3576bbe7160fcb592a3ddcfacf\",\"url\":\"https://api.github.com/repos/jithuev/Demo/commits/03d155d43c1f6e3576bbe7160fcb592a3ddcfacf\",\"html_url\":\"https://github.com/jithuev/Demo/commit/03d155d43c1f6e3576bbe7160fcb592a3ddcfacf\"}]},{\"sha\":\"03d155d43c1f6e3576bbe7160fcb592a3ddcfacf\",\"node_id\":\"MDY6Q29tbWl0MTM4NTY2NTc2OjAzZDE1NWQ0M2MxZjZlMzU3NmJiZTcxNjBmY2I1OTJhM2RkY2ZhY2Y=\",\"commit\":{\"author\":{\"name\":\"jithuev\",\"email\":\"jithuev@gmail.com\",\"date\":\"2018-06-25T13:33:12Z\"},\"committer\":{\"name\":\"jithuev\",\"email\":\"jithuev@gmail.com\",\"date\":\"2018-06-25T13:33:12Z\"},\"message\":\"test commit 3\",\"tree\":{\"sha\":\"21f54ad3449bd3805b34f994059e6ac0651031c3\",\"url\":\"https://api.github.com/repos/jithuev/Demo/git/trees/21f54ad3449bd3805b34f994059e6ac0651031c3\"},\"url\":\"https://api.github.com/repos/jithuev/Demo/git/commits/03d155d43c1f6e3576bbe7160fcb592a3ddcfacf\",\"comment_count\":0,\"verification\":{\"verified\":false,\"reason\":\"unsigned\",\"signature\":null,\"payload\":null}},\"url\":\"https://api.github.com/repos/jithuev/Demo/commits/03d155d43c1f6e3576bbe7160fcb592a3ddcfacf\",\"html_url\":\"https://github.com/jithuev/Demo/commit/03d155d43c1f6e3576bbe7160fcb592a3ddcfacf\",\"comments_url\":\"https://api.github.com/repos/jithuev/Demo/commits/03d155d43c1f6e3576bbe7160fcb592a3ddcfacf/comments\",\"author\":{\"login\":\"jithuev\",\"id\":40561805,\"node_id\":\"MDQ6VXNlcjQwNTYxODA1\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/40561805?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/jithuev\",\"html_url\":\"https://github.com/jithuev\",\"followers_url\":\"https://api.github.com/users/jithuev/followers\",\"following_url\":\"https://api.github.com/users/jithuev/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/jithuev/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/jithuev/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/jithuev/subscriptions\",\"organizations_url\":\"https://api.github.com/users/jithuev/orgs\",\"repos_url\":\"https://api.github.com/users/jithuev/repos\",\"events_url\":\"https://api.github.com/users/jithuev/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/jithuev/received_events\",\"type\":\"User\",\"site_admin\":false},\"committer\":{\"login\":\"jithuev\",\"id\":40561805,\"node_id\":\"MDQ6VXNlcjQwNTYxODA1\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/40561805?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/jithuev\",\"html_url\":\"https://github.com/jithuev\",\"followers_url\":\"https://api.github.com/users/jithuev/followers\",\"following_url\":\"https://api.github.com/users/jithuev/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/jithuev/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/jithuev/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/jithuev/subscriptions\",\"organizations_url\":\"https://api.github.com/users/jithuev/orgs\",\"repos_url\":\"https://api.github.com/users/jithuev/repos\",\"events_url\":\"https://api.github.com/users/jithuev/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/jithuev/received_events\",\"type\":\"User\",\"site_admin\":false},\"parents\":[{\"sha\":\"a67a137d3a0b6b83dbab24bff8c65c8f40f3ed43\",\"url\":\"https://api.github.com/repos/jithuev/Demo/commits/a67a137d3a0b6b83dbab24bff8c65c8f40f3ed43\",\"html_url\":\"https://github.com/jithuev/Demo/commit/a67a137d3a0b6b83dbab24bff8c65c8f40f3ed43\"}]},{\"sha\":\"a67a137d3a0b6b83dbab24bff8c65c8f40f3ed43\",\"node_id\":\"MDY6Q29tbWl0MTM4NTY2NTc2OmE2N2ExMzdkM2EwYjZiODNkYmFiMjRiZmY4YzY1YzhmNDBmM2VkNDM=\",\"commit\":{\"author\":{\"name\":\"jithuev\",\"email\":\"jithuev@gmail.com\",\"date\":\"2018-06-25T13:30:53Z\"},\"committer\":{\"name\":\"jithuev\",\"email\":\"jithuev@gmail.com\",\"date\":\"2018-06-25T13:30:53Z\"},\"message\":\"test commit 2\",\"tree\":{\"sha\":\"81525b8b978bdb9c70fea33c10da6ee0d6c379d0\",\"url\":\"https://api.github.com/repos/jithuev/Demo/git/trees/81525b8b978bdb9c70fea33c10da6ee0d6c379d0\"},\"url\":\"https://api.github.com/repos/jithuev/Demo/git/commits/a67a137d3a0b6b83dbab24bff8c65c8f40f3ed43\",\"comment_count\":0,\"verification\":{\"verified\":false,\"reason\":\"unsigned\",\"signature\":null,\"payload\":null}},\"url\":\"https://api.github.com/repos/jithuev/Demo/commits/a67a137d3a0b6b83dbab24bff8c65c8f40f3ed43\",\"html_url\":\"https://github.com/jithuev/Demo/commit/a67a137d3a0b6b83dbab24bff8c65c8f40f3ed43\",\"comments_url\":\"https://api.github.com/repos/jithuev/Demo/commits/a67a137d3a0b6b83dbab24bff8c65c8f40f3ed43/comments\",\"author\":{\"login\":\"jithuev\",\"id\":40561805,\"node_id\":\"MDQ6VXNlcjQwNTYxODA1\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/40561805?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/jithuev\",\"html_url\":\"https://github.com/jithuev\",\"followers_url\":\"https://api.github.com/users/jithuev/followers\",\"following_url\":\"https://api.github.com/users/jithuev/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/jithuev/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/jithuev/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/jithuev/subscriptions\",\"organizations_url\":\"https://api.github.com/users/jithuev/orgs\",\"repos_url\":\"https://api.github.com/users/jithuev/repos\",\"events_url\":\"https://api.github.com/users/jithuev/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/jithuev/received_events\",\"type\":\"User\",\"site_admin\":false},\"committer\":{\"login\":\"jithuev\",\"id\":40561805,\"node_id\":\"MDQ6VXNlcjQwNTYxODA1\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/40561805?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/jithuev\",\"html_url\":\"https://github.com/jithuev\",\"followers_url\":\"https://api.github.com/users/jithuev/followers\",\"following_url\":\"https://api.github.com/users/jithuev/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/jithuev/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/jithuev/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/jithuev/subscriptions\",\"organizations_url\":\"https://api.github.com/users/jithuev/orgs\",\"repos_url\":\"https://api.github.com/users/jithuev/repos\",\"events_url\":\"https://api.github.com/users/jithuev/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/jithuev/received_events\",\"type\":\"User\",\"site_admin\":false},\"parents\":[{\"sha\":\"09a9c302a4dedd1ebfbae01fddfe09552b31f88d\",\"url\":\"https://api.github.com/repos/jithuev/Demo/commits/09a9c302a4dedd1ebfbae01fddfe09552b31f88d\",\"html_url\":\"https://github.com/jithuev/Demo/commit/09a9c302a4dedd1ebfbae01fddfe09552b31f88d\"}]},{\"sha\":\"09a9c302a4dedd1ebfbae01fddfe09552b31f88d\",\"node_id\":\"MDY6Q29tbWl0MTM4NTY2NTc2OjA5YTljMzAyYTRkZWRkMWViZmJhZTAxZmRkZmUwOTU1MmIzMWY4OGQ=\",\"commit\":{\"author\":{\"name\":\"jithuev\",\"email\":\"jithuev@gmail.com\",\"date\":\"2018-06-25T10:26:38Z\"},\"committer\":{\"name\":\"jithuev\",\"email\":\"jithuev@gmail.com\",\"date\":\"2018-06-25T10:26:38Z\"},\"message\":\"Initial commit\",\"tree\":{\"sha\":\"02a388f34208f6681352ec935584ce37b436c065\",\"url\":\"https://api.github.com/repos/jithuev/Demo/git/trees/02a388f34208f6681352ec935584ce37b436c065\"},\"url\":\"https://api.github.com/repos/jithuev/Demo/git/commits/09a9c302a4dedd1ebfbae01fddfe09552b31f88d\",\"comment_count\":0,\"verification\":{\"verified\":false,\"reason\":\"unsigned\",\"signature\":null,\"payload\":null}},\"url\":\"https://api.github.com/repos/jithuev/Demo/commits/09a9c302a4dedd1ebfbae01fddfe09552b31f88d\",\"html_url\":\"https://github.com/jithuev/Demo/commit/09a9c302a4dedd1ebfbae01fddfe09552b31f88d\",\"comments_url\":\"https://api.github.com/repos/jithuev/Demo/commits/09a9c302a4dedd1ebfbae01fddfe09552b31f88d/comments\",\"author\":{\"login\":\"jithuev\",\"id\":40561805,\"node_id\":\"MDQ6VXNlcjQwNTYxODA1\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/40561805?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/jithuev\",\"html_url\":\"https://github.com/jithuev\",\"followers_url\":\"https://api.github.com/users/jithuev/followers\",\"following_url\":\"https://api.github.com/users/jithuev/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/jithuev/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/jithuev/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/jithuev/subscriptions\",\"organizations_url\":\"https://api.github.com/users/jithuev/orgs\",\"repos_url\":\"https://api.github.com/users/jithuev/repos\",\"events_url\":\"https://api.github.com/users/jithuev/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/jithuev/received_events\",\"type\":\"User\",\"site_admin\":false},\"committer\":{\"login\":\"jithuev\",\"id\":40561805,\"node_id\":\"MDQ6VXNlcjQwNTYxODA1\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/40561805?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/jithuev\",\"html_url\":\"https://github.com/jithuev\",\"followers_url\":\"https://api.github.com/users/jithuev/followers\",\"following_url\":\"https://api.github.com/users/jithuev/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/jithuev/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/jithuev/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/jithuev/subscriptions\",\"organizations_url\":\"https://api.github.com/users/jithuev/orgs\",\"repos_url\":\"https://api.github.com/users/jithuev/repos\",\"events_url\":\"https://api.github.com/users/jithuev/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/jithuev/received_events\",\"type\":\"User\",\"site_admin\":false},\"parents\":[]}]"));
//				List<JAXBElement> elements = new ArrayList<>()
				CommitEntity element =  unmarshaller.unmarshal(json, CommitEntity.class).getValue();
//				Object obj = element.getValue();
				
				System.out.println("as");
				// Getting the employee pojo again from the json
//				CommitEntity employee = unmarshaller.unmarshal(json, CommitEntity.class).getValue();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Print the employee data to console
//				System.out.println("Employee Id: " + employee.getId());
//				System.out.println("\nEmployee Name: " + employee.getName());
//				System.out.println("\nEmployee Skills: "
//						+ StringUtils.join(employee.getSkills(), ','));
			}*/
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
		System.out.println("commitdetails list : "+commitDetailsList.size());
		System.out.println(result);
	}
}
