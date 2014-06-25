package com.amitcodes.rabbitmq.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Sender
{
    private Configuration config;

    public Sender(Configuration config)
    {
        this.config = config;
    }

    void send() throws IOException
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(config.getProperty("rabbitmq.host"));
        factory.setPort(Integer.parseInt(config.getProperty("rabbitmq.port")));
        factory.setVirtualHost(config.getProperty("rabbitmq.vhost"));
        factory.setUsername(config.getProperty("rabbitmq.user"));
        factory.setPassword(config.getProperty("rabbitmq.password"));

        Connection conn = null;
        Channel channel = null;
        try {
            conn = factory.newConnection();
            channel = conn.createChannel();
            channel.queueDeclare(config.getProperty("rabbitmq.queue.name"), true, false, false, null);
            System.out.println("Publishing messages to exchange. Press ^C to terminate.");
            while(true) {
                channel.basicPublish("", config.getProperty("rabbitmq.queue.name"), null, msgBytes);
            }
        } finally {
            if (channel != null) { channel.close();}
            if (conn != null) {conn.close();}
        }
    }
    byte[] msgBytes = "{{\"name\": \"John Doe\", \"age\": 30, \"sex\": \"tranny\", \"whatever\": \"however\"}}"
            .getBytes(StandardCharsets.UTF_8);

    public static void main(String[] args) throws IOException
    {
        Configuration config = new Configuration(new File(args[0]));
        new Sender(config).send();
    }
}
