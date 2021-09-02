package com.pluralsight.repository;

import java.util.List;

import com.pluralsight.model.Ride;

public interface RideRepository {

    //region READ
    Ride getRide(Integer id);

    List<Ride> getRides();

    Ride createRide(Ride ride);
}