package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FilterFlightImplement implements FilterFlight {

    @Override
    public List<Flight> excludeDepartureBeforeCurrentTime(List<Flight> original) {
        return original.stream().filter(flight -> !flight.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> excludeArrivalDateBeforeDepartureDate(List<Flight> original) {
        return original.stream().filter(flight -> flight.getSegments().stream()
                .allMatch(s -> s.getDepartureDate().isBefore(s.getArrivalDate()))).collect(Collectors.toList());
    }

    @Override
    public List<Flight> excludeTimeOnEarthMoreThanTwoHours(List<Flight> original) {
        List<Flight> copy = original.stream().collect(Collectors.toList());
        Iterator<Flight> flightIterator = copy.iterator();
        while (flightIterator.hasNext()) {
            Flight flight = flightIterator.next();
            if (flight.getSegments().size() <= 1) {
                continue;
            }
            List<Segment> segmentList = flight.getSegments();
            long timeOnEarth = 0;
            for (int i = 0; i < segmentList.size(); i++) {
                if (i + 1 != segmentList.size()) {
                    Duration duration = Duration.between(segmentList.get(i).getArrivalDate(),
                            segmentList.get(i + 1).getDepartureDate());
                    timeOnEarth += duration.toHours();
                }
            }
            if (timeOnEarth > 2) {
                flightIterator.remove();
            }
        }
        return copy;
    }
}
