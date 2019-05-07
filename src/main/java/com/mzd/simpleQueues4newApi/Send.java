package com.mzd.simpleQueues4newApi;

import com.alibaba.fastjson.JSON;
import com.mzd.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class Send {

    /**
     * 生产者---------队列--------消费者
     */
    private static final String Queues_Name = "BCH_MACHINE_QUEUENAME_3009999981";

    public static void main(String[] args) throws IOException, TimeoutException {
        HashMap hashMap = new HashMap();
        Long time = System.currentTimeMillis();
        hashMap.put("current_time", time);

        hashMap.put("order_sn", "123");

        hashMap.put("cmd", "open_door");

        //第几扇门
        hashMap.put("door_no", "1");

        hashMap.put("order_type", "1");
        String str11 = "{\n" +
                "  \"machineSn\": \"3009999981\",\n" +
                "  \"timestamp\": \"1555482931\",\n" +
                "  \"gridNo\": \"2\",\n" +
                "  \"cmd\": \"open_door\"\n" +
                "}";
        String str = JSON.toJSONString(hashMap);
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Queues_Name, true, false, false, null);
        channel.basicPublish("", Queues_Name, null, str11.getBytes());
        System.out.println("发送消息");
        channel.close();
        connection.close();
    }
}
