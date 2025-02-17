package com.pluralsight.service;

import java.util.List;

import com.pluralsight.model.Ride;

public interface RideService {

	List<Ride> getRides();

    Ride getRide(Integer id);

    Ride createRide(Ride ride);

    Ride updateRide(Ride ride);

    void batchUpdateRides();

    void deleteRide(Integer id);

    void batchCreateRides(Integer numberOfRides);
}