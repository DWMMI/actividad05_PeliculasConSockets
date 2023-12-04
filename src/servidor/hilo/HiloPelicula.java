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
        numCliente++;
        hilo = new Thread(this, "Cliente_"+numCliente);
        this.socketAlCliente = socketAlCliente;
        hilo.start();
    }

    @Override
    public void run() {
        System.out.println("Estableciendo comunicacion con " + hilo.getName());
        PrintStream salida = null;
        InputStreamReader entrada = null;
        BufferedReader entradaBuffer = null;
        Pelicula pelicula = new Pelicula();
        pelicula.añadirPeliculasPD();
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
                    //Hay que tener en cuenta que toda comunicacion entre cliente y servidor
                    //esta en formato de cadena de texto
                    System.out.println("SERVIDOR: Me ha llegado del cliente: " + opcionCliente);
                    //Como sabemos que el cliente nos envia (opcion-, hacemos un split por "-"
                    //para obtener la información.
                    String[] opciones = opcionCliente.split("-");
                    int numero = Integer.parseInt(opciones[0]);

                    switch (numero){
                        case 1:
                            System.out.println("SERVIDOR: el cliente ha elegido 1. cosultar peliculas por ID");
                            salida.println(pelicula.consultarPeliculaPorId(Integer.parseInt(opciones[1])));
                            System.out.println("SERVIDOR: " + pelicula.consultarPeliculaPorId(Integer.parseInt(opciones[1])));
                            break;
                        case 2:
                            System.out.println("SERVIDOR: el cliente ha elegido 2. cosultar peliculas por título");
                            salida.println(pelicula.consultarPeliculaPorTitulo(opciones[1].toString()));
                            System.out.println("SERVIDOR: " + pelicula.consultarPeliculaPorTitulo(opciones[1].toString()));
                            break;
                        case 3:
                            System.out.println("SERVIDOR: el cliente ha elegido 3. cosultar peliculas por director");
                            salida.println(pelicula.consultarPeliculaPorDirector(opciones[1].toString()));
                            break;
                        case 4:
                            System.out.println("SERVIDOR: el cliente ha elegido 4. agregar pelicula");
                            salida.println("Por faver introduzca la pelicula en el siguiente formato: id-titulo-director-precio");
                            pelicula.agregarPelicula(opciones[1].toString());
                            salida.println("Pelicula agregada correctamente");
                            break;
                    }
                    pelicula.getPeliculas().forEach(System.out::println);
                }
            }
            //Cerramos el socket
            socketAlCliente.close();
            //Notese que si no cerramos el socket ni en el servidor ni en el cliente, mantendremos
            //la comunicacion abierta
        } catch (IOException e) {
            System.err.println("HiloPelicula: Error de entrada/salida");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("HiloPelicula: Error");
            e.printStackTrace();
        }
    }
}
