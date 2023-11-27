package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class App extends Thread
{
    public static void main( String[] args ) throws Exception
    {
        //Connessione di un Thread al Server
        ServerSocket ss = new ServerSocket(6789);
        while(true)
        {
            Socket s = ss.accept();
            Server thread = new Server(s);
            thread.start();
        }
        
    }



    //Comunicazione tra i Client
    /***************************/
    Socket client;

    BufferedReader input;

    private static List<Socket> socketList = new ArrayList<>();
    String st = "";


    public App (Socket client){
        this.client = client;
        socketList.add(client);
    }

    public void run(){
        
        try{
            
            //Inizializzazione delle variabili
            input = new BufferedReader( new InputStreamReader( client.getInputStream() ) );

            while( !st.equals( "EXIT" ) ) {
                //Lettura del messaggio arrivato
                st = input.readLine();
                System.out.println( st );

                //Invio broadcast del messaggio
                for ( Socket socket : socketList ) {
                if (socket == client) {
                    continue;//ignora chi invia il messaggio
                }
                DataOutputStream output = new DataOutputStream( socket.getOutputStream() );
                output.writeUTF(st);
            }
                
                //Se viene ricevuta la Stringa 'EXIT' il programma viene chiuso
                if ( st.equals( "EXIT" ) ){ 
                    System.out.println();
                    client.close();
                }
            }
        }

        catch ( Exception e ){
            System.out.println( e.getMessage() );
            System.out.println( "Errore durante l'istanza del server !" );
            System.exit( 1 );
        }
    }
}

