/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.dao;

import com.fo.superherosighting.dto.Hero;
import com.fo.superherosighting.dto.SuperPower;
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
public class SuperPowerDaoJdbcImplTest {
    
    SuperPowerDao superPowerDao;
    HeroDao heroDao;
    
    public SuperPowerDaoJdbcImplTest() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        superPowerDao = ctx.getBean("superPowerDao", SuperPowerDao.class);
        heroDao = ctx.getBean("heroDao", HeroDao.class);
        List<SuperPower> superPowers = superPowerDao.getAllSuperPowers();
        for (SuperPower sp : superPowers) {
            superPowerDao.deleteSuperPower(sp.getSuperPowerID());
        }
        List<Hero> heros = heroDao.getAllHeros();
        for (Hero hero : heros) {
            heroDao.deleteHeroByID(hero.getHeroID());
        }
    }
    
    @After
    public void tearDown() {
        List<SuperPower> superPowers = superPowerDao.getAllSuperPowers();
        for (SuperPower sp : superPowers) {
            superPowerDao.deleteSuperPower(sp.getSuperPowerID());
        }
        List<Hero> heros = heroDao.getAllHeros();
        for (Hero hero : heros) {
            heroDao.deleteHeroByID(hero.getHeroID());
        }
    }
    
    /**
     * Test of addSuperPower and getSuperPowerByID methods, of class SuperPowerDaoJdbcImpl.
     */
    @Test
    public void testAddGetSuperPower() {
        System.out.println("addSuperPower");
        SuperPower sp = new SuperPower();
        sp.setDescription("power");
        superPowerDao.addSuperPower(sp);
        int superPowerID = sp.getSuperPowerID();
        SuperPower result = superPowerDao.getSuperPowerByID(superPowerID);
        assertEquals(sp, result);
    }
    
    /**
     * Test of getAllSuperPowers method, of class SuperPowerDaoJdbcImpl.
     */
    @Test
    public void testGetAllSuperPowers() {
        System.out.println("getAllSuperPowers");
        SuperPower sp1 = new SuperPower();
        sp1.setDescription("power 1");
        superPowerDao.addSuperPower(sp1);
        SuperPower sp2 = new SuperPower();
        sp2.setDescription("power 2");
        superPowerDao.addSuperPower(sp2);
        int expResult = 2;
        int result = superPowerDao.getAllSuperPowers().size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSuperPowersByHeroID method, of class SuperPowerDaoJdbcImpl and addHeroOrg method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testGetSuperPowersByHeroID() {
        System.out.println("getSuperPowersByHeroID");
        SuperPower sp1 = new SuperPower();
        sp1.setDescription("power 1");
        superPowerDao.addSuperPower(sp1);
        int spId1 = sp1.getSuperPowerID();
        SuperPower sp2 = new SuperPower();
        sp2.setDescription("power 2");
        superPowerDao.addSuperPower(sp2);
        int spId2 = sp2.getSuperPowerID();
        SuperPower sp3 = new SuperPower();
        sp3.setDescription("power 3");
        superPowerDao.addSuperPower(sp3);
        int spId3 = sp3.getSuperPowerID();
        Hero hero1 = new Hero();
        hero1.setHeroName("Hero 1");
        heroDao.addHero(hero1);
        int heroId1 = hero1.getHeroID();
        Hero hero2 = new Hero();
        hero2.setHeroName("Hero 2");
        heroDao.addHero(hero2);
        int heroId2 = hero2.getHeroID();
        heroDao.addHeroSuperPower(heroId1, spId1);
        heroDao.addHeroSuperPower(heroId2, spId2);
        heroDao.addHeroSuperPower(heroId2, spId3);
        List<SuperPower> superPowers = superPowerDao.getSuperPowersByHeroID(heroId2);
        assertTrue(superPowers.size() == 2);
        assertTrue(superPowers.stream().anyMatch(s -> s.getSuperPowerID() == spId2));
        assertTrue(superPowers.stream().anyMatch(s -> s.getSuperPowerID() == spId3));
    }
    
    /**
     * Test of updateSuperPower method, of class SuperPowerDaoJdbcImpl.
     */
    @Test
    public void testUpdateSuperPower() {
        System.out.println("updateSuperPower");
        SuperPower sp = new SuperPower();
        sp.setDescription("power 1");
        superPowerDao.addSuperPower(sp);
        int superPowerID = sp.getSuperPowerID();
        sp.setDescription("power 1");
        superPowerDao.updateSuperPower(sp);
        SuperPower result = superPowerDao.getSuperPowerByID(superPowerID);
        assertEquals(sp, result);
    }
    
    /**
     * Test of deleteSuperPower method, of class SuperPowerDaoJdbcImpl.
     */
    @Test
    public void testDeleteSuperPower() {
        System.out.println("deleteSuperPower");
        SuperPower sp = new SuperPower();
        sp.setDescription("power 1");
        superPowerDao.addSuperPower(sp);
        int superPowerID = sp.getSuperPowerID();
        SuperPower result = superPowerDao.getSuperPowerByID(superPowerID);
        assertEquals(sp, result);
        superPowerDao.deleteSuperPower(superPowerID);
        assertNull(superPowerDao.getSuperPowerByID(superPowerID));
    }
    
}

