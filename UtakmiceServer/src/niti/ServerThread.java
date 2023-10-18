/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private List<ClientThread> list;

    public ServerThread(int port) {
        try {
            list = new LinkedList<>();
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Socket socket = serverSocket.accept();
                ClientThread thc = new ClientThread(socket);
                thc.start();
                list.add(thc);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<ClientThread> getList() {
        return list;
    }

    public void zaustaviServer() {
        try{
            for(ClientThread clientThread: list){
                clientThread.zaustaviKlijenta();
            }
            this.interrupt();
            serverSocket.close();
        }catch(Exception ex){
            
        }
    }

}
