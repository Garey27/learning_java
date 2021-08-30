package com.company.transport;

public final class TransportTruck implements TransportGroup {
    @Override
    public int Code() {
        return 200;
    }

    @Override
    public double FuelPrice() {
        return 48.90;
    }

    @Override
    public double FuelConsumption() {
        return 12;
    }


    @Override
    public String toString() {
        return "Грузовой транспорт";
    }
}
