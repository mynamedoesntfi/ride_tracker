package com.pluralsight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.pluralsight.model.Ride;
import com.pluralsight.service.RideService;

@Controller
@RequestMapping(value = "ride_tracker")
public class RideController {

	@Autowired
	private RideService rideService;


	//region CREATE
	@PostMapping
	@RequestMapping(value = "createRide")
	public @ResponseBody Ride createRide(@RequestBody Ride ride)	{
		return rideService.createRide(ride);
	}
	//endregion

	//region READ
	@RequestMapping(value = "getAllRides", method = RequestMethod.GET)
	public @ResponseBody List<Ride> getRides() {
		return rideService.getRides();
	}

	@GetMapping
	@RequestMapping(value = "getRide/{id}")
	public @ResponseBody Ride getRide(@PathVariable(value = "id") Integer id) {
		return rideService.getRide(id);
	}
	//endregion

}
