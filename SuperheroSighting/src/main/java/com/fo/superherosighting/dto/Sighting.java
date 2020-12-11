/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author TheFemiFactor
 */
public class Sighting {
    
    private int sightingID;
    private Hero hero;
    private Location location;
    private LocalDate date;
    
    public int getSightingID() {
        return sightingID;
    }
    public void setSightingID(int sightingID) {
        this.sightingID = sightingID;
    }
    public Hero getHero() {
        return hero;
    }
    public void setHero(Hero hero) {
        this.hero = hero;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.sightingID;
        hash = 23 * hash + Objects.hashCode(this.hero.getHeroID());
        hash = 23 * hash + Objects.hashCode(this.location.getLocationID());
        hash = 23 * hash + Objects.hashCode(this.date);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingID != other.sightingID) {
            return false;
        }
        if (this.hero.getHeroID() != other.hero.getHeroID()) {
            return false;
        }
        if (this.location.getLocationID() != other.location.getLocationID()) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }        
}
