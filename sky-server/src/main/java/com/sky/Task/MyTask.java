package com.sky.Task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
//@Component
public class MyTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void execute(){
        log.info("定时任务开始执行{}",new Date());
    }
}
