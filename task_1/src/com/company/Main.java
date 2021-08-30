package com.company;


import com.company.transport.*;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Main {
    final static HashMap<Integer, TransportGroup> transportGroups;
    final static String[] data = new String[]
    {
            "C100_1-100",
            "C200_1-120-1200",
            "C300_1-120-30",
            "C400_1-80-20",
            "C100_2-50",
            "C200_2-40-1000",
            "C300_2-200-45",
            "C400_2-10-20",
            "C100_3-10",
            "C200_3-170-1100",
            "C300_3-150-29",
            "C400_3-100-28",
            "C100_1-300",
            "C200_1-100-750",
            "C300_1-32-15"
    };
    static TransportGroup minConsuptionGroup = null;
    static TransportGroup maxConsuptionGroup = null;
    static {
        transportGroups = new HashMap<>();
        transportGroups.put(100, new TransportPassenger());
        transportGroups.put(200, new TransportTruck());
        transportGroups.put(300, new TransportBus());
        transportGroups.put(400, new TransportCrane());
        transportGroups.forEach((k,v) -> {
            if(minConsuptionGroup == null || (v.FuelConsumption() < minConsuptionGroup.FuelConsumption()))
            {
                minConsuptionGroup = v;
            }
            if(maxConsuptionGroup == null || (v.FuelConsumption() > maxConsuptionGroup.FuelConsumption()))
            {
                maxConsuptionGroup = v;
            }
        });
    }

    public static double consumptionForCar(Transport car)
    {
        return ((car.getMilage() / 100.0) * car.getTransportGroup().FuelConsumption()) * car.getTransportGroup().FuelPrice();
    }

    public static double totalCarsConsumption(HashMap<String, Transport> cars, TransportGroup transportGroup)
    {
        double totalConsuption = 0;
        for (HashMap.Entry<String, Transport> entry : cars.entrySet()) {
            if(transportGroup == null || (entry.getValue().getTransportGroup().Code() == transportGroup.Code())) {
                totalConsuption += consumptionForCar(entry.getValue());
            }
        }
        return totalConsuption;
    }
    public static Transport parseCar(String data) throws Exception {
        // Maybe better regex but ¯\_(ツ)_/¯
        String[] parts = data.split("-");
        String id = "";
        int milage = 0;
        int extraInfo = 0;
        int i = 0;
        for (String part: parts) {
            i++;
            switch(i)
            {
                case 1:
                    id = part;
                    break;
                case 2:
                    milage = Integer.parseInt(part);
                    break;
                case 3:
                    extraInfo = Integer.parseInt(part);
                    break;
            }
        }
        int transportId = 0;
        if(!id.isEmpty())
        {
            int indexOfC = id.indexOf("C");
            if(indexOfC != -1)
            {
                int indexOfUnderScore = id.indexOf("_");
                if(indexOfUnderScore != -1)
                {
                    transportId = Integer.parseInt(id.substring(indexOfC + 1, indexOfUnderScore));
                }
            }
        }

        if(transportId == 0) {
            throw new Exception("Invalid data for parsing (id is invalid)");
        }
        if(!transportGroups.containsKey(transportId))
        {
            throw new Exception("Invalid transportGroup " + transportId);
        }
        return new Transport(id, milage, transportGroups.get(transportId) ,extraInfo);
    }
    public static void main(String[] args) throws Exception {
        HashMap<String, Transport> transportInstances = new HashMap<>();
        for (String car: data) {
            Transport transport = parseCar(car);
            if(transportInstances.containsKey(transport.getId()))
            {
                transportInstances.get(transport.getId()).addMilage(transport.getMilage());
            }
            else
            {
                transportInstances.put(transport.getId(), transport);
            }
        }

        // Непонять имеется ввиду самый экономичный/растратный тип или же тип авто из входных данных.
        // Предположим что первое но выполним оба случая 0)
        System.out.printf("Самый растратный тип авто: %s\n", maxConsuptionGroup.toString());
        System.out.printf("Самый экономичный тип авто: %s\n", minConsuptionGroup.toString());

        Pair<TransportGroup, Double> minCostConsuptionGroup = null;
        Pair<TransportGroup, Double> maxCostConsuptionGroup = null;

        System.out.printf("Общая стоимость расходов на ГСМ: %.2f\n", totalCarsConsumption(transportInstances, null));
        for (HashMap.Entry<Integer, TransportGroup> entry : transportGroups.entrySet()) {
            double consumption = totalCarsConsumption(transportInstances, entry.getValue());

            if(minCostConsuptionGroup == null || (consumption < minCostConsuptionGroup.getValue()))
            {
                minCostConsuptionGroup = new Pair<>(entry.getValue(), consumption);
            }
            if(maxCostConsuptionGroup == null || (consumption > maxCostConsuptionGroup.getValue()))
            {
                maxCostConsuptionGroup = new Pair<>(entry.getValue(), consumption);
            }
            System.out.printf("Стоимость расходов на класс авто \"%s\" :%.2f\n", entry.getValue().toString(), consumption);
        }

        if(maxCostConsuptionGroup != null) {
            System.out.printf("Тип авто имеющий наибольшую стоимость расходов %s\n", maxCostConsuptionGroup.getKey().toString());
        }
        if(minCostConsuptionGroup != null) {
            System.out.printf("Тип авто имеющий наименьшую стоимость расходов %s\n", minCostConsuptionGroup.getKey().toString());
        }


        transportGroups.forEach((k,v) -> {
            System.out.printf("Информация о ТС: %s\n", v.toString());

            transportInstances.forEach((kt,vt) -> {
                if(v.Code() == vt.getTransportGroup().Code())
                {
                    System.out.println(vt);
                }
            });
        });
    }
}
