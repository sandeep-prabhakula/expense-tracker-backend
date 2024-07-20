package com.sandeepprabhakula.ExpenseTracker.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class ServiceWakingupJob {

    @Autowired
    RestTemplate restTemplate;

    @Scheduled(cron = "0 */12 * * * ?")
    public void getBlog(){
        try{
            String url = "https://expensetracker-mbqurkyj.b4a.run/users/job";
            String response = restTemplate.getForObject(url, String.class);
            System.out.println("I'm awake " + new Date());
        }catch (Exception e){
            System.out.println(e.getMessage() + new Date());
        }
    }
}
