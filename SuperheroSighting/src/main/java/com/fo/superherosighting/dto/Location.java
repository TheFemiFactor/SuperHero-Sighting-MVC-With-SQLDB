/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 *
 * @author TheFemiFactor
 */
public class Location {
    
    private int locationID;
    private String locationName;
    private String description;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    
    public Location() {}
    public Location(String locationName, String description, String streetAddress, String city, String state, String zipcode, String latitude, String longitude) {
        if (locationName != null && locationName.trim().length() > 0) {
            this.locationName = locationName;
        }
        if (description != null && description.trim().length() > 0) {
            this.description = description;
        }
        if (streetAddress != null && streetAddress.trim().length() > 0) {
            this.streetAddress = streetAddress;
        }
        if (city != null && city.trim().length() > 0) {
            this.city = city;
        }
        if (state != null && state.trim().length() > 0) {
            this.state = state;
        }
        if (zipcode != null && zipcode.trim().length() > 0) {
            this.zipcode = zipcode;
        }
        if (latitude != null && latitude.trim().length() > 0) {
            try {
                BigDecimal lat = new BigDecimal(latitude).setScale(6, RoundingMode.HALF_UP);
                if(lat.abs().compareTo(new BigDecimal("90")) <= 0) {
                    this.latitude = lat;
                }
            } catch (NumberFormatException e) {}
        }
        if (longitude != null && longitude.trim().length() > 0) {
            try {
                BigDecimal lon = new BigDecimal(longitude).setScale(6, RoundingMode.HALF_UP);
                if (lon.abs().compareTo(new BigDecimal("180")) <= 0) {
                    this.longitude= lon;
                }                
            } catch (NumberFormatException e) {}
        }
    }
    
    public int getLocationID() {
        return locationID;
    }    
    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }    
    public String getLocationName() {
        return locationName;
    }    
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }    
    public String getDescription() {
        return description;
    }    
    public void setDescription(String description) {
        this.description = description;
    }    
    public String getStreetAddress() {
        return streetAddress;
    }    
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }    
    public String getCity() {
        return city;
    }    
    public void setCity(String city) {
        this.city = city;
    }    
    public String getState() {
        return state;
    }    
    public void setState(String state) {
        this.state = state;
    }    
    public String getZipcode() {
        return zipcode;
    }    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }    
    public BigDecimal getLatitude() {
        return latitude;
    }    
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }    
    public BigDecimal getLongitude() {
        return longitude;
    }    
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.locationID;
        hash = 17 * hash + Objects.hashCode(this.locationName);
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + Objects.hashCode(this.streetAddress);
        hash = 17 * hash + Objects.hashCode(this.city);
        hash = 17 * hash + Objects.hashCode(this.state);
        hash = 17 * hash + Objects.hashCode(this.zipcode);
        hash = 17 * hash + Objects.hashCode(this.latitude);
        hash = 17 * hash + Objects.hashCode(this.longitude);
        return hash;
    }    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.locationID != other.locationID) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.streetAddress, other.streetAddress)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zipcode, other.zipcode)) {
            return false;
        }
        if (
                !(this.latitude == null && other.latitude == null) &&
                !(this.latitude.equals(other.latitude))
                ) {
            return false;
        }
        if (
                !(this.longitude == null && other.longitude == null) &&
                !(this.longitude.equals(other.longitude))
                ) {
            return false;
        }
        return true;
    }                    
}
