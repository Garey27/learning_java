package com.company.transport;

public class Transport {
    private String id;
    private int milage;
    private TransportGroup transportGroup;
    private int extra_info;

    public Transport(String id, int milage, TransportGroup transportGroup, int extra_info) {
        this.id = id;
        this.milage = milage;
        this.transportGroup = transportGroup;
        this.extra_info = extra_info;
    }

    @Override
    public String toString() {
        return String.format("Номер авто: %s Тип:%s Пробег: %d Доп параметр:\"%d\"", id, transportGroup.toString(), milage, extra_info);
    }

    public String getId() {
        return id;
    }

    public int getMilage() {
        return milage;
    }

    public void addMilage(int milage) {
        this.milage += milage;
    }

    public TransportGroup getTransportGroup() {
        return transportGroup;
    }
}
