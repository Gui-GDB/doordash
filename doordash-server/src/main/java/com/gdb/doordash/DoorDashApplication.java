package com.gdb.doordash;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//开启注解方式的事务管理
@EnableTransactionManagement
@Slf4j
public class DoorDashApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoorDashApplication.class, args);
        log.info("server started");
    }
}
