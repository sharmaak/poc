package com.amitcodes.rabbitmq.hello.si;

import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.Payload;

public interface SISender
{
    public void push(@Header(value = "routing_key") String q,  @Payload String p);
}
