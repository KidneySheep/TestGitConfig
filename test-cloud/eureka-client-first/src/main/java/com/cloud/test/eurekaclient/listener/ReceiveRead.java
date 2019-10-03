package com.cloud.test.eurekaclient.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class ReceiveRead implements Runnable{
    private static final Logger LOG = LoggerFactory.getLogger(ReceiveRead.class);
    private BufferedReader reader;

    private Socket socket;

    public ReceiveRead(Socket clientSocket)

    {
        try
        {
// 得到socket连接
            socket = clientSocket;
            // 得到客户端发来的消息
            InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(isReader);
            LOG.info("接收到监听。。。。。。。。。。。。。。。。。。。。。。。。。。。");
//socket.shutdownOutput();
        } catch (Exception e)
        {
            LOG.error("ReceiveRead.ReceiveRead异常:"+e.getMessage(), e);
        }
        String message;
        try
        {
            while((message = reader.readLine()) != null)
            {
                LOG.info("客户端消息: " + message);
            }

        } catch (IOException e)
        {
            LOG.error("ReceiveRead.run异常:"+e.getMessage(), e);

        }
    }

    public void run() {
        try
        {
//发送指令
            OutputStream os=socket.getOutputStream();
            PrintWriter pw=new PrintWriter(os);
            String info="HTTP/1.1 200 OK    \n" +
                    "Server: Microsoft-IIS/4.0    \n" +
                    "Date: Mon, 5 Jan 2019 13:13:33 GMT  Content-Type: application/json    \n" +
                    "Last-Modified: Mon, 5 Jan 2019 13:13:12 GMT  Content-Length: 112   \n" +
                    "\n" +
                    "{  'returnCode': 0,  'returnMessage': '发送短信成功'}     ";
            pw.write(info);
            pw.flush();
            LOG.info("发送出监听。。。。。。。。。。。。。。。。。。。。。。。。。。。");
//socket.shutdownOutput();
        } catch (IOException e)
        {
            LOG.error("ReceiveRead.ReceiveRead异常:"+e.getMessage(), e);
        }
    }
}
