package com.mdsech.sample.jsf;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogEvent {

    private static final LogEvent logEvent = new LogEvent();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final Logger log = LoggerFactory.getLogger(LogEvent.class);

    public <T> void sendEvent(T t) {
        log.info("Called sendEvent "+ t.toString());
        System.out.println("Called sendEvent "+ t.toString());
        CompletableFuture.runAsync(() -> {
            log.info(Thread.currentThread().getName()+": Sample event from caller "+ t.toString());
            System.out.println(Thread.currentThread().getName()+": Sample event from caller "+ t.toString());
        }, executorService);
    }

    public static LogEvent getInstance() {
        return logEvent;
    }
}
