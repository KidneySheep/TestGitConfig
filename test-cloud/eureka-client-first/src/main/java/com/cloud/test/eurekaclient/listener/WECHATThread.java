package com.cloud.test.eurekaclient.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WECHATThread extends Thread {
    private static final Logger LOG = LoggerFactory.getLogger(WECHATThread.class);
    public void run() {

        int GPSport=8081;

        LOG.info("-----------------GPSport:"+GPSport+"---------------------------");

        try {
            ServerSocket serverSocket = new ServerSocket(GPSport);
// 轮流等待请求
            while(true)
            {
// 等待客户端请求,无请求则闲置;有请求到来时,返回一个对该请求的socket连接
                Socket clientSocket = serverSocket.accept();
                Thread t = new Thread(new ReceiveRead(clientSocket));
                t.start();
// 创建zithread对象,通过socket连接通信
                LOG.info("接收到监听WECHATThread8081");
            }

        } catch (IOException e) {
// TODO Auto-generated catch block
            LOG.error("WECHATThread.run异常:"+e.getMessage(), e);
        }

    }

}
