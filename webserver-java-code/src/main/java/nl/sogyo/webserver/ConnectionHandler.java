package nl.sogyo.webserver;

import org.omg.CORBA.portable.ResponseHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ConnectionHandler implements Runnable {
    private Socket socket;

    public ConnectionHandler(Socket toHandle) {
        this.socket = toHandle;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = reader.readLine();
            System.out.println();
            List<String> requestData = new ArrayList<String>();

            while(!reader.ready()){
                Thread.sleep(10);
            }

            while (!line.isEmpty()) {
                requestData.add(line);
                System.out.println(line);
                line = reader.readLine();
            }
            RequestMessage req = new RequestMessage(requestData);
            ResponseMessage res = new ResponseMessage(req);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(res.getRes());
            writer.flush();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        try {
            ServerSocket socket = new ServerSocket(9090);
            while(true) {
                Socket newConnection = socket.accept();
                Thread t = new Thread(new ConnectionHandler(newConnection));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}