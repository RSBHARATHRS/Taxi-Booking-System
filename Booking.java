import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking implements TaxiRules {

    static List<Taxi> taxiList;

    /*
     * _____________________________________Create_Taxi_____________________________________
     */
    static List<Taxi> createTaxi(int n) {
        taxiList = new ArrayList<Taxi>();
        for (int i = 0; i < n; i++) {
            Taxi t = new Taxi();
            taxiList.add(t);
        }
        return taxiList;
    }

    /*
     * _________________________________Print_Taxi_Details___________________________________
     */
    public void printTaxiDetails(Taxi t) {

        System.out.println(t.taxiId + "\t\t" + t.isBooked + "\t\t" + t.currentLocation + " \t\t\t" + t.pickUpPoint
                + "\t\t" + t.dropPoint + "\t\t" + t.freeTime + "\t\t" + t.totalEarning);
        System.out.println();
    }

    /*
     * ______________________________________Book_Taxi________________________________________
     */
    public void bookTaxi(char p_point, char d_point, int p_time) {
        // int min_dis = 999;
        // int min_earning = 9999;
        boolean bookingStatus = false;
        Taxi allocatedTaxi = new Taxi();

        List<Taxi> availableTaxis = getAvailableTaxi(taxiList, p_point, p_time);
        List<Taxi> taxisInSameLocation = getTaxisInSameLocation(availableTaxis, p_point);
        List<Taxi> allocatableTaxis = getTaxisInSameLocation(availableTaxis, p_point).isEmpty()
                ? getTaxisInNearestLocation(availableTaxis, p_point)
                : taxisInSameLocation;
        if (allocatableTaxis.isEmpty()) {
            System.out.println("All Taxis are busy at the moment, Please try again later :)");
            return;
            // return taxiList;
        } else if (allocatableTaxis.size() == 1) {
            allocatedTaxi = allocatableTaxis.get(0);
            bookingStatus = true;
        } else {
            for (int i = 0; i < allocatableTaxis.size() - 1; i++) {
                if (allocatableTaxis.get(i).totalEarning <= allocatableTaxis.get(i + 1).totalEarning) {
                    allocatedTaxi = allocatableTaxis.get(i);
                    bookingStatus = true;
                } else {
                    allocatedTaxi = allocatableTaxis.get(i + 1);
                    bookingStatus = true;
                }
            }
        }

        allocatedTaxi.update(bookingStatus, p_point, d_point, p_time);
        if (bookingStatus) {
            System.out.println("Successfully booked with taxi No : " + allocatedTaxi.taxiId);
        } else {
            System.out.println("Cant");
        }

    }

    /*
     * _________________________________Get_Available_Taxis___________________________________
     */
    public List<Taxi> getAvailableTaxi(List<Taxi> taxiList, char pickupPoint, int pickupTime) {
        List<Taxi> availableTaxiList = new ArrayList<Taxi>();
        for (Taxi t : taxiList) {
            int timeTackToReachCustomer = t.freeTime + Math.abs(pickupPoint - t.currentLocation);
            if (timeTackToReachCustomer <= pickupTime && pickupTime <= t.freeTime)
                availableTaxiList.add(t);

        }
        return availableTaxiList;
    }
    /*
     * ______________________________Get_Taxis_In_Same_Location________________________________
     */

    public List<Taxi> getTaxisInSameLocation(List<Taxi> availableTaxis, char pickUpPoint) {
        List<Taxi> taxisInSameLocation = new ArrayList<Taxi>();
        for (Taxi t : availableTaxis) {
            if (t.currentLocation == pickUpPoint) {
                taxisInSameLocation.add(t);
            }
        }
        return taxisInSameLocation;
    }
    /*
     * _____________________________Get_Taxis_In_Nearest_Locations____________________________
     */

    public List<Taxi> getTaxisInNearestLocation(List<Taxi> availableTaxis, char pickUpPoint) {
        List<Taxi> taxisInNearestLocation = new ArrayList<Taxi>();
        int min_dis = 999;
        for (Taxi t : availableTaxis) {
            int distanceBetweenPC = Math.abs(t.currentLocation - pickUpPoint);
            if (distanceBetweenPC == min_dis) {
                taxisInNearestLocation.add(t);
            } else if (distanceBetweenPC < min_dis) {
                taxisInNearestLocation.clear();
                taxisInNearestLocation.add(t);
                min_dis = distanceBetweenPC;
            }
        }
        return taxisInNearestLocation;
    }

    /*
     * _______________________________________Main_Method_____________________________________
     */
    public static void main(String args[]) {
        Booking booking = new Booking();
        Scanner sc = new Scanner(System.in);
        int total_no_of_taxis;
        int choice = 0;
        char pickup_point;
        char drop_point;
        System.out.println("___________Taxi Booking System___________");
        System.out.print("Enter total no of taxis : ");
        total_no_of_taxis = sc.nextInt();
        taxiList = createTaxi(total_no_of_taxis);
        boolean loop = true;
        do {
            System.out.println("0 -> Book Taxi");
            System.out.println("1 -> General Taxi Details");
            System.out.println("2 -> Taxis all trips Details");
            System.out.println("3 -> Exit");
            System.out.print("Enter your choice : ");
            choice = sc.nextInt();

            int pickup_time = 0;
            switch (choice) {

                case 0:
                    boolean flag = true;
                    do {
                        System.out.print("Enter pick up point ('A','B','C','D','E','F') : ");
                        pickup_point = sc.next().toUpperCase().charAt(0);
                        System.out.print("Enter drop up point ('A','B','C','D','E','F') : ");
                        drop_point = sc.next().toUpperCase().charAt(0);

                        if (pickup_point < 'A' || pickup_point > 'F' || drop_point < 'A' || drop_point > 'F') {
                            System.out.println("Invalid points, Try again.");
                            continue;
                        }

                        System.out.print("Enter pick up time : ");
                        pickup_time = sc.nextInt();
                        System.out.println("Confirem (Y/N)? : ");
                        char temp = sc.next().charAt(0);
                        if (temp == 'Y' || temp == 'y') {
                            flag = false;
                        }
                    } while (flag);

                    System.out.println(pickup_point + "," + drop_point);
                    booking.bookTaxi(pickup_point, drop_point, pickup_time);
                    break;

                case 1:
                    System.out.println(
                            "Taxi Id\t\tBooked status\tcurrent location\tpick up point\tdrop point\tfree time\ttotal Earning");
                    System.out.println(
                            "_____________________________________________________________________________________________________________________");
                    for (Taxi t : taxiList) {
                        booking.printTaxiDetails(t);
                    }
                    break;

                case 2:
                    for (Taxi t : taxiList) {
                        t.printTaxiFullDetails(t.tripDetailsList);
                    }
                    break;

                case 3:
                    loop = false;
                    break;

                default:
                    System.out.println("Invalid choice, Enter a valid choice (0-3).\n");
                    break;
            }

        } while (loop);
        sc.close();

    } // Main Method END
} // Booking1 class END
