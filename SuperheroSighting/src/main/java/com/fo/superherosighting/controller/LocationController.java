/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.controller;

import com.fo.superherosighting.dao.LocationDao;
import com.fo.superherosighting.dto.Location;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author TheFemiFactor
 */
@Controller
public class LocationController {
    LocationDao locationDao;
    
    @Inject
    public LocationController(LocationDao locationDao) {
        this.locationDao = locationDao;
    }
    public LocationController() {}
    
    @RequestMapping(value="location", method=RequestMethod.GET)
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        return "/location";
    }
    @RequestMapping(value="addLocation", method=RequestMethod.POST)
    public String addLocation(String locationName, String description, String streetAddress, String city, String state, String zipcode, String latitude, String longitude) {
        Location location = new Location(locationName,description,streetAddress,city,state,zipcode,latitude,longitude);        
        locationDao.addLocation(location);
        return "redirect:/location";
    }
    @RequestMapping(value="editLocation/{locationID}", method=RequestMethod.GET)
    public String editLocation(@PathVariable("locationID") int id, Model model) {
        Location location = locationDao.getLocationByID(id);
        model.addAttribute("locationToEdit", location);
        return "/editLocation";
    }
    @RequestMapping(value="updateLocation", method=RequestMethod.POST)
    public String updateLocation(int locationID, String locationName, String description, String streetAddress, String city, String state, String zipcode, String latitude, String longitude) {
        Location location = new Location(locationName,description,streetAddress,city,state,zipcode,latitude,longitude);
        location.setLocationID(locationID);       
        locationDao.updateLocation(location);
        return "redirect:/location";
    }
    @RequestMapping(value="deleteLocation/{locationID}", method=RequestMethod.GET)
    public String deleteLocation(@PathVariable("locationID") int id) {
        locationDao.deleteLocationByID(id);
        return "redirect:/location";
    }
}
