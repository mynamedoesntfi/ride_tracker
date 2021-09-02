package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerWriteTest {

    @Test(timeout=3000)
    public void testCreateRide() 	{
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = new Ride();
        ride.setName("Sagebrush Trail Ride");
        ride.setDuration(35);

        ride = restTemplate.postForObject("http://localhost:8080/ride_tracker/createRide", ride, Ride.class);

        System.out.println("Ride: " + ride);
    }

    @Test(timeout=3000)
    public void testUpdateRide() {
        RestTemplate restTemplate = new RestTemplate();

        //TODO changeable id
        int id = 1;

        Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/getRide/" + id, Ride.class);

        ride.setDuration(ride.getDuration() + 1);

        restTemplate.put("http://localhost:8080/ride_tracker/updateRide/", ride);

        System.out.println("Updated ride ...\nUpdated Ride: " + ride);
    }

    @Test(timeout=3000)
    public void testBatchUpdateRide() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject("http://localhost:8080/ride_tracker/batchUpdateRides/", Ride.class);
    }
}
