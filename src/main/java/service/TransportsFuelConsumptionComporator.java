package service;

import entity.transports.AbstractTransport;

import java.util.Comparator;

public class TransportsFuelConsumptionComporator implements Comparator<AbstractTransport> {

    @Override
    public int compare(AbstractTransport o1, AbstractTransport o2) {
        if (o1.getFuelConsumptionPerOneHundredKm() - o2.getFuelConsumptionPerOneHundredKm() > 0) {
            return 1;
        }
        if (o1.getFuelConsumptionPerOneHundredKm() - o2.getFuelConsumptionPerOneHundredKm() < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
