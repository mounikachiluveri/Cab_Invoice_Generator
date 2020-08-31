package cabinvoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {
    static Map<String, ArrayList<Ride>> userRidesMap = new HashMap<>();

    public static void addRides(String userId, Ride[] rides){
        userRidesMap.put(userId, new ArrayList<>((Arrays.asList(rides))));
    }

    public static Ride[] getRides(String userId) {
        return userRidesMap.get(userId).toArray(new Ride[0]);
    }
}
