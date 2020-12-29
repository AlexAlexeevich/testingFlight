package com.gridnine.testing;

import java.util.List;

public interface FilterFlight {
    List<Flight> excludeDepartureBeforeCurrentTime(List<Flight> original);

    List<Flight> excludeArrivalDateBeforeDepartureDate(List<Flight> original);

    List<Flight> excludeTimeOnEarthMoreThanTwoHours(List<Flight> original);
}
