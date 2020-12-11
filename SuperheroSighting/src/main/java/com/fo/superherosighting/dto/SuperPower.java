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
public class SuperPower {
    
    private int superPowerID;
    private String description;
    
    public SuperPower() {}
    public SuperPower(String description) {
        if (description != null && description.trim().length() > 0) {
            this.description = description;
        }
    }

    public int getSuperPowerID() {
        return superPowerID;
    }
    public void setSuperPowerID(int superPowerID) {
        this.superPowerID = superPowerID;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.superPowerID;
        hash = 17 * hash + Objects.hashCode(this.description);
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
        final SuperPower other = (SuperPower) obj;
        if (this.superPowerID != other.superPowerID) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }        
}
