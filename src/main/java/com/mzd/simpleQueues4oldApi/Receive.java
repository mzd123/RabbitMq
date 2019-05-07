package com.mzd.simpleQueues4oldApi;

import com.mzd.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;


public class Receive {

    private static final String Queues_Name = "test_01";

    public static void main(String[] args) throws Exception {
        System.out.println("开启");
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        channel.basicConsume(Queues_Name, true, queueingConsumer);
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String str = new String(delivery.getBody());
            System.out.println("接受到消息：" + str);
        }
    }

}
