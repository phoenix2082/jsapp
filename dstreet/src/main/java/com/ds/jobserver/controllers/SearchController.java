/**
 *
 */
package com.ds.jobserver.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ds.jobserver.dto.JobDetail;
import com.ds.jobserver.jpa.search.SearchRepository;
import com.ds.jobserver.jpa.search.entity.SearchHistory;
import com.ds.jobserver.service.GitHubJobService;

/**
 * @author jr
 *
 */
@RestController
public class SearchController {

    /**
     * Logger.
     */
    private static final Logger LOG =
            LoggerFactory.getLogger(SearchController.class);

    /**
     * JobService.
     */
    @Autowired
    private GitHubJobService  jobService;
    
    @Autowired
    private SearchRepository  searchRepository;

    /**
     * Utility for Rest request/response handling.
     * @param builder Builder used for RestTemplate creation.
     * @return rest client.
     */
    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * Search Job.
     * @param location Location usually a City.
     * @param description Job Description.
     * @return List of Jobs.
     */
    @GetMapping("/api/jobs")
    public List<JobDetail> find(
            final @RequestParam(value = "location",
                required = false) String location,
            final @RequestParam(value = "description",
            required = false) String description,
            @RequestHeader HttpHeaders headers,
            @RequestHeader Map<String, String> reqHeaders) {

        SearchHistory sh = new SearchHistory();
        String ipAddress = reqHeaders.get("X-FORWARDED-FOR");
        if( ipAddress == null) {
        	ipAddress = reqHeaders.get("REMOTE_ADDR");
        }
        sh.setLocation(location);
        sh.setDescription(description);
        sh.setSearchOn(new Date());
        sh.setIpAddress(ipAddress);
        searchRepository.save(sh);

        return jobService.findJobs(location, description);
    }
    
}
