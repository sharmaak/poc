package com.amitcodes.rabbitmq.hello.si;

import org.springframework.integration.annotation.Payload;

import java.io.IOException;
import java.util.logging.Logger;

public class SIReceiver
{
    private static final Logger logger = Logger.getLogger(SIReceiver.class.getCanonicalName());

    public SIReceiver()
    {
    }

    public void receive(@Payload String migrationRecord) throws IOException, InterruptedException
    {
        // No op method
    }

}
