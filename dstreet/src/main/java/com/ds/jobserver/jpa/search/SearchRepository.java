package com.ds.jobserver.jpa.search;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ds.jobserver.jpa.search.entity.SearchHistory;

/**
 * Repository service for accessing job search history record.
 * @author jr
 */
public interface SearchRepository extends
    PagingAndSortingRepository<SearchHistory, Long> {

}
