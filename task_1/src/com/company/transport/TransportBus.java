package com.company.transport;

public final class TransportBus implements TransportGroup {
    @Override
    public int Code() {
        return 300;
    }

    @Override
    public double FuelPrice() {
        return 47.50;
    }

    @Override
    public String toString() {

        return "Пассажирский транспорт";
    }

    @Override
    public double FuelConsumption() {
        return 11.5;
    }
}
