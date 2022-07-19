package FolderPlane;

import Passenger.Passenger;
import Ticket.*;
import Travel.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

public abstract class Gestion implements Serializable {

    private static List <Plane> planes;
    private static List <Passenger> passenger;
    private static List <Ticket> tickets;
    private static List <Travel> travels;

    public Gestion (){}



    public static  ArrayList<Plane> add_a_Flota(ArrayList planes){

        Bronze boeing123 = new Bronze("boeing 123-Bronce");
        Bronze boeing124 = new Bronze("boeing 124-Bronce");
        Silver eclipse550 = new Silver("eclipse 550-Silver");
        Silver eclipse555 = new Silver("eclipse 555-Silver");
        Gold cessna550 = new Gold("cessna 550-Gold");
        Gold cessna650 = new Gold("cessna 650-Gold");

        Calendar calendar7 = Calendar.getInstance();
        calendar7.set(2022,7,7);
        Calendar calendar8 = Calendar.getInstance();
        calendar8.set(2022,9,9);
        Calendar calendar9 = Calendar.getInstance();
        calendar9.set(2022,7,7);

        cessna550.setList(calendar7);
        cessna650.setList(calendar8);
        boeing123.setList(calendar9);

        planes.add(boeing123);
        planes.add(boeing124);
        planes.add(eclipse555);
        planes.add(cessna650);
        planes.add(eclipse550);
        planes.add(cessna550);


        return planes;
    }

    public static List<Plane> getPlanes() {
        return planes;
    }

    ///VER DEL ARCHIVO
    public static  <T> void persistencia(List<T> t, String name) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedWriter whrite = null;

        try {
            whrite = new BufferedWriter(new FileWriter(new File(name)));
            gson.toJson(t, t.getClass(), whrite);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (whrite != null) {
                try {
                    whrite.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ArrayList<Ticket> add_a_Ticket(ArrayList tickets, Ticket ticket){

        tickets.add(ticket);

        return tickets;

    }

    public static ArrayList<Travel> add_a_Array (ArrayList travels, Travel travel){
        travels.add(travel);

        return travels;
    }

    public static void bestPlane (List<Plane> lista){
        Plane mayor = Collections.max(lista, Comparator.comparing(c -> c.getCoste()));
        System.out.println("  best category: " + "\n" + mayor.getNombre());
        System.out.println(mayor.toString());
        }
}
