package entity.transports;

import entity.TransportDriver;

public class Truck extends AbstractTransport {
    private int loadCapacity;

    public Truck(long transportId, TransportDriver transportDriver, TransportType transportType, String model,
                 int maxSpeed, int fuelCapacity, double fuelConsumptionPerOneHundredKm, int transportCurrentPrice,
                 int loadCapacity) {
        super(transportId, transportDriver, transportType, model, maxSpeed, fuelCapacity,
                fuelConsumptionPerOneHundredKm, transportCurrentPrice);
        this.loadCapacity = loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    @Override
    public String toString() {
        return super.toString() +
                "; loadCapacity = " + loadCapacity;
    }

    @Override
    public String toOutputInFile() {
        return super.toOutputInFile().replace("\n", ", " + loadCapacity + ",\n");
    }
}
