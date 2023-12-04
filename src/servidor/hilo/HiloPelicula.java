package servidor.hilo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HiloPelicula implements Runnable {
    private Thread hilo;
    private static int numCliente = 0;
    private Socket socketAlCliente;
    public HiloPelicula(Socket socketAlCliente) {
    }

    @Override
    public void run() {
        System.out.println("Estableciendo comunicacion con " + hilo.getName());
        PrintStream salida = null;
        InputStreamReader entrada = null;
        BufferedReader entradaBuffer = null;

        try {
            //Salida del servidor al cliente
            salida = new PrintStream(socketAlCliente.getOutputStream());
            //Entrada del servidor al cliente
            entrada = new InputStreamReader(socketAlCliente.getInputStream());
            entradaBuffer = new BufferedReader(entrada);

            String opcionCliente = "";
            boolean continuar = true;

            //Procesaremos entradas hasta que el texto del cliente sea FIN
            while (continuar) {
                opcionCliente = entradaBuffer.readLine();
                //Peticion del cliente 5: salir
                if (opcionCliente.equalsIgnoreCase("FIN")) {
                    salida.println("OK");
                    System.out.println(hilo.getName() + " ha cerrado la comunicacion");
                    continuar = false;
                } else {
                    // La opcion del cliente de 1-4
                   
                    
                    
                    //Le mandamos la respuesta al cliente
                    salida.println(); // Todo
                }
            }
            //Cerramos el socket
            socketAlCliente.close();
            //Notese que si no cerramos el socket ni en el servidor ni en el cliente, mantendremos
            //la comunicacion abierta
        } catch (IOException e) {
            System.err.println("HiloContadorLetras: Error de entrada/salida");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("HiloContadorLetras: Error");
            e.printStackTrace();
        }
    }
}
