package FolderPlane;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public abstract class Plane implements Service, Serializable {
    private Integer planeType;
    private String nombre;
    private int coste;
    private int maxPassenger;
    private double kmXhs;
    private double kmTraveled;
    private engineType engineType;
    public List<Calendar> listPlane ;



    Plane(){
    }

    public Plane(String nombre, int coste, int maxPassenger, double kmXhs, double kmTraveled, engineType engineType, Integer PlaneType) {
        this.nombre = nombre;
        this.coste = coste;
        this.maxPassenger = maxPassenger;
        this.kmXhs = kmXhs;
        this.kmTraveled = kmTraveled;
        this.engineType = engineType;
        this.planeType = PlaneType;
        this.listPlane = new ArrayList<>();
    }
    public List<Calendar> getListPlane() {
        return listPlane;
    }

    public void setList(Calendar calendar) {
        listPlane.add(calendar);
        this.listPlane = listPlane;
    }

    public int getCoste() {
        return coste;
    }

    public Integer getPlaneType() {
        return planeType;
    }


    public String getNombre() {
        return nombre;
    }


    public int getMaxPassenger() {
        return maxPassenger;
    }

    public void setMaxPassenger(int maxPassenger) {
        this.maxPassenger = maxPassenger;
    }

    public boolean vacantSeat (int seats_to_be_used){
        int vacanS = getMaxPassenger();
        if (vacanS > seats_to_be_used){
            vacanS -= seats_to_be_used;
            setMaxPassenger(vacanS);
            return true;
        }
        return false;
    }
    public enum engineType {
        MOTOR_A_REACCION,
        MOTOR_A_HELICE,
        MOTOR_DE_PISTONES,
    }

    @Override
    public String toString() {      // TODO: 6/17/2022 correccion salto de linea
        return             "           <Plane> " + "\n" +
                " nombre:            " + nombre + "\n" +
                " coste fuel:        " + coste +  "\n" +
                " maxPassenger:      " + maxPassenger +  "\n" +
                " kmXhs:             " + kmXhs +  "\n" +
                " kmTraveled:        " + kmTraveled +  "\n" +
                " engineType:        " + engineType +  "\n" +
                " PlaneType:         " + planeType +  "\n" + "\n";
    }

}
