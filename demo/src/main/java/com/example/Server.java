package com.example;

import java.io.*;
import java.net.*;

public class Server extends Thread{
    Socket client;

    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Server (Socket client){
        this.client = client;
    }

    public void run(){
        try{
            inDalClient = new BufferedReader( new InputStreamReader( client.inDalClient ) );
            outVersoClient  = new DataOutputStream( client.getOutputStream() );

            do{

            } while(true);
        }

        catch ( Exception e ){
            System.out.println( e.getMessage() );
            System.out.println( "Errore durante l'istanza del server !" );
            System.exit( 1 );
        }
    }
}