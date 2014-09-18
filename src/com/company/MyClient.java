package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class MyClient {
    public static void main (String [] args ) throws IOException {
        InetAddress ip = InetAddress.getByName(args[0]);
        int port = Integer.parseInt(args[1]);
        String path = args[2];
        Socket socket = new Socket(ip, port);
        File transferFile = new File(path);

        final int prefSize = 1024;
        byte[] byteArray = new byte [prefSize];

        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(transferFile));

        OutputStream os = socket.getOutputStream();
        System.out.println("Sending Files...");
        for (int bytesRead = bin.read(byteArray, 0, byteArray.length); bytesRead != -1;) {
            os.write(byteArray, 0, bytesRead);
            bytesRead = bin.read(byteArray, 0, byteArray.length);
        }
        os.flush();
        socket.close();
    }
}
