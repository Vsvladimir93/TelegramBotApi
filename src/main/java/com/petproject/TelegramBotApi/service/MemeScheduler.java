package com.petproject.TelegramBotApi.service;

import org.slf4j.Logger;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
public class MemeScheduler {

    private final Logger logger;
    private final TaskScheduler scheduler;
    private final Map<Long, Task> jobsMap = new HashMap<>();

    public MemeScheduler(Logger logger, TaskScheduler scheduler) {
        this.logger = logger;
        this.scheduler = scheduler;
    }

    public void addTaskToScheduler(Long chatId, CronExpression cron, TimeZone timeZone, Runnable task) {
        jobsMap.put(chatId, new Task(cron, timeZone, task).schedule(scheduler));
        logger.info("Chat: {} added new task to scheduler. CronTime: {} TimeZone: {}", chatId, cron, timeZone);
    }

    public void rescheduleTask(Long chatId, CronExpression cron, TimeZone timeZone) {
        Task task = jobsMap.get(chatId);
        if (task != null) {
            removeTaskFromScheduler(chatId);
            addTaskToScheduler(chatId, cron, timeZone, task.runnableTask);
        } else {
            logger.error("Cannot reschedule task for chat: {}", chatId);
        }
    }

    public void removeTaskFromScheduler(Long chatId) {
        Task task = jobsMap.get(chatId);
        if (task != null) {
            task.cancel();
            jobsMap.put(chatId, null);
            logger.info("Task removed for chat: {}", chatId);
        } else {
            logger.error("Cannot remove task from scheduler for chat: {}", chatId);
        }
    }

    public boolean hasTaskForChat(Long chatId) {
        return jobsMap.containsKey(chatId);
    }

    private static class Task {
        final CronExpression cron;
        final TimeZone timeZone;
        final Runnable runnableTask;
        ScheduledFuture<?> future;

        private Task(CronExpression cron, TimeZone timeZone, Runnable runnableTask) {
            this.cron = cron;
            this.timeZone = timeZone;
            this.runnableTask = runnableTask;
        }

        Task schedule(TaskScheduler scheduler) {
            future = scheduler.schedule(
                    runnableTask,
                    new CronTrigger(cron.toString(), ZoneId.of(timeZone.getID()))
            );
            return this;
        }

        boolean cancel() {
            return future.cancel(true);
        }
    }

}
