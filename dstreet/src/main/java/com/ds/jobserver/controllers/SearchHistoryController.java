package com.ds.jobserver.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ds.jobserver.jpa.search.SearchRepository;
import com.ds.jobserver.jpa.search.entity.SearchHistory;

/**
 * Rest controller to view logs of job searches
 * @author jr
 */
@RestController
public class SearchHistoryController {

	private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    
    /**
     * Search History service.
     */
    @Autowired
    private SearchRepository  searchRepository;

    /**
     * Get list of searches.
     * @return list of search history.
     */
    @GetMapping("/api/searchhistory/all")
    public @ResponseBody Iterable<SearchHistory> get(
    		@RequestParam("size") Optional<Integer> size,
            @RequestParam("page") Optional<Integer> page) {
    	
    	int pSize = size.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int pPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        
        return searchRepository.findAll(PageRequest.of(pPage, pSize));
    }
}
