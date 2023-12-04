import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//En esta clase vamos a crear un cliente que se conecte a un servidor y le mande peticiones al servidor
public class Cliente {
    //Puerto
    public static final int PUERTO = 2023;
    //IP del servidor
    public static final String IP_SERVER = "localhost";
    private static int numero;


    public static void main(String[] args) {
        System.out.println("        APLICACIÓN CLIENTE         ");
        System.out.println("-----------------------------------");

        //En este objeto vamos a encapsular la IP y el puerto al que nos vamos a conectar
        InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);

        //Usaremos la declaracion try-with-resources, esta declaraci�n nos asegura que
        //todos los objetos que declaremos e instanciemos dentro del bloque try, ser�n
        //cerrados cuando salgan del bloque try-catch. Es muy util parar ahorranos codigo
        //ya que sino deber�amos cerrarlos (ejecutar su metodo close()), dentro del bloque
        //finally, creando muchas m�s lineas para ello.
        try (Scanner sc = new Scanner(System.in);
             Socket socketAlServidor = new Socket()) {
            //Mostrar el menu
            System.out.println("MENU:");
            System.out.println("1. Consultar peliculas por ID");
            System.out.println("2. Consultar peliculas por titulo");
            System.out.println("3. Consultar peliculas por director");
            System.out.println("4. Agregar pelicula");
            System.out.println("5. Salir de la aplicacion");
            //Pedimos al usuario que elija una opcion
            do {
                System.out.println("CLIENTE: elige una opcion introduciendo un número de 1 - 5: ");
                numero = Integer.parseInt(sc.nextLine());
            } while (numero < 1 || numero > 5);
            switch (numero) {
                case 1:
                    System.out.println("CLIENTE: Introduzca el ID de la pelicula");
                    String id = sc.nextLine();
                    String opcion = "1-" + id;
                    mandarPeticion(opcion, direccionServidor, socketAlServidor);
                    break;
                case 2:
                    System.out.println("CLIENTE: Introduzca el titulo de la pelicula");
                    String titulo = sc.nextLine();
                    String opcion2 = "2-" + titulo;
                    mandarPeticion(opcion2, direccionServidor, socketAlServidor);
                    break;
                case 3:
                    System.out.println("CLIENTE: Introduzca el director de la pelicula");
                    String director = sc.nextLine();
                    String opcion3 = "3-" + director;
                    mandarPeticion(opcion3, direccionServidor, socketAlServidor);
                    break;
                case 4:
                    System.out.println("CLIENTE: Introduzca el titulo de la pelicula");
                    String titulo2 = sc.nextLine();
                    System.out.println("CLIENTE: Introduzca el director de la pelicula");
                    String director2 = sc.nextLine();
                    System.out.println("CLIENTE: Introduzca el año de la pelicula");
                    String anio = sc.nextLine();
                    String opcion4 = "4-" + titulo2 + "-" + director2 + "-" + anio;
                    mandarPeticion(opcion4, direccionServidor, socketAlServidor);
                    break;
                case 5:
                    System.out.println("CLIENTE: Saliendo de la aplicacion");
                    break;
            }


            //Observese como no conviertos los datos a enteros, ya que cuando
            //se mandan a traves de un socket se mandan SIEMPRE en formato cadena

            //Establecemos la conexión
            System.out.println("CLIENTE: Esperando a que el servidor acepte la conexión");
            socketAlServidor.connect(direccionServidor);
            System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER
                    + " por el puerto " + PUERTO);

            //Creamos el objeto que nos permite mandar informacion al servidor
            PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());


        }catch (UnknownHostException e) {
            System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("CLIENTE: Error de entrada/salida");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("CLIENTE: Error -> " + e);
            e.printStackTrace();
        }
    }

    public static void mandarPeticion(String opcion, InetSocketAddress direccionServidor, Socket socketAlServidor) throws IOException {

    }
}