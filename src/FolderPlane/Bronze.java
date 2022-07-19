package FolderPlane;

import java.io.Serializable;
import java.util.Calendar;

public class Bronze extends Plane implements Serializable {

    private Calendar diaVuelo;

    public Bronze(String name, int coste, double fuel, int maxPassenger,
                  double kmXhs, double kmTraveled, engineType engineType) {
        super( name, coste, maxPassenger, kmXhs, kmTraveled, engineType,3000);
    }



    public Bronze(String name) {
        super(name ,150, 60, 876, 3440, engineType.MOTOR_DE_PISTONES,3000);

    }

    public Bronze() {

    }


    @Override
    public int catering(int coste) {
        System.out.println("service not available");
        return coste;
    }

    @Override
    public int wifi(int coste) {
        System.out.println("service not available");
        return coste;
    }

    @Override
    public int carry_on_bag(int coste) {
        System.out.println("Carry_on_bag: Coste $300");
        return coste=coste+300;
    }

    @Override
    public int confort(int coste) {
        System.out.println("Confort: Precio $200");
        return coste=coste+200;
    }

}
