//Socket cliente Java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args){
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Buscando servidor...");
            String hostname = "localhost";
            int port = 5020;
            clientSocket = new Socket(hostname,port);
            System.out.println("(Conectado al servidor con exito)");
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            String nombre= "Cliente: ";

            Thread sender = new Thread(new Runnable() {
            String msg;
            @Override
            public void run() {
                while(true){
                    msg = sc.nextLine();
                    out.println(nombre+msg + "");
                    out.flush();
                    }}
            });
            sender.start();
            Thread receiver = new Thread(new Runnable() {
            String msg;
            @Override
            public void run() {
                try {
                    msg = in.readLine();
                    while(msg!=null){
                        System.out.println(msg);
                        msg = in.readLine();
                        }
                        System.out.println("Servidor fuera de servicio");
                        out.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiver .start();
    }catch (IOException e){
        e.printStackTrace();
        }
    }
}