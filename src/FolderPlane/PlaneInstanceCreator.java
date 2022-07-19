package FolderPlane;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

public class PlaneInstanceCreator implements InstanceCreator<Plane> {


    @Override
    public Plane createInstance(Type type) {
        return new Gold();
    }

}
