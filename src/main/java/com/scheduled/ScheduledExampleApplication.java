package com.scheduled;

import com.scheduled.repository.PlaneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
@Slf4j
public class ScheduledExampleApplication {

    @Autowired
    PlaneRepository planeRepository;

    public static void main(String[] args) {
        SpringApplication.run(ScheduledExampleApplication.class, args);
        welcomeText();
    }

    @Scheduled(initialDelay = 2000L, fixedDelayString = "${executing.delay}")
    void appChecker() {
        log.info("Current time : " + new Date());
        log.info("App condition ok...");
    }

    @Scheduled(fixedDelayString = "${executing.count}")
    void checkDatabase() {
        log.info("Checking database..." );
        int count = planeRepository.findAll().size();
        log.info("You have {} planes in your DB.", count);
    }

    @Scheduled(fixedDelayString = "${executing.erase}")
    void cleaningDatabase() {
        log.info("Current time : " + new Date());
        log.warn("Ok, enough playing. We are deleting all planes from db.");
        planeRepository.deleteAll();
    }

    private static void welcomeText() {
        log.info("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        log.info("*              This is simple crud application with Scheduler.                      *");
        log.info("*    When you start the app, create plane first and play with it but be careful.    *");
        log.warn("*                Database will be erased after 20 minutes.                          *");
        log.info("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }

}

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
class SchedulingConfiguration {

}
