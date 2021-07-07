import java.util.List;

public interface TaxiRules {
    int cost = 100; // by default final and static

    public List<Taxi> createTaxi(int n);

    public void printTaxiDetails(Taxi t);

    public List<Taxi> getAvailableTaxi(List<Taxi> taxiList, char pickupPoint, int pickupTime);

    public List<Taxi> getTaxisInSameLocation(List<Taxi> availableTaxis, char pickUpPoint);

    public List<Taxi> getTaxisInNearestLocation(List<Taxi> availableTaxis, char pickUpPoint);

    public void bookTaxi(String customerID, char p_point, char d_point, int p_time);
}
