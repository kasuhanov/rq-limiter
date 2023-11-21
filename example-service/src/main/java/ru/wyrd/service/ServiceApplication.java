package ru.wyrd.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ru.wyrd.limiter.Limit;

@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    @Limit
//    public void startup() {
//        System.out.println("hello");
//    }
}
