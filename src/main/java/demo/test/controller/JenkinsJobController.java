package demo.test.controller;

import demo.test.service.JenkinsJobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JenkinsJobController {

    @Autowired
    JenkinsJobServiceImpl jenkinsJobServiceImpl;

    @RequestMapping(path = "/getJenkinsJobs", method = RequestMethod.POST)
    public void getJobs() {
        jenkinsJobServiceImpl.getJobs();
    }

    @RequestMapping(path = "/getJenkinsJob", method = RequestMethod.POST)
    public void getJob(@RequestParam("jobName") String jobName) {

        jenkinsJobServiceImpl.getJob(jobName);
    }
}

