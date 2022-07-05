//Socket server Java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    public static void main(String[] args){
        final ServerSocket serverSocket;
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc= new Scanner(System.in);

        try {
            System.out.println("Corriendo servidor Java...");
            int port = 5020;
            serverSocket = new ServerSocket(port);
            System.out.println("Buscando cliente...");
            clientSocket = serverSocket.accept();
            System.out.println("(Cliente conectado con exito)");
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));

            String nombre= "Server: ";
            Thread sender= new Thread(new Runnable() {
            String msj;
            @Override
            public void run() {
                while(true){
                    msj = sc.nextLine();
                    out.println(nombre+msj);
                    out.flush();
                    }
                }
            });
            sender.start();

            Thread receive= new Thread(new Runnable() {
                String msj ;
                @Override
                public void run() {
                    try {
                        msj = in.readLine();
                        
                        while(msj!=null){
                            System.out.println(msj);
                            msj = in.readLine();
                        }

                        System.out.println("Cliente desconectado");

                        out.close();
                        clientSocket.close();
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receive.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}