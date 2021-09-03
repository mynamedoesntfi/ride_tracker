package com.pluralsight.service;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("rideService")
public class RideServiceImpl implements RideService {

	@Autowired
	private RideRepository rideRepository;

	//region DATABASE

	//region CREATE
	@Override
	public Ride createRide(Ride ride) {
		return rideRepository.createRide(ride);
	}

	@Override
	public void batchCreateRides(Integer numberOfRides) {
		List<Ride> rides = new ArrayList<>();
		for(int i=0;i<numberOfRides;i++) {
			Ride ride = new Ride();
			ride.setName("RideName" + (i+ 1));
			ride.setDuration(30 + i);
		}

		List<Object[]> entries = new ArrayList<>();
		for(Ride ride : rides)	{
			Object [] entry = { ride.getName(), ride.getDuration(), new Date()};
			entries.add(entry);
		}
		rideRepository.batchCreateRides(entries);
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

	//region DELETE
	@Override
	public void deleteRide(Integer id) {
		rideRepository.deleteRide(id);
	}
	//endregion

	//endregion
}
