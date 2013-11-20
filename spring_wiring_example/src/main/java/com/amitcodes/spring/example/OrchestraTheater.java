package com.amitcodes.spring.example;

import com.amitcodes.spring.example.service.Orchestra;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrchestraTheater {

    private Orchestra orchestra;

    public OrchestraTheater() {
        ClassPathXmlApplicationContext appContext =
                new ClassPathXmlApplicationContext("/spring-config-services.xml");
        orchestra = appContext.getBean("orchestra.BangaloreOrchestra", Orchestra.class);
    }

    public String broadcastPerformance() {
        return orchestra.perform();
    }

}
