package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
    Socket client;

    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    String st;

    ArrayList nomeThread = new ArrayList<String>();


    public Server (Socket client){
        this.client = client;
    }

    public void run(){
        try{
            inDalClient = new BufferedReader( new InputStreamReader( client.getInputStream() ) );
            outVersoClient  = new DataOutputStream( client.getOutputStream() );
            boolean condizioneUscita = false;



            st = inDalClient.readLine();
            System.out.println(st);
            nomeThread.add(st);

            for(int i = 0; i < nomeThread.size(); i++){
                System.out.println(nomeThread.get(i));
            }
            
            do{
                 st = inDalClient.readLine();
                System.out.println(st);
                
                outVersoClient.writeBytes(st);
                if (st.equals("EXIT")){
                    condizioneUscita = true;
                }
            } while(!condizioneUscita);
        }

        catch ( Exception e ){
            System.out.println( e.getMessage() );
            System.out.println( "Errore durante l'istanza del server !" );
            System.exit( 1 );
        }
    }
}