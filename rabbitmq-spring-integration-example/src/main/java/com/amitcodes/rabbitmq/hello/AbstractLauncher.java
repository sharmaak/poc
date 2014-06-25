package com.amitcodes.rabbitmq.hello;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.MutablePropertySources;

import java.io.File;
import java.util.logging.Logger;

public abstract class AbstractLauncher
{
    private static Logger logger = Logger.getLogger(AbstractLauncher.class.getCanonicalName());
    protected ApplicationContext context;

    protected void init(File configFile, String... springConfigFiles)
    {
        // 1. Init and validate exec config
        Configuration config = new Configuration(configFile);
        System.out.println("config = " + config);
        // 2. Init spring application context
        initAppContext(config, springConfigFiles);
    }

    protected void initAppContext(Configuration readerConfig,String... springConfigFiles)
    {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(springConfigFiles, false);
        MutablePropertySources sources = context.getEnvironment().getPropertySources();

        sources.addFirst(readerConfig);
        context.refresh();
        this.context = context;
        logger.info("[Spring context]: refresh completed.");
    }


    protected abstract void launch();

}
