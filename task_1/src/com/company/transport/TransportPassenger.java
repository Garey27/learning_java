package com.company.transport;

public final class TransportPassenger implements TransportGroup {
    @Override
    public int Code() {
        return 100;
    }

    @Override
    public double FuelPrice() {
        return 46.10;
    }

    @Override
    public double FuelConsumption() {
        return 12.5;
    }


    @Override
    public String toString() {
        return "Легковой транспорт";
    }
}
