import java.util.List;

public interface TaxiRules {
    int cost = 100; // by default final and static

    public void printTaxiDetails(Taxi t);

    public List<Taxi> getAvailableTaxi(List<Taxi> taxiList, char pickupPoint, int pickupTime);

    public void bookTaxi(char p_point, char d_point, int p_time);
}
