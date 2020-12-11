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
public class Hero {
    
    private int heroID;
    private String heroName;
    private String description;
    
    public Hero() {}
    public Hero(String heroName, String description) {
        if (heroName != null && heroName.trim().length() > 0) {
            this.heroName = heroName;           
        }
        if (description != null && description.trim().length() > 0) {
            this.description = description;
        }
    }

    public int getHeroID() {
        return heroID;
    }
    public void setHeroID(int heroID) {
        this.heroID = heroID;
    }
    public String getHeroName() {
        return heroName;
    }
    public void setHeroName(String heroName) {
        this.heroName = heroName;
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
        hash = 13 * hash + this.heroID;
        hash = 13 * hash + Objects.hashCode(this.heroName);
        hash = 13 * hash + Objects.hashCode(this.description);
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
        final Hero other = (Hero) obj;
        if (this.heroID != other.heroID) {
            return false;
        }
        if (!Objects.equals(this.heroName, other.heroName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
}
