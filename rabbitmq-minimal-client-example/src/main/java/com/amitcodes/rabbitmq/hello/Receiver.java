package com.amitcodes.rabbitmq.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Receiver
{
    private static final Logger logger = Logger.getLogger(Receiver.class.getCanonicalName());
    private Configuration config;

    public Receiver(Configuration config)
    {
        this.config = config;
    }

    void receive() throws IOException, InterruptedException
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
            System.out.println("[*] Waiting for messages. To exit press CTRL+C");;
            channel.basicQos(Integer.parseInt(config.getProperty("spring.amqp.prefetch.count")));

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(config.getProperty("rabbitmq.queue.name"), true, consumer);

            while(true){
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                //String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
            }
        } finally {
            if (channel != null) {
                if (channel.isOpen()) {channel.close();}
            }
            if (conn != null) {
                if (conn.isOpen()) {conn.close();}
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException
    {
        Configuration config = new Configuration(new File(args[0]));
        new Receiver(config).receive();
    }
}
