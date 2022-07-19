package Ticket;

import Crud.Crud;
import Files.FileManagement;
import FolderPlane.Gestion;
import FolderPlane.Plane;
import Menu.Menu;
import Passenger.Passenger;
import Travel.Distances;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Ticket implements Serializable {
    private UUID ticket;
    private int price;
    private Distances destination;
    private int total_passengers;
    private Plane plane;
    private Passenger passager;
    private String Seat;
    private Calendar fechaDeViaje;




    public Ticket() {
        this.ticket = UUID.randomUUID();
    }

    public Ticket(Distances destination, String seat, Plane plane) {
        this.ticket = UUID.randomUUID();
        this.destination = destination;
        Seat = seat;
        this.plane = plane;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal_passengers() {
        return total_passengers;
    }

    public void setTotal_passengers(int total_passengers) {
        this.total_passengers = total_passengers;
    }

    public Distances getDestination() {
        return destination;
    }

    public void setDestination(Distances destination) {
        this.destination = destination;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Passenger getPassager() {
        return passager;
    }

    public void setPassager(Passenger passager) {
        this.passager = passager;
    }

    public String getSeat() {
        return Seat;
    }

    public void setSeat(String seat) {
        Seat = seat;
    }

    public int travelCost(Plane plane) {
        int coste = 0;
        coste = plane.getCoste();
        //(canKm * cosKm)+(canPas * 3500)+(tarifaTipoAvion)
        return coste;
    }
    public void setFechaDeViaje(Calendar fechaDeViaje) {
        this.fechaDeViaje = fechaDeViaje;
    }

    public Calendar getFechaDeViaje() {
        return fechaDeViaje;
    }

    /* public Ticket costoTicket() {
            Ticket ticket = new Ticket();
            ticket.price = plane.carry_on_bag(plane.getCoste());
            return ticket;
        }*/
    public void costoTicket() {
        int costo = 0;
        System.out.println("      Costo total del ticket  ");
        if (this.getTotal_passengers() > 1){
            System.out.println("Pasajeros agregados, total de boletos: " + this.getTotal_passengers());
        }
            costo = this.plane.carry_on_bag(costo) + this.plane.confort(costo)
                + this.plane.catering(costo) + this.plane.wifi(costo) + this.plane.getPlaneType() + 3500;
            costo  = (getTotal_passengers() * costo) + (this.plane.getCoste() * this.destination.retornarNumero());

        System.out.println("     Costo del viaje en avion " + "<" + plane.getNombre() +">" + " es " + plane.getPlaneType()  );
        System.out.println("     por pasajero es " + 3500 + "  total: " + costo  + "\n" );
        this.setPrice(costo) ;
    }

    @Override
    public String toString() {
        return  "ID ticket:      " + ticket.toString().substring(0,10).toUpperCase(Locale.ROOT) +  "\n" +
                "Name passenger: " + passager.getName() + " " + passager.getLastName() + "\n" +
                "destination:    " + destination + "\n" +
                "Seats:          " + this.getTotal_passengers() + "\n" +
                "Plane:          " + plane.getNombre() + "\n" +
                "Price:         $" + price + "\n" ;
    }

    public static void ticket_registration(String nameFileTicket, String nameFilePax) throws IOException {
        List<Plane> flota = Gestion.add_a_Flota(new ArrayList<>());
        FileManagement filePas = new FileManagement();
        FileManagement fileTi = new FileManagement();
        ArrayList <Passenger> pax = filePas.jSonToArrayList(nameFilePax);
        ArrayList <Ticket> tickets = fileTi.jSonToArrayListTicket(nameFileTicket);

        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();
        Crud crud = new Crud();
        Ticket ticket = new Ticket();
        Fechas fechas = new Fechas();

        int option = 0;
        if (fileTi.isFileEmpty(new File(nameFileTicket))) {

            while (option != 2) {
                System.out.println(             "NUEVO ARCHIVO DE TICKET EN PROCESO" + "\n");
                eligeDestino(ticket);

                Calendar calendar= fechas.elegir();
                ticket.setFechaDeViaje(calendar);

                int companions= menu.addCompa();
                ticket.setTotal_passengers(companions);

                int num = eligeAvion(ticket);
                ticket.setPlane(flota.get(num));
                ticket.costoTicket();

                System.out.println("ENTER DNI");
                String dni = scan.nextLine();
                newPass(nameFilePax, dni);
                pax = filePas.jSonToArrayList(nameFilePax);

                ticket.setPassager(pax.get(crud.buscaPorDni(nameFilePax,dni)));

                System.out.println(           "Datos del ticket" + "\n"  + ticket.toString());
                System.out.println(       "Desea Confirmar el Ticket ?  --- S   /   N");
                String conf= scan.nextLine().toUpperCase();
                if (conf.contains("S")){
                    tickets.add(ticket);
                }
                System.out.println(             "1 para modificar ticket o 2 para continuar");
                option = scan.nextInt();
                scan.nextLine();
                fileTi.arrayToJsonFormat(tickets, nameFileTicket);
            }

        } else {
            ArrayList<Ticket> aux = new ArrayList<>();
            aux = fileTi.jSonToArrayListTicket(nameFileTicket);
            while (option != 2) {
                eligeDestino(ticket);

                Calendar calendar= fechas.elegir();
                ticket.setFechaDeViaje(calendar);


                int companions= menu.addCompa();
                ticket.setTotal_passengers(companions);

                int num = eligeAvion(ticket);
                ticket.setPlane(flota.get(num));
                ticket.costoTicket();

                System.out.println("ENTER DNI");
                String dni = scan.nextLine();
                newPass(nameFilePax, dni);       // TODO: 6/17/2022 busca el dni en el archivo, sino existe lo agrega
                pax = filePas.jSonToArrayList(nameFilePax);  // TODO: 6/18/2022 Fue necesario traer archivo para setear pasajero

                ticket.setPassager(pax.get(crud.buscaPorDni(nameFilePax,dni)));

                System.out.println(            "Datos de nuevo ticket" + "\n" + ticket.toString());
                System.out.println(            "Desea Confirmar El Ticket ?  --- S   /   N");
                String conf= scan.nextLine().toUpperCase();
                if (conf.contains("S")){
                    aux.add(ticket);
                }
                System.out.println(           " 1 para modificar ticket o 2 para confirmar");
                option = scan.nextInt();
                scan.nextLine();
                fileTi.arrayToJsonFormatTicket(aux, nameFileTicket);
            }

        }
    }

    public boolean equalsTicket(Calendar calendar) {
        if (fechaDeViaje.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) && fechaDeViaje.get(Calendar.DATE) == calendar.get(Calendar.DATE)) {
            return true;
        }else {
            return false;
        }
    }
    public static boolean ticke_dates(Plane plane, Ticket ticket){
        boolean flame = false;
        for (Calendar calendar: plane.listPlane ){
            flame = ticket.equalsTicket(calendar);
            if (flame==true){
                return true;
            }
        }
        return false;
    }
    public static void plane_ticket_dates(Ticket ticket){
        List<Plane> misAviones = Gestion.add_a_Flota(new ArrayList<>());
        boolean flame = false;
        int i=0;
        System.out.println("<<< Elija opcion >>>");
        System.out.println("Aviones disponibles para la fecha: "+ ticket.fechaDeViaje.get(Calendar.DATE)+"/"+ticket.fechaDeViaje.get(Calendar.MONTH));
        for (Plane plane: misAviones){
            flame=ticke_dates(plane, ticket);
            if (flame == false){
                System.out.println(i +"-Avion: "+ plane.getNombre());
            }
            i++;
        }
    }

    public static int eligeAvion(Ticket ticket) throws IOException {
        Scanner scan = new Scanner(System.in);
        int respuesta;
        int pos = -1;
        do {
            plane_ticket_dates(ticket);
            respuesta = scan.nextInt();

        }while(respuesta==0);

        return pos=respuesta;
    }



    /*public static int eligeAvion() throws IOException {
        Scanner scan = new Scanner(System.in);
        int respuesta;
        int pos = -1;
        do {
            tipo_de_avion();
            respuesta = scan.nextInt();
            switch (respuesta) {
                case 1:
                   pos = 0;
                    break;
                case 2:
                    pos = 1;
                    break;
                case 3:
                    pos = 2;
                    break;
                case 4:
                    pos = 3;
                    break;
                case 5:
                    pos = 4;
                    break;
                case 6:
                    pos = 5;
                    break;
                case 0:
                    System.out.println("ESC");
                    break;
                default:
                    respuesta = 0;
                    System.out.println("Solo puede elegir las opciones 1, 2, 3, 4, 5, 6 o 0...");
                    break;

            }
            return pos;

        } while (respuesta != 0);

    }*/

    private static void tipo_de_avion() {
        System.out.println("<<< Elija opcion >>>");
        System.out.println("1- Bronze ");
        System.out.println("2- Bronze ");
        System.out.println("3- Silver ");
        System.out.println("4- Bronze ");
        System.out.println("5- Gold ");
        System.out.println("6- Gold ");
        System.out.println("0- ESC");
    }

    public static void eligeDestino(Ticket tick)  {
        Scanner scan = new Scanner(System.in);
        int respuesta;

        do {
            destinos();
            respuesta = scan.nextInt();
            switch (respuesta) {
                case 1:
                    tick.setDestination(Distances.BsAs_Cor);
                    break;
                case 2:
                    tick.setDestination(Distances.Cor_San);
                    break;
                case 3:
                    tick.setDestination(Distances.Cor_Mon);
                    break;
                case 4:
                    tick.setDestination(Distances.BsAs_San);
                    break;
                case 5:
                    tick.setDestination(Distances.Mon_San);
                    break;
                case 6:
                    tick.setDestination(Distances.BsAs_Mon);
                    break;
                default:
                    respuesta = 0;
                    System.out.println("Solo puede elegir las opciones 1, 2, 3, 4, 5, 6 o 0...");
                    break;
            }

        } while (respuesta == 0);
        System.out.println("Destino seleccionado !!");
    }

    private static void destinos() {
        System.out.println("<<< Elija opcion >>>");
        System.out.println("1- Bs.As_Cordoba ");
        System.out.println("2- Cordoba_Santiago ");
        System.out.println("3- Corboda_Montevideo ");
        System.out.println("4- Bs.As_Santiago ");
        System.out.println("5- Montevideo_Santiago ");
        System.out.println("6- Bs.As_Montevideo ");
        System.out.println("0- ESC");
    }

    public static void newPass (String nameFile, String dni) throws RuntimeException, IOException {
        Crud crud = new Crud();
        try {
           if (crud.buscaPorDni(nameFile,dni)>=0){
            FileManagement file = new FileManagement();
            ArrayList<Object> aux = new ArrayList<>();
            aux = file.jSonToArrayList(nameFile);
            System.out.println("             Cliente encontrado " +  "\n"  + aux.get(crud.buscaPorDni(nameFile, dni)));
        }else {
                System.out.println("            Ingrese datos para nuevo pasajero");
                crud.altaPassenger(nameFile);

        }
        }catch (RuntimeException e){
            System.out.println("               Archivo no encontrado" );
        }
    }

}