package com.pluralsight.model;

import java.util.Date;

public class Ride {

	private int id;
	private String name;
	private int duration;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public String getName() {
		return name;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "(id=" + id +
				", name='" + name + '\'' +
				", duration=" + duration + ")";
	}
}
