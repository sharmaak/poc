package com.amitcodes.rabbitmq.hello.si;

import com.amitcodes.rabbitmq.hello.AbstractLauncher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

/**
 * Launcher class for Vault Import Writer
 */
public class SIReceiverLauncher extends AbstractLauncher
{
    private static Logger logger = Logger.getLogger(SIReceiverLauncher.class.getCanonicalName());

    public static void main(String... args)
    {
        try {
            // 1. Check execution properties file exists and is valid
            File configFile = new File(args[0]);
            if (!configFile.exists() || !configFile.isFile()) {
                throw new FileNotFoundException("Unable to locate input config file: " + configFile.getCanonicalPath());
            }
            SIReceiverLauncher reader = new SIReceiverLauncher();
            reader.init(configFile, "receiver.xml");
            reader.launch();
        } catch (Throwable throwable) {
            System.out.println("[FATAL ERROR]: *** Receiver launch failed! ***");
            throwable.printStackTrace();
            System.exit(-1);
        }
    }

    protected void launch() {
        logger.info("[Launch Sequence]: Completed.");
    }
}
