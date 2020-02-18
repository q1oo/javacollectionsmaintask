import entity.TransportStaiton;
import service.FileManager;

import static service.FileNames.CARS;

public class MainApp {
    public static void main(String[] args) {
        TransportStaiton staiton = new TransportStaiton();
        System.out.println(staiton.getSumOfTransportPriceFromDatabase());
        System.out.println(staiton.sortListOfTransportsByFuelConsumptionFromDatabase(staiton.getListOfAllTransportsFromDatabase()));
        System.out.println(staiton.getListOfTransportsByMaxSpeedRangeFromDatabase(0, 160));
    }
}
