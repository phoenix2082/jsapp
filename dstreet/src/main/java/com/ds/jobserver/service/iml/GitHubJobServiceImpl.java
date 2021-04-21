package com.ds.jobserver.service.iml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ds.jobserver.dto.JobDetail;
import com.ds.jobserver.service.GitHubJobService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GitHubJobServiceImpl implements GitHubJobService {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(GitHubJobServiceImpl.class);

    /**
     * Rest template client to process request and response from endpoint.
     */
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public final List<JobDetail> findJobs(final String location,
            final String description) {
        String endpoint =
                "https://jobs.github.com/positions.json";
        List<JobDetail> result = new ArrayList<JobDetail>();

        if (location != null && !location.isEmpty()) {
            endpoint += "?location=" + location;
        }

        if (description != null && !description.isEmpty()) {

            if (endpoint.indexOf("?") != -1) {
                endpoint += "&description=" + description;
            } else {
                endpoint += "?description=" + description;
            }
        }

        try {
            ResponseEntity<Object[]> responseEntity = restTemplate
                    .getForEntity(endpoint, Object[].class);
            Object[] objects = responseEntity.getBody();
            result = processResult(objects);
        } catch (Exception e) {
            LOG.error("HTTP Endpoint Error", e);
        }
        return result;

    }

    /**
     * Process response received from JobSource and convert to JobDetails.
     * @param response Response received from End point
     * @return list of JobDetails
     */
    private List<JobDetail> processResult(final Object[] response) {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(response)
                .map(object ->
                mapper.convertValue(object, JobDetail.class))
            .collect(Collectors.toList());
    }

}
