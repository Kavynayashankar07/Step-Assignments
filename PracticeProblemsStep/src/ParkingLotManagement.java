import java.util.*;

class ParkingLotManagement {

    static int SIZE = 10; // total parking spots
    static String[] spots = new String[SIZE]; // store license plates
    static HashMap<String, Long> entryTime = new HashMap<>();

    // simple hash function
    public static int hash(String plate) {
        return Math.abs(plate.hashCode()) % SIZE;
    }

    // park vehicle
    public static void parkCar(String plate) {

        int index = hash(plate);

        // linear probing
        while (spots[index] != null) {
            index = (index + 1) % SIZE;
        }

        spots[index] = plate;
        entryTime.put(plate, System.currentTimeMillis());

        System.out.println("Car " + plate + " parked at spot " + index);
    }

    // remove vehicle
    public static void exitCar(String plate) {

        for (int i = 0; i < SIZE; i++) {
            if (plate.equals(spots[i])) {

                long time = System.currentTimeMillis() - entryTime.get(plate);

                spots[i] = null;
                entryTime.remove(plate);

                System.out.println("Car " + plate + " exited from spot " + i);
                System.out.println("Parking time (seconds): " + time / 1000);
                return;
            }
        }

        System.out.println("Car not found");
    }

    // show parking status
    public static void showParking() {
        System.out.println("\nParking Lot Status:");

        for (int i = 0; i < SIZE; i++) {
            System.out.println("Spot " + i + " : " + spots[i]);
        }
    }

    public static void main(String[] args) {

        parkCar("TN10AB1234");
        parkCar("TN09XY5678");
        parkCar("TN22CD9999");

        showParking();

        exitCar("TN09XY5678");

        showParking();
    }
}
