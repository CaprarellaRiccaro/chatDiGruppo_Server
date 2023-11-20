package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Server extends Thread{
    Socket client;

    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Server (Socket client){
        this.client = client;
    }

    public void run(){
        try{
            inDalClient = new BufferedReader( new InputStreamReader( client.getInputStream() ) );
            outVersoClient  = new DataOutputStream( client.getOutputStream() );

            do{
                String st = inDalClient.readLine();
                System.out.println(st);
                String stringaClient = "messaggio ricevuto";
  
            } while(true);
        }

        catch ( Exception e ){
            System.out.println( e.getMessage() );
            System.out.println( "Errore durante l'istanza del server !" );
            System.exit( 1 );
        }
    }
}