package com.ds.jobserver.jpa.search.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Record for user job search history.
 * @author jr
 */
@Entity
public class SearchHistory {

    /**
     * Unique Identifier for search record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Time of job search.
     */
    @Column(name = "searched_on")
    private Date searchOn;

    /**
     * Job Description used for search.
     */
    @Column(name = "description")
    private String description;

    /**
     * Location criteria used for search.
     */
    @Column(name = "location")
    private String location;

    /**
     * IP Address of client.
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * Getter for Search Record ID.
     * @return search id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for search record id.
     * @param searchId - search id
     */
    public void setId(final Long searchId) {
        this.id = searchId;
    }

    /**
     * Getter for date when search is performed.
     * @return date of search.
     */
    public Date getSearchOn() {
        return searchOn;
    }

    /**
     * Setter for date when search is performed.
     * @param date - date of search
     */
    public void setSearchOn(final Date date) {
        this.searchOn = date;
    }

    /**
     * Getter for job description used for search.
     * @return job description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for job description used for search.
     * @param desc - job description
     */
    public void setDescription(final String desc) {
        this.description = desc;
    }

    /**
     * Getter for job location used for search.
     * @return - location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for job location for search.
     * @param loc - job location.
     */
    public void setLocation(final String loc) {
        this.location = loc;
    }

    /**
     * Getter for end user IP Address.
     * @return IP Address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Setter for IP Address.
     * @param ip - IP Address of request origin.
     */
    public void setIpAddress(final String ip) {
        this.ipAddress = ip;
    }

}
