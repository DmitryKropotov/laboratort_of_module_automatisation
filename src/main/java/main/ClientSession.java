package main;

import http.HttpRequest;
import http.HttpResponse;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSession implements Runnable {
    private Socket socket;

    public ClientSession(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
         try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
             //parse request into request object
             HttpRequest req = new HttpRequest(br);
             //generate response to the request
             HttpResponse resp = new HttpResponse(req);
             //write response into the socket output stream
             try (BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream())) {
                 resp.write(os);
             }


         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
