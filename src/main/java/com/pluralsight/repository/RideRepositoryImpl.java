package com.pluralsight.repository;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//region CREATE
	@Override
	public Ride createRide(Ride ride) {
//		jdbcTemplate.update("insert into ride (name, duration) values (?, ?)", ride.getName(), ride.getDuration());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("insert into ride (name, duration) values (?, ?)", new String [] {"id"});
				ps.setString(1, ride.getName());
				ps.setInt(2, ride.getDuration());
				return ps;
			}
		}, keyHolder);
		Number id = keyHolder.getKey();

		assert id != null;
		return getRide(id.intValue());
	}

	@Override
	public void batchCreateRides(List<Object[]> entries) {
//		jdbcTemplate.batchUpdate("insert into ride (name, duration, ride_date) values(?, ?, ?)", new BatchPreparedStatementSetter() {
//			@Override
//			public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
//				Object[] entry = entries.get(i);
//				preparedStatement.setString(1, entry[0].toString());
//				preparedStatement.setString(2, entry[1].toString());
//				preparedStatement.setDate(3, new Date(Long.parseLong(entry[2].toString())));
//			}
//
//			@Override
//			public int getBatchSize() {
//				return entries.size();
//			}
//		});
		return;
	}

	//endregion

	//region READ
	@Override
	public Ride getRide(Integer id)	{
		return jdbcTemplate.queryForObject("select * from ride where id = ?",
				new RideRowMapper(),
				id);
	}

	@Override
	public List<Ride> getRides() {
		return jdbcTemplate.query("select * from ride", new RideRowMapper());
	}
	//endregion

	//region UPDATE
	@Override
	public Ride updateRide(Ride ride) {
		jdbcTemplate.update("update ride set name = ?, duration = ? where id = ?",
				ride.getName(),
				ride.getDuration(),
				ride.getId());
		return ride;
	}

	@Override
	public void batchUpdateRides(List<Object[]> pairs) {
		jdbcTemplate.batchUpdate("update ride set ride_date = ? where id = ?",  pairs);
	}

	//endregion

	//region DELETE
	@Override
	public void deleteRide(Integer id) {
		jdbcTemplate.update("delete from ride where id = ?", id);
	}
	//endregion
}