/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.dao;

import com.fo.superherosighting.dto.Hero;
import com.fo.superherosighting.dto.Location;
import com.fo.superherosighting.dto.Organization;
import com.fo.superherosighting.dto.Sighting;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author TheFemiFactor
 */
public class HeroDaoJdbcImplTest {
    
    HeroDao heroDao;
    SightingDao sightingDao;
    LocationDao locationDao;
    OrgDao orgDao;
    
    public HeroDaoJdbcImplTest() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        heroDao = ctx.getBean("heroDao", HeroDao.class);
        orgDao = ctx.getBean("orgDao", OrgDao.class);
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSightingByID(sighting.getSightingID());
        }
        List<Hero> heros = heroDao.getAllHeros();
        for (Hero hero : heros) {
            heroDao.deleteHeroByID(hero.getHeroID());
        }
        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocationByID(location.getLocationID());
        }
        List<Organization> orgs = orgDao.getAllOrgs();
        for (Organization org : orgs) {
            orgDao.deleteOrg(org.getOrgID());
        }
    }
    
    @After
    public void tearDown() {
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSightingByID(sighting.getSightingID());
        }
        List<Hero> heros = heroDao.getAllHeros();
        for (Hero hero : heros) {
            heroDao.deleteHeroByID(hero.getHeroID());
        }
        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocationByID(location.getLocationID());
        }
        List<Organization> orgs = orgDao.getAllOrgs();
        for (Organization org : orgs) {
            orgDao.deleteOrg(org.getOrgID());
        }
    }
    
    /**
     * Test of addHero and getHeroByID methods, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testAddGetHero() {
        System.out.println("addHero");
        Hero hero = new Hero();
        hero.setHeroName("Wonder Woman");
        hero.setDescription("classic superhero");
        heroDao.addHero(hero);
        Hero result = heroDao.getHeroByID(hero.getHeroID());
        assertEquals(hero, result);
    }
    
    /**
     * Test of getAllHeros method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testGetAllHeros() {
        Hero hero1 = new Hero();
        hero1.setHeroName("Hero 1");
        heroDao.addHero(hero1);
        Hero hero2 = new Hero();
        hero2.setHeroName("Hero 2");
        heroDao.addHero(hero2);
        int expResult = 2;
        int result = heroDao.getAllHeros().size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHerosByLocationID method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testGetHerosByLocationID() {
        Hero hero1 = new Hero();
        hero1.setHeroName("Hero 1");
        heroDao.addHero(hero1);
        int heroId1 = hero1.getHeroID();
        Hero hero2 = new Hero();
        hero2.setHeroName("Hero 2");
        heroDao.addHero(hero2);
        int heroId2 = hero2.getHeroID();
        Hero hero3 = new Hero();
        hero3.setHeroName("Hero 3");
        heroDao.addHero(hero3);
        int heroId3 = hero3.getHeroID();
        Location location1 = new Location();
        location1.setLocationName("Location 1");
        locationDao.addLocation(location1);
        Location location2 = new Location();
        location2.setLocationName("Location 2");
        locationDao.addLocation(location2);
        Sighting sighting1 = new Sighting();
        sighting1.setDate(LocalDate.parse("2020-06-11"));
        sighting1.setHero(hero1);
        sighting1.setLocation(location1);
        sightingDao.addSighting(sighting1);
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.parse("2020-02-03"));
        sighting2.setHero(hero2);
        sighting2.setLocation(location2);
        sightingDao.addSighting(sighting2);
        Sighting sighting3 = new Sighting();
        sighting3.setDate(LocalDate.parse("2020-03-06"));
        sighting3.setHero(hero3);
        sighting3.setLocation(location2);
        sightingDao.addSighting(sighting3);
        List<Hero> result = heroDao.getHerosByLocationID(location2.getLocationID());
        assertTrue(result.stream().anyMatch(h -> h.getHeroID() == heroId2));
        assertTrue(result.stream().anyMatch(h -> h.getHeroID() == heroId3));
        assertTrue(result.size() == 2);
    }
    
    /**
     * Test of addHeroOrg and getHerosByOrganizationID methods, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testAddGetHeroOrgs() {
        System.out.println("getHerosByOrganizationID, addHeroOrg");
        Hero hero1 = new Hero();
        hero1.setHeroName("Hero 1");
        heroDao.addHero(hero1);
        int heroId1 = hero1.getHeroID();
        Hero hero2 = new Hero();
        hero2.setHeroName("Hero 2");
        heroDao.addHero(hero2);
        int heroId2 = hero2.getHeroID();
        Hero hero3 = new Hero();
        hero3.setHeroName("Hero 3");
        heroDao.addHero(hero3);
        int heroId3 = hero3.getHeroID();
        Organization org1 = new Organization();
        org1.setOrgName("Org 1");
        orgDao.addOrg(org1);
        int orgId1 = org1.getOrgID();
        Organization org2 = new Organization();
        org2.setOrgName("Org 2");
        orgDao.addOrg(org2);
        int orgId2 = org2.getOrgID();
        heroDao.addHeroOrg(heroId1, orgId1);
        heroDao.addHeroOrg(heroId2, orgId2);
        heroDao.addHeroOrg(heroId3, orgId2);
        List<Hero> result = heroDao.getHerosByOrganizationID(orgId2);
        assertTrue(result.size() == 2);
        assertTrue(result.stream().anyMatch(h -> h.getHeroID() == heroId2));
        assertTrue(result.stream().anyMatch(h -> h.getHeroID() == heroId3));
    }
    
    /**
     * Test of updateHero method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testUpdateHero() {
        System.out.println("updateHero");
        Hero hero = new Hero();
        hero.setHeroName("Hero 1");
        hero.setDescription("Hero 1");
        heroDao.addHero(hero);
        hero.setDescription("Description");
        heroDao.updateHero(hero);
        Hero result = heroDao.getHeroByID(hero.getHeroID());
        assertEquals(hero, result);
    }
    
    /**
     * Test of deleteHeroByID method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testDeleteHeroByID() {
        System.out.println("deleteHeroByID");
        Hero hero = new Hero();
        hero.setHeroName("Hero 1");
        heroDao.addHero(hero);
        int heroID = hero.getHeroID();
        Hero heroFromDao = heroDao.getHeroByID(heroID);
        assertEquals(hero,heroFromDao);
        heroDao.deleteHeroByID(heroID);
        assertNull(heroDao.getHeroByID(heroID));
    }
    
}
