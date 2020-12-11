/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.dao;

import com.fo.superherosighting.dto.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author TheFemiFactor
 */
public interface SightingDao {

    void addSighting(Sighting sighting);

    void deleteSightingByID(int sightingID);

    Sighting getSightingByID(int sightingID);
    
    List<Sighting> getAllSightings();
    
    public List<Sighting> getAllSightingsToLimit();

    List<Sighting> getSightingsByDate(LocalDate date);

    List<Sighting> getSightingsByLocationID(int locationID);

    void updateSighting(Sighting sighting);

}
