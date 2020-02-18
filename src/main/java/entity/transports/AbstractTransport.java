package entity.transports;

import entity.TransportDriver;
import java.util.Objects;

abstract public class AbstractTransport {
    private long transportId;
    TransportDriver transportDriver;
    TransportType transportType;
    private String model;
    private int maxSpeed;
    private int fuelCapacity;
    private double fuelConsumptionPerOneHundredKm;
    private int transportCurrentPrice;

    public AbstractTransport(long transportId, TransportDriver transportDriver, TransportType transportType,
                             String model, int maxSpeed, int fuelCapacity, double fuelConsumptionPerOneHundredKm,
                             int transportCurrentPrice) {
        this.transportId = transportId;
        this.transportDriver = transportDriver;
        this.transportType = transportType;
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.fuelCapacity = fuelCapacity;
        this.fuelConsumptionPerOneHundredKm = fuelConsumptionPerOneHundredKm;
        this.transportCurrentPrice = transportCurrentPrice;
    }

    public long getTransportId() {
        return transportId;
    }

    public TransportDriver getTransportDriver() {
        return transportDriver;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public double getFuelConsumptionPerOneHundredKm() {
        return fuelConsumptionPerOneHundredKm;
    }

    public int getTransportCurrentPrice() {
        return transportCurrentPrice;
    }

    @Override
    public String toString() {
        return "\n-----------------------------------------------------------------------------------------------" +
                "---------------------------------------------------------------------------------------------------------------------------------------------------------------" +
                "\ntransportId = " + transportId +
                "; transportDriver = " + "("+ transportDriver.getDriverId() + ")" + transportDriver.getFirstName() + " " + transportDriver.getLastName() +
                "; transportType = " + transportType +
                "; model = " + model +
                "; maxSpeed = " + maxSpeed +
                "; fuelCapacity = " + fuelCapacity +
                "; fuelConsumptionPerOneHundredKm = " + fuelConsumptionPerOneHundredKm +
                "; transportCurrentPrice = " + transportCurrentPrice;
    }

    public String toOutputInFile() {
        return transportId +
               ", " + transportDriver.getDriverId() +
               ", " + transportType +
               ", " + model +
               ", " + maxSpeed +
               ", " + fuelCapacity +
               ", " + fuelConsumptionPerOneHundredKm +
               ", " + transportCurrentPrice + "\n";
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

        return Objects.hash(transportId, transportDriver, transportType, model, maxSpeed, fuelCapacity, fuelConsumptionPerOneHundredKm, transportCurrentPrice);
    }
}


