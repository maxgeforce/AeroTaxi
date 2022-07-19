package FolderPlane;

import java.io.Serializable;
import java.util.Calendar;

public class Gold extends Plane implements Serializable {


    public Gold(String name, int coste, double fuel, int maxPassenger,
                double kmXhs, double kmTraveled, engineType engineType) {
        super(name, coste, maxPassenger, kmXhs, kmTraveled, engineType,6000);
    }
    public Gold(String name) {
        super(name, 300,8,746,3700, engineType.MOTOR_A_REACCION, 6000);

    }

    public Gold() {

    }

    @Override
    public int catering(int coste) {
        System.out.println("Precio $500");
        return coste=coste+500;
    }

    @Override
    public int wifi(int coste) {
        return coste=coste+100;
    }

    @Override
    public int carry_on_bag(int coste) {
        return coste=coste+300;
    }

    @Override
    public int confort(int coste) {
        return coste=coste+400;
    }
}
