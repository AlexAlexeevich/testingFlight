package com.gridnine.testing;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class FilterFlightImplementTest extends TestCase {

    private List<Segment> listSegment1;
    private List<Segment> listSegment2;
    private List<Segment> listSegment3;
    private FilterFlightImplement testObject = new FilterFlightImplement();

    @Before
    public void setUp() throws Exception {
        listSegment1 = Arrays.asList(new Segment(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(2)));
        listSegment2 = Arrays.asList(new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5)));
        listSegment3 = Arrays.asList(new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(2)));
    }

    @Test
    public void testExcludeDepartureBeforeCurrentTime() {
        List<Flight> expectedFlight = Arrays.asList(new Flight(listSegment1), new Flight(listSegment2));
        List<Flight> expected = testObject.excludeDepartureBeforeCurrentTime(expectedFlight);

        List<Flight> actual = Arrays.asList(new Flight(listSegment2));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExcludeArrivalDateBeforeDepartureDate() {
        List<Flight> expectedFlight1 = Arrays.asList(new Flight(listSegment3), new Flight(listSegment2));
        List<Flight> expected = testObject.excludeArrivalDateBeforeDepartureDate(expectedFlight1);

        List<Flight> actual = Arrays.asList(new Flight(listSegment2));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExcludeTimeOnEarthMoreThanTwoHours() {
        List<Flight> expectedFlight1 = Arrays.asList(new Flight(listSegment1), new Flight(listSegment2));
        List<Flight> expected = testObject.excludeTimeOnEarthMoreThanTwoHours(expectedFlight1);

        List<Flight> actual = Arrays.asList(new Flight(listSegment1), new Flight(listSegment2));

        Assert.assertEquals(expected, actual);
    }
}