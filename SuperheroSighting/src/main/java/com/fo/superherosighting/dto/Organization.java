/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.dto;

import java.util.Objects;

/**
 *
 * @author TheFemiFactor
 */
public class Organization {
    
    private int orgID;
    private String orgName;
    private String description;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;

    public Organization() {}
    public Organization(String orgName, String description, String streetAddress, String city, String state, String zipcode) {
        if (orgName != null && orgName.trim().length() > 0) {
            this.orgName = orgName;
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
    }
    
    public int getOrgID() {
        return orgID;
    }
    public void setOrgID(int orgID) {
        this.orgID = orgID;
    }
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
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
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.orgID;
        hash = 17 * hash + Objects.hashCode(this.orgName);
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + Objects.hashCode(this.streetAddress);
        hash = 17 * hash + Objects.hashCode(this.city);
        hash = 17 * hash + Objects.hashCode(this.state);
        hash = 17 * hash + Objects.hashCode(this.zipcode);
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
        final Organization other = (Organization) obj;
        if (this.orgID != other.orgID) {
            return false;
        }
        if (!Objects.equals(this.orgName, other.orgName)) {
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
        return true;
    }        
}