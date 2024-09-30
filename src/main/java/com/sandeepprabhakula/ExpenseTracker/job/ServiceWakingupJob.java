package com.sandeepprabhakula.ExpenseTracker.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class ServiceWakingupJob {

    @Autowired
    RestTemplate restTemplate;

    private final Logger log = LoggerFactory.getLogger(ServiceWakingupJob.class);

    @Scheduled(cron = "0 */12 * * * ?")
    public void getBlog(){
        try{
            String url = "https://expensetracker-mbqurkyj.b4a.run/users/job";
            String response = restTemplate.getForObject(url, String.class);
            log.info("I'm awake {}{}", response, new Date());
        }catch (Exception e){
            System.out.println(e.getMessage() + new Date());
        }
    }
}
