package com.pluralsight.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pluralsight.repository.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.RideRepository;

@Service("rideService")
public class RideServiceImpl implements RideService {

	@Autowired
	private RideRepository rideRepository;

	//region CREATE
	@Override
	public Ride createRide(Ride ride) {
		return rideRepository.createRide(ride);
	}
	//endregion

	//region READ
	@Override
	public List<Ride> getRides() {
		return rideRepository.getRides();
	}

	@Override
	public Ride getRide(Integer id)	{
		return rideRepository.getRide(id);
	}
	//endregion

	//region UPDATE
	@Override
	public Ride updateRide(Ride ride) {
		return rideRepository.updateRide(ride);
	}

	@Override
	public void batchUpdateRides() {
		List<Ride> rides = rideRepository.getRides();

		List<Object[]> pairs = new ArrayList<>();
		for(Ride ride : rides)	{
			Object [] pair = { new Date(), ride.getId()};
			pairs.add(pair);
		}

		rideRepository.batchUpdateRides(pairs);
	}

	//endregion
}
