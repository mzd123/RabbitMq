package com.mzd.utils;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtils {

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("127.0.0.1");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/test_db");
//        connectionFactory.setUsername("mzd");
//        connectionFactory.setPassword("123");


        connectionFactory.setHost("bmmq.zongs365.cc");
        connectionFactory.setPort(40762);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("zongsmq");
        connectionFactory.setPassword("zongsMQpwd");
        Connection connection = connectionFactory.newConnection();
        return connection;
    }

}
