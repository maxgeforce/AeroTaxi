package Files;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import FolderPlane.*;
import Passenger.*;
import Ticket.Ticket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.management.ObjectName;

public class FileManagement {

    public FileManagement() {
    }

    Gson gson = new Gson();

   /* public void escribiendoArchivo(String string) throws IOException {

        try {
            BufferedWriter buffer = new BufferedWriter(new FileWriter(new File("prueba.txt")));
            buffer.write(string);
            buffer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
*/
    public void arrayListToJson (ArrayList array, String nombreArchivo) throws IOException {
        try{
            String jSon=gson.toJson(array);
            BufferedWriter buffer = new BufferedWriter(new FileWriter(new File(nombreArchivo)));
            buffer.write(jSon);
            buffer.close();
           } catch (IOException e){
                    System.out.println(e.getMessage());
                }
        }

        public ArrayList jSonToArrayList (String archivoJson){

                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                BufferedReader reader = null;

                ArrayList<Passenger> passengers= new ArrayList<>();

                try {
                    reader = new BufferedReader(new FileReader(new File(archivoJson)));
                    passengers = gson.fromJson(reader,
                            (new TypeToken<ArrayList<Passenger>>() {}.getType())
                    );

                } catch (IOException e) {
                    e.printStackTrace();

                } finally {
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

         return passengers;
        }

        public  <T> void arrayToJsonFormat(ArrayList<T> t, String nombreArchivo) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            BufferedWriter whrite = null;

            try {
                whrite = new BufferedWriter(new FileWriter(new File(nombreArchivo)));
                gson.toJson(t, t.getClass(), whrite);
            } catch (
                    IOException e) {
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
        public void leer(String archivoJson) throws IOException, ClassNotFoundException {
            File file = new File(archivoJson);
            ObjectInputStream objInputStream = new ObjectInputStream(new FileInputStream(file));

            Object aux = objInputStream.readObject();
            while (aux!=null) {
                if (aux instanceof Ticket)
                    System.out.println(aux);
                aux = objInputStream.readObject();
            }

            objInputStream.close();
        }

        public ArrayList jSonToArrayListTicket (String archivoJson){

            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(Plane.class, new PlaneInstanceCreator()).create();
           // gson.registerTypeAdapter(Passenger.class, new PassengerInstanceCreator()).create();
           //(( gson.registerTypeAdapter(Plane.class, new Bronze());
            //gson.registerTypeAdapter(Plane.class, new Silver());))

            Gson gson1 = gson.create();

            BufferedReader reader = null;
            ArrayList<Ticket> rescatado = new ArrayList();
            Type colecctionType = TypeToken.getParameterized(List.class, Ticket.class).getType();

            BufferedReader entrada = null;

            try {
                entrada = new BufferedReader(new FileReader(archivoJson));

                rescatado = gson1.fromJson(entrada, colecctionType);

             } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return rescatado;
        }

    public  <Ticket> void arrayToJsonFormatTicket(ArrayList<Ticket> t, String nombreArchivo) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedWriter whrite = null;

        try {
            whrite = new BufferedWriter(new FileWriter(new File(nombreArchivo)));
            gson.toJson(t, t.getClass(), whrite);
        } catch (
                IOException e) {
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

    public boolean isFileEmpty(File file){
        return file.length() == 0;
    }

}




