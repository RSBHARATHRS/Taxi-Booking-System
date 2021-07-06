import java.util.ArrayList;
import java.util.List;

public class Taxi {
    static int taxiCount = 0;
    int taxiId;
    boolean isBooked;
    char currentLocation;
    char pickUpPoint;
    char dropPoint;
    int freeTime;
    int totalEarning;
    List<String> tripDetailsList = new ArrayList<String>();
    /*
     * __________________________________Taxi_Constructor____________________________________
     */

    public Taxi() {
        taxiCount = taxiCount + 1;
        taxiId = taxiCount;
        isBooked = false;
        currentLocation = 'A';
        pickUpPoint = '_';
        dropPoint = '_';
        freeTime = 6;
        totalEarning = 0;
    }

    /*
     * _______________________________________Update_____________________________________
     */
    public void update(boolean bookingStatus, char p_point, char d_point, int pickUpTime) {
        this.isBooked = true;
        this.pickUpPoint = p_point;
        this.dropPoint = d_point;
        int travelTime = Math.abs(d_point - p_point);
        int totalDistance = Math.abs(d_point - p_point) * 15;
        int time_to_come_pick_point = Math.abs(this.currentLocation - p_point);
        this.totalEarning = totalEarning + (totalDistance - 5) * 10 + 100;
        this.currentLocation = d_point;
        this.freeTime = freeTime + time_to_come_pick_point + travelTime;
        tripDetailsList.add(this.isBooked + "\t\t" + this.currentLocation + " \t\t\t" + this.pickUpPoint + "\t\t"
                + this.dropPoint + "\t\t" + this.freeTime + "\t\t" + this.totalEarning);
    }

    /*
     * _______________________________Print_Taxis_Full_Trips_Details__________________________________
     */
    public void printTaxiFullDetails(List<String> lStr) {
        System.out.println("Booked status\tcurrent location\tpick up point\tdrop point\tfree time\ttotal Earning");
        System.out.println(
                "____________________________________________________________________________________________________________");
        for (String str : lStr) {
            System.out.println(str);
        }

    }
}// END of Taxi Class
