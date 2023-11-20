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
            //Inizializzazione delle variabili
            inDalClient = new BufferedReader( new InputStreamReader( client.getInputStream() ) );
            outVersoClient  = new DataOutputStream( client.getOutputStream() );
            boolean condizioneUscita = false;


            //Salvataggio degli username dei Client
            st = inDalClient.readLine();
            System.out.println(st);
            for(int i = 0; i < nomeThread.size(); i++){
                System.out.println(nomeThread.get(i));
            }

            do{
                //Lettura del messaggio ricevuto dal Client
                st = inDalClient.readLine();
                System.out.println(st);
                
                //Se viene ricevuta la Stringa 'EXIT' il programma viene chiuso
                outVersoClient.writeBytes(st);
                
            } while(!condizioneUscita);
        }

        catch ( Exception e ){
            System.out.println( e.getMessage() );
            System.out.println( "Errore durante l'istanza del server !" );
            System.exit( 1 );
        }
    }
}