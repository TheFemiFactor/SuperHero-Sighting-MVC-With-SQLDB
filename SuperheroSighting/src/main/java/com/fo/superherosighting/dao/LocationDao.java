/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.dao;

import com.fo.superherosighting.dto.Location;
import java.util.List;

/**
 *
 * @author TheFemiFactor
 */
public interface LocationDao {

    void addLocation(Location location);

    void deleteLocationByID(int locationID);

    List<Location> getAllLocations();

    Location getLocationByID(int locationID);

    List<Location> getLocationsByHeroID(int heroID);

    void updateLocation(Location location);

}

