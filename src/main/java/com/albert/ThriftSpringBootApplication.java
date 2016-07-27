package com.albert;

import com.albert.config.AppConfig;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ThriftSpringBootApplication {

    private static Logger logger = Logger.getLogger(ThriftSpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ThriftSpringBootApplication.class, args);

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ThriftServer thriftServer = (ThriftServer) context.getBean("thriftServer");
		thriftServer.startServer();
	}
}
