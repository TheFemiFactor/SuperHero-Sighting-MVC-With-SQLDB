/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.dao;

import com.fo.superherosighting.dto.Hero;
import com.fo.superherosighting.dto.Location;
import com.fo.superherosighting.dto.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TheFemiFactor
 */
public class SightingDaoJdbcImpl implements SightingDao {
    
    private static final String SQL_INSERT_SIGHTING = "insert into Sighting (LocationID, SightingDate, HeroID) values (?,?,?)";
    private static final String SQL_SELECT_SIGHTING_BY_ID =
            "select Hero.*, Location.*, Sighting.SightingID, Sighting.SightingDate from Location" +
            " inner join Sighting on Location.LocationID = Sighting.LocationID" +
            " inner join Hero on Hero.HeroID = Sighting.HeroID" +
            " where Sighting.SightingID = ?";
    private static final String SQL_SELECT_ALL_SIGHTINGS =
            "select Hero.*, Location.*, Sighting.SightingID, Sighting.SightingDate from Location" +
            " inner join Sighting on Location.LocationID = Sighting.LocationID" +
            " inner join Hero on Hero.HeroID = Sighting.HeroID order by Sighting.SightingDate desc";
    private static final String SQL_SELECT_SIGHTINGS_ORDER_BY_DATE_LIMIT_10 =
            "select Hero.*, Location.*, Sighting.SightingID, Sighting.SightingDate from Location" +
            " inner join Sighting on Location.LocationID = Sighting.LocationID" +
            " inner join Hero on Hero.HeroID = Sighting.HeroID order by Sighting.SightingDate desc limit 0,10";
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE =
            "select Hero.*, Location.*, Sighting.SightingID, Sighting.SightingDate from Location" +
            " inner join Sighting on Location.LocationID = Sighting.LocationID" +
            " inner join Hero on Hero.HeroID = Sighting.HeroID" +
            " where Sighting.SightingDate = ?";
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID =
            "select Hero.*, Location.*, Sighting.SightingID, Sighting.SightingDate from Location" +
            " inner join Sighting on Location.LocationID = Sighting.LocationID" +
            " inner join Hero on Hero.HeroID = Sighting.HeroID" +
            " where Location.LocationID = ?";
    private static final String SQL_UPDATE_SIGHTING_BY_ID = "update Sighting set LocationID = ?, SightingDate = ?, HeroID = ? where SightingID = ?";
    private static final String SQL_DELETE_SIGHTING_BY_ID = "delete from Sighting where SightingID = ?";
    
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        if (sighting != null && sighting.getDate() != null && sighting.getHero() != null && sighting.getLocation() != null && sighting.getHero().getHeroID() > 0 && sighting.getLocation().getLocationID() > 0) {
            jdbcTemplate.update(SQL_INSERT_SIGHTING, sighting.getLocation().getLocationID(), sighting.getDate().toString(), sighting.getHero().getHeroID());
            int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
            sighting.setSightingID(id);
        }
    }
    @Override
    public Sighting getSightingByID(int sightingID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
    }
    @Override
    public List<Sighting> getAllSightingsToLimit() {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_ORDER_BY_DATE_LIMIT_10, new SightingMapper());
    }
    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE, new SightingMapper(), date.toString());
    }
    @Override
    public List<Sighting> getSightingsByLocationID(int locationID) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATION_ID, new SightingMapper(), locationID);
    }
    @Override
    public void updateSighting(Sighting sighting) {
        if (sighting != null && sighting.getDate() != null && sighting.getHero() != null && sighting.getLocation() != null && sighting.getSightingID() > 0 && sighting.getHero().getHeroID() > 0 && sighting.getLocation().getLocationID() > 0) {
            jdbcTemplate.update(SQL_UPDATE_SIGHTING_BY_ID, sighting.getLocation().getLocationID(), sighting.getDate().toString(), sighting.getHero().getHeroID(), sighting.getSightingID());
        }
    }
    @Override
    public void deleteSightingByID(int sightingID) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING_BY_ID, sightingID);
    }
    
    private static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroID(rs.getInt("HeroID"));
            hero.setHeroName(rs.getString("HeroName"));
            hero.setDescription(rs.getString("Description"));
            Location location = new Location();
            location.setLocationID(rs.getInt("LocationID"));
            location.setLocationName(rs.getString("LocationName"));
            location.setDescription(rs.getString("Description"));
            location.setStreetAddress(rs.getString("StreetAddress"));
            location.setCity(rs.getString("City"));
            location.setState(rs.getString("State"));
            location.setZipcode(rs.getString("Zipcode"));
            location.setLatitude(rs.getBigDecimal("Latitude"));
            location.setLongitude(rs.getBigDecimal("Longitude"));
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("SightingID"));
            sighting.setHero(hero);
            sighting.setLocation(location);
            sighting.setDate(LocalDate.parse(rs.getDate("SightingDate").toString()));
            return sighting;
        }
    }
}