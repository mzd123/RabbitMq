package com.mzd.workQueues4robin;

import com.mzd.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 轮询分发
 */
public class Send {

    /**
     *                             \--------消费者1
     * 生产者---------队列---------\
     *                             \--------消费者2
     */


    private static final String Queues_Name = "test_02";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Queues_Name, true, false, false, null);
        for (int i = 0; i < 100; i++) {
            Thread.sleep(100);
            System.out.println("hello_" + i);
            channel.basicPublish("", Queues_Name, null, ("hello_" + i).getBytes());
        }

        System.out.println("发送消息完毕");
        channel.close();
        connection.close();
    }
}
