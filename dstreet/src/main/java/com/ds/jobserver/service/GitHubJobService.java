package com.ds.jobserver.service;

import java.util.List;

import com.ds.jobserver.dto.JobDetail;

public interface GitHubJobService {

    /**
     * Find the Job from Remote Endpoint.
     * @param location location of job.
     * @param description job description
     * @return all the jobs meeting the criteria
     */
    List<JobDetail> findJobs(String location, String description);
}
