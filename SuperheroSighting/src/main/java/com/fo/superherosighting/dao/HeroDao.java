/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.dao;

import com.fo.superherosighting.dto.Hero;
import java.util.List;

/**
 *
 * @author TheFemiFactor
 */
public interface HeroDao {

    void addHero(Hero hero);

    void addHeroOrg(int heroID, int orgID);

    void addHeroSuperPower(int heroID, int superPowerID);

    void deleteHeroByID(int heroID);

    void deleteHeroOrg(int heroID, int orgID);

    void deleteHeroSuperPower(int heroID, int superPowerID);

    List<Hero> getAllHeros();

    Hero getHeroByID(int heroID);

    List<Hero> getHerosByLocationID(int locationID);

    List<Hero> getHerosByOrganizationID(int organizationID);

    void updateHero(Hero hero);

}
