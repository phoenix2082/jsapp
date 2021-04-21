package com.ds.jobserver.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representation of Job Details.
 * @author jr
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobDetail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1553685643859571059L;

    /**
     * Unique Job identifier.
     */
    private String id;

    /**
     * Job type identifier one of - "Full Time" or "Part Time".
     */
    private String type;

    /**
     * URL for the JOB.
     */
    private String url;

    /**
     * Date of Job Posting.
     */
    @JsonProperty("created_at")
    private String createdAt;

    /**
     * Company Name.
     */
    private String company;

    /**
     * URL of the company website.
     */
    private String companyURL;

    /**
     * Location of Job.
     */
    private String location;

    /**
     * Designation of job profile.
     */
    private String title;

    /**
     * Details of Job Description.
     */
    private String description;

    /**
     *
     */
    @JsonProperty("how_to_apply")
    private String howToApply;

    /**
     * Default Constructor.
     */
    public JobDetail() {
        super();
    }

    /**
     * URL of the Company Logo.
     */
    @JsonProperty("company_logo")
    private String companyLogoURL;

    /**
     * Create a Job details instance.
     * @param jobID - Unique Job Identifier
     * @param employmentType - Type of Job
     */
    public JobDetail(final String jobID, final String employmentType) {
        this.id = jobID;
        this.type = employmentType;
    }

    /**
     * Unique Job ID.
     * @return the Job ID
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for job details URL.
     * @return URL of job details.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter for job details URL.
     * @param jobURL URL of the Job.
     */
    public void setUrl(final String jobURL) {
        this.url = jobURL;
    }

    /**
     * Getter for Company Name.
     * @return name of the Company.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set the name of the Company.
     * @param name Company name.
     */
    public void setCompany(final String name) {
        this.company = name;
    }

    /**
     * Getter for Company Website URL.
     * @return URL of the company website.
     */
    public String getCompanyURL() {
        return companyURL;
    }

    /**
     * Setter for Company Website URL.
     * @param websiteURL company website URL.
     */
    public void setCompanyURL(final String websiteURL) {
        this.companyURL = websiteURL;
    }

    /**
     * Getter for location of job.
     * It can be combination of City, State, Country.
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for job location.
     * @param loc city, state or country of job.
     */
    public void setLocation(final String loc) {
        this.location = loc;
    }

    /**
     * Title for job title.
     * @return job posting title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for job title.
     * @param val job title.
     */
    public void setTitle(final String val) {
        this.title = val;
    }

    /**
     * Getter for detailed job description.
     * @return job description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for job description.
     * @param val job description value.
     */
    public void setDescription(final String val) {
        this.description = val;
    }

    /**
     * Setter for job id.
     * @param jobId - unique job id.
     */
    public void setId(final String jobId) {
        this.id = jobId;
    }

    /**
     * Getter for job type.
     * @return type of job.
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for job type.
     * @param jobType type of job.
     */
    public void setType(final String jobType) {
        this.type = jobType;
    }

    /**
     * @return the created_at
     */
    public String getCreatedAt() {

        DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("E MMM dd HH:mm:ss z yyyy");

        LocalDate postedDate = formatter.parse(createdAt, LocalDate :: from);
        Period period = Period.between(postedDate, LocalDate.now());
        String result = "";

        if (period.getYears() > 0) {
            result += period.getYears() + " year, ";
        }

        if (period.getMonths() > 0) {
            result += period.getMonths() + " months, ";
        }

        if (period.getDays() > 0) {
            result += period.getDays() + " days";
        }

        return result;
    }

    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedAt(final String createdOn) {
        this.createdAt = createdOn;
    }

    /**
     * Get details about to How to Apply to this Job Posting.
     * @return details
     */
    public String getHowToApply() {
        return howToApply;
    }

    /**
     * Setter for How to Apply.
     * @param details
     */
    public void setHowToApply(final String details) {
        this.howToApply = details;
    }

    /**
     * Getter for Company Logo.
     * @return URL of the company Logo.
     */
    public String getCompanyLogoURL() {
        return companyLogoURL;
    }

    /**
     * Setter for Company logo.
     * @param logoURL url
     */
    public void setCompanyLogoURL(final String logoURL) {
        this.companyLogoURL = logoURL;
    }
}
