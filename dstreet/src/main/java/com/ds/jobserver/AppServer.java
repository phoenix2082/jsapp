package com.ds.jobserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Start the job server.
 * @author jr
 *
 */
@SpringBootApplication
public class AppServer {

    /**
     * Main Job Server which receives manages request from front end
     * application to various Jobs provider.
     * @param args - Program Argument
     */
    public static void main(final String[] args) {
        SpringApplication.run(AppServer.class);
    }

}
