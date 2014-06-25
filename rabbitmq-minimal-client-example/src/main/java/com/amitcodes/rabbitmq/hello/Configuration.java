package com.amitcodes.rabbitmq.hello;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration
{
    Properties props;

    public Configuration(File config)
    {
        readProperties(config);
    }

    private void readProperties(File propertiesFile)
    {
        props = new Properties();
        try (InputStream propsFileStream = new FileInputStream(propertiesFile)) {
            props.load(propsFileStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load properties file" + propertiesFile.toString(), e);
        }
    }

    public String getProperty(String name)
    {
        return props.getProperty(name);
    }

    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Configuration{");
        sb.append("props=").append(props);
        sb.append('}');
        return sb.toString();
    }
}
