package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        System.out.println("Accepted connection : " + socket);

        final int prefSize = 1024;
        byte[] byteArray = new byte[prefSize];

        InputStream inputStream = socket.getInputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("output.jpg"));

        for (int bytesRead = inputStream.read(byteArray, 0, byteArray.length); bytesRead != -1;) {
            bufferedOutputStream.write(byteArray, 0, bytesRead);
            bytesRead = inputStream.read(byteArray, 0, byteArray.length);
        }

        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        socket.close();

        System.out.println("File transfer complete");
    }
}
