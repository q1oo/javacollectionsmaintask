package entity.transports;

import entity.TransportDriver;

public class Bus extends AbstractTransport {
    TransportCategory transportCategory;
    private int passengerCapacity;

    public Bus(long transportId, TransportDriver transportDriver, TransportType transportType, String model,
               int maxSpeed, int fuelCapacity, double fuelConsumptionPerOneHundredKm, int transportCurrentPrice,
               TransportCategory transportCategory, int passengerCapacity) {
        super(transportId, transportDriver, transportType, model, maxSpeed, fuelCapacity,
                fuelConsumptionPerOneHundredKm, transportCurrentPrice);
        this.transportCategory = transportCategory;
        this.passengerCapacity = passengerCapacity;
    }

    public TransportCategory getTransportCategory() {
        return transportCategory;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public String toString() {
        return super.toString() +
                "; transportCategory = " + transportCategory +
                "; passengerCapacity = " + passengerCapacity;
    }

    @Override
    public String toOutputInFile() {
        return super.toOutputInFile().replace("\n", ", " + transportCategory + ", " +passengerCapacity + ",\n");
    }
}
