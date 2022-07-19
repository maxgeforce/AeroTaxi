package Menu;

import Files.FileManagement;
import FolderPlane.Gestion;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import Crud.*;
import FolderPlane.Plane;

import Passenger.Passenger;
import Ticket.Ticket;

import java.util.function.ToDoubleBiFunction;

public class Menu {
    // comente clase Gestión
    static Crud crud = new Crud();
    static FileManagement file = new FileManagement();

    public static void primerMenu () throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        int respuesta;
        do {
            cuestionarioInicial();
            respuesta = scan.nextInt();
            switch (respuesta){
                case 1:
                    Ticket.ticket_registration("ARCHIVO_TICKET.json","ARCHIVO_PASAJEROS.json");
                    break;
                case 2:

                     gestionPasajeros("ARCHIVO_PASAJEROS.json");

                    break;
                case 3:
                    gestionTicket("ARCHIVO_TICKET.json");
                    break;
                case 0:
                    opcion3();
                    break;
                default:
                    respuesta = 0;
                    System.out.println("Solo puede elegir las opciones 1, 2, 3, 4 o 0...");
                    break;
            }
        }while(respuesta != 0);
        System.out.println("despegue !!");
    }
    public static void gestionTicket(String nombreArchivo) throws IOException{
        ArrayList<Ticket> aux = new ArrayList<>();
        aux = file.jSonToArrayListTicket(nombreArchivo);
        Scanner scan = new Scanner(System.in);
        int respuesta;
        do {
            opcion0();
            respuesta = scan.nextInt();
            switch (respuesta){
                case 1:
                    aux=CancelarVuelo(aux);
                    file.arrayToJsonFormatTicket(aux, nombreArchivo);
                    break;
                case 2:
                    MostrarTickets(aux);
                    break;
                case 0:
                    opcion3();
                    break;
                default:
                    opcionDefault(); // solo puede elegir opción 1 o 2
                    break;
            }
        }while (respuesta != 0);
    }

    private static void opcion0() {
        System.out.println("1- CANCELAR VUELO");
        System.out.println("2- MOSTRAR TICKETS");
        System.out.println("0- ESC");
    }
    private static ArrayList<Ticket> CancelarVuelo( ArrayList<Ticket> tickets) {
        Scanner scan = new Scanner(System.in);
        Calendar dayToday = Calendar.getInstance();

        int i=0;
        int respuesta=0;
        for(var ticket : tickets){
            System.out.println("    Ticket numero: "+i);
            System.out.println(ticket.toString());
            i++;
        }
        System.out.println("Elija ticket a Borrar");
        respuesta = scan.nextInt();
        if(tickets.get(respuesta).getFechaDeViaje().get(Calendar.MONTH)== dayToday.get(Calendar.MONTH) && tickets.get(respuesta).getFechaDeViaje().get(Calendar.DATE)== dayToday.get(Calendar.DATE)){
            System.out.println("No se puede Borrar una Fecha en menos de 24hs");
        }else {
            tickets.remove(respuesta);
            System.out.println("Vuelo Cancelado....");
        }

        return tickets;
    }
    private static void MostrarTickets(ArrayList<Ticket> tickets) {
        int i=0;
        for(var ticket : tickets){
            System.out.println("    Ticket numero: "+i);
            System.out.println(ticket.toString());
            i++;
        }
    }


    private static void cuestionarioInicial() {
        System.out.println("<<< Bienvenidos a AeroTaxi >>>");
        System.out.println("1- VIAJE");
        System.out.println("2- GESTION DE PASAJEROS");
        System.out.println("3- GESTION DE TICKET");
        System.out.println("0- ESC");
    }

    private static void opcion3() {
        System.out.println("ESC");
    }
    private static void opcionDefault() {
        System.out.println("Solo puede elegir las opciones 1 o 2");
    }

    public int addCompa() {
        Scanner scanner = new Scanner(System.in);

        int cantAcompanantes = 0;
        System.out.println("Ingrese la cantidad de acompanantes: ");
        cantAcompanantes = scanner.nextInt();
        int suma = 1;
        if (cantAcompanantes > 0) {
            // yo
            suma=suma+cantAcompanantes;
            System.out.println("Usted reserva "+suma+" lugares ");
        }else {
            System.out.println("sin acompanantes..");
        }
        return suma;
    }
    private static void opcion15() {
        System.out.println("Imprimiendo pasaje...");// TODO: 10/06/2022 se puede cambiar el msj, de singular a plural, según la cantidad de pasajes que se compren
    }

    public static void gestionPasajeros (String ArchivoPasajero) throws IOException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Passenger> aux = new ArrayList<>();
         aux = file.jSonToArrayList(ArchivoPasajero);
        System.out.println("1 - AGREGAR PASAJERO" +
                           " 2 - MODIFICAR PASAJERO " +
                            " 3 - BUSCAR POR DNI"+
                            " 4 - ELIMINAR PASAJERO");
        String resp =scan.nextLine();
        switch (resp){
            case "1" : crud.altaPassenger(ArchivoPasajero);
            break;
            case "2": crud.modificarDatosPasajero(ArchivoPasajero);
            break;
            case "3":
                System.out.println("Ingrese el Dni");
                String dni= scan.nextLine();
                System.out.println(aux.get(crud.buscaPorDni(ArchivoPasajero, dni)));
                break;
            case "4":
                System.out.println("Ingrese el Dni");
                String search= scan.nextLine();
                crud.bajaPassenger(ArchivoPasajero, search);
                break;
        }
    }

}