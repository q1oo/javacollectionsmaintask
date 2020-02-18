package entity.transports;

import entity.TransportDriver;
import java.util.Objects;

public class Car extends AbstractTransport {
    TransportCategory transportCategory;
    private int passengerCapacity;

    public Car(long transportId, TransportDriver transportDriver, TransportType transportType, String model,
               int maxSpeed, int fuelCapacity, double fuelConsumptionPerOneHundredKm, int transportCurrentPrice,
               TransportCategory transportCategory, int passengerCapacity) {
        super(transportId, transportDriver, transportType, model, maxSpeed, fuelCapacity,
                fuelConsumptionPerOneHundredKm, transportCurrentPrice);
        this.transportCategory = transportCategory;
        this.passengerCapacity = passengerCapacity;
    }

    public Car(long transportId, TransportDriver transportDriver, TransportType transportType, String model, int maxSpeed, int fuelCapacity, double fuelConsumptionPerOneHundredKm, int transportCurrentPrice) {
        super(transportId, transportDriver, transportType, model, maxSpeed, fuelCapacity, fuelConsumptionPerOneHundredKm, transportCurrentPrice);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        Car car = (Car) o;
        return (this.getTransportId() == car.getTransportId() &&
                this.getTransportDriver().getDriverId() == car.getTransportDriver().getDriverId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
