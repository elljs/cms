package com.ell.cms.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ell.cms.task.SampleTask;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail sampleTaskDetail() {
        return JobBuilder.newJob(SampleTask.class)
                .withIdentity("sample-task")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleTaskTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10) // 每10秒执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(sampleTaskDetail())
                .withIdentity("sample-task-trigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
