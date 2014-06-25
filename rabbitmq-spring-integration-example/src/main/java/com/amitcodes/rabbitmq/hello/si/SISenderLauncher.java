package com.amitcodes.rabbitmq.hello.si;

import com.amitcodes.rabbitmq.hello.AbstractLauncher;

import java.io.File;
import java.io.FileNotFoundException;

public class SISenderLauncher extends AbstractLauncher
{
    public static void main(String... args)
    {
        try {
            // 1. Check execution properties file exists and is valid
            File configFile = new File(args[0]);
            if (!configFile.exists() || !configFile.isFile()) {
                throw new FileNotFoundException(
                        "Unable to locate input config file: " + configFile.getCanonicalPath());
            }
            SISenderLauncher sender = new SISenderLauncher();
            sender.init(configFile, "sender.xml");
            sender.launch();
        } catch (Throwable throwable) {
            System.out.println("[FATAL ERROR]: *** Receiver launch failed! ***");
            throwable.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    protected void init(File configFile, String... springConfigFiles)
    {
        super.init(configFile, springConfigFiles);
    }

    protected void launch()
    {
        SISender sender = context.getBean("theGateway", SISender.class);
        String payload = "{name: \"John Doe\", age: 30, sex: \"male\"}";
        System.out.println("Publishing messages to Exchange. Press ^C to terminate");
        while (true) {
            sender.push("bmq", payload);
        }
    }

}

