package com.schedule;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    private static final Logger log = Logger.getLogger(ScheduledTask.class);

//
//    @Scheduled(cron = "0 */#{T(com.util.DateUtil).generateRandomMinutes()} * * * ?")
//    public void createRandomScheduleTask() {
//        log.error("Task running every 5-20 min");
//    }

}


