package com.mzd.simpleQueues4oldApi;

import com.mzd.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {


    /**
     * 生产者---------队列--------消费者
     */
    private static final String Queues_Name = "test_01";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Queues_Name, true, false, false, null);
        channel.basicPublish("", Queues_Name, null, "hello".getBytes());
        System.out.println("发送消息");
        channel.close();
        connection.close();
    }
}
