package com.company.transport;

public final class TransportCrane implements TransportGroup {
    @Override
    public int Code() {
        return 400;
    }
    @Override
    public double FuelPrice() {
        return 48.90;
    }

    @Override
    public String toString() {
        return "Тяжелый транспорт (Краны)";
    }

    @Override
    public double FuelConsumption() {
        return 20;
    }
}
