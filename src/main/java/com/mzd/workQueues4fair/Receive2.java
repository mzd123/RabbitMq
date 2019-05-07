package com.mzd.workQueues4fair;

import com.mzd.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;


public class Receive2 {

    private static final String Queues_Name = "test_02";

    public static void main(String[] args) throws Exception {
        System.out.println("开启Receive2");
        Connection connection = ConnectionUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(Queues_Name, true, false, false, null);
        //每个消费者，在确认消息被消费之前，消息队列不会向消费者发送消息，即：一次只发送predfetchCount个消息给消费者
        int predfetchCount = 1;
        channel.basicQos(predfetchCount);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            //一旦有消息进入队列，就会被触发，事件模型
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String str = new String(body, "utf-8");
                System.out.println("Receive2收到消息：" + str);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        boolean ack=false;
        //监听队列
        channel.basicConsume(Queues_Name, ack, defaultConsumer);
    }

}
