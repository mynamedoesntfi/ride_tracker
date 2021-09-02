package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerReadTest {

	@Test(timeout=3000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8080/ride_tracker/getAllRides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		System.out.println("ALL RIDES\n---------");
		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
		System.out.println("---------");
	}

	@Test(timeout=3000)
	public void testGetRide() {
		RestTemplate restTemplate = new RestTemplate();

		//TODO changeable id
		int id = 1;

		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/getRide/" + id, Ride.class);

		System.out.println("Fetching ride ...\nRide: " + ride);
	}
}
