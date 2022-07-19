package Passenger;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

public class PassengerInstanceCreator implements InstanceCreator<Passenger> {
    @Override
    public Passenger createInstance(Type type) {
        return new Passenger();
    }
}
