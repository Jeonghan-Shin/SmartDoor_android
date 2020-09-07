package com.example.capstone3;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClientTask extends AsyncTask<Void, Void, Void> {

    String address;
    int port;
    String response = "";
    String msg = "";

    Socket socket = null;

    public MyClientTask(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public MyClientTask(String address, int port, String msg) {
        this.address = address;
        this.port = port;
        this.msg = msg;
    }

    public void sendMsg(String msg) {
        try {
            OutputStream out = socket.getOutputStream();
            out.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            socket = new Socket(address, port);

            OutputStream out = socket.getOutputStream();
            out.write(msg.getBytes());

//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            InputStream inputStream = socket.getInputStream();
//
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//
//                byteArrayOutputStream.write(buffer, 0, bytesRead);
//                response += byteArrayOutputStream.toString("UTF-8");
//            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

}