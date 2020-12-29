package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> original = FlightBuilder.createFlights();
        System.out.println("Оригинал: ");
        System.out.println(original);

        FilterFlight filter = new FilterFlightImplement();

        System.out.println("\n" + "Набор, без вылета до текущего времени: ");
        System.out.println(filter.excludeDepartureBeforeCurrentTime(original));

        System.out.println("\n" + "Набор, без сегментов прилета раньше вылета: ");
        System.out.println(filter.excludeArrivalDateBeforeDepartureDate(original));

        System.out.println("\n" + "Набор, с временем на земле не более двух часов: ");
        System.out.println(filter.excludeTimeOnEarthMoreThanTwoHours(original));

    }
}
