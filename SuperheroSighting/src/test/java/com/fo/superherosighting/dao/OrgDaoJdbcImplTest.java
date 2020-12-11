/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.dao;

import com.fo.superherosighting.dto.Hero;
import com.fo.superherosighting.dto.Organization;
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
public class OrgDaoJdbcImplTest {
    
    OrgDao orgDao;
    HeroDao heroDao;    
    
    public OrgDaoJdbcImplTest() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        orgDao = ctx.getBean("orgDao", OrgDao.class);
        heroDao = ctx.getBean("heroDao", HeroDao.class);
        List<Organization> orgs = orgDao.getAllOrgs();
        for (Organization org : orgs) {
            orgDao.deleteOrg(org.getOrgID());
        }
        List<Hero> heros = heroDao.getAllHeros();
        for (Hero hero : heros) {
            heroDao.deleteHeroByID(hero.getHeroID());
        }
    }
    
    @After
    public void tearDown() {
        List<Organization> orgs = orgDao.getAllOrgs();
        for (Organization org : orgs) {
            orgDao.deleteOrg(org.getOrgID());
        }
        List<Hero> heros = heroDao.getAllHeros();
        for (Hero hero : heros) {
            heroDao.deleteHeroByID(hero.getHeroID());
        }
    }
    
    /**
     * Test of addOrg and getOrgByID methods, of class OrgDaoJdbcImpl.
     */
    @Test
    public void testAddGetOrg() {
        System.out.println("addOrg");
        Organization org = new Organization();
        org.setOrgName("Org 1");
        org.setDescription("Description");
        org.setStreetAddress("Org 1");
        org.setCity("Org 1");
        org.setState("O1");
        org.setZipcode("11212");
        orgDao.addOrg(org);
        int orgID = org.getOrgID();
        Organization result = orgDao.getOrgByID(orgID);
        assertEquals(org, result);
    }
    
    /**
     * Test of getAllOrgs method, of class OrgDaoJdbcImpl.
     */
    @Test
    public void testGetAllOrgs() {
        System.out.println("getAllOrgs");
        Organization org1 = new Organization();
        org1.setOrgName("Org 1");
        orgDao.addOrg(org1);
        Organization org2 = new Organization();
        org2.setOrgName("Org 2");
        orgDao.addOrg(org2);
        int expResult = 2;
        int result = orgDao.getAllOrgs().size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getOrgsByHeroID method, of class OrgDaoJdbcImpl.
     */
    @Test
    public void testGetOrgsByHeroID() {
        System.out.println("getOrgsByHeroID");
        System.out.println("getHerosByOrganizationID, addHeroOrg");
        Hero hero1 = new Hero();
        hero1.setHeroName("Hero 1");
        heroDao.addHero(hero1);
        int heroId1 = hero1.getHeroID();
        Hero hero2 = new Hero();
        hero2.setHeroName("Hero 2");
        heroDao.addHero(hero2);
        int heroId2 = hero2.getHeroID();
        Organization org1 = new Organization();
        org1.setOrgName("Org 1");
        orgDao.addOrg(org1);
        int orgId1 = org1.getOrgID();
        Organization org2 = new Organization();
        org2.setOrgName("Org 2");
        orgDao.addOrg(org2);
        int orgId2 = org2.getOrgID();
        Organization org3 = new Organization();
        org3.setOrgName("Org 3");
        orgDao.addOrg(org3);
        int orgId3 = org3.getOrgID();
        heroDao.addHeroOrg(heroId1, orgId1);
        heroDao.addHeroOrg(heroId2, orgId2);
        heroDao.addHeroOrg(heroId2, orgId3);
        List<Organization> orgs = orgDao.getOrgsByHeroID(heroId2);
        assertTrue(orgs.size() == 2);
        assertTrue(orgs.stream().anyMatch(o -> o.getOrgID() == orgId2));
        assertTrue(orgs.stream().anyMatch(o -> o.getOrgID() == orgId3));
    }
    
    /**
     * Test of updateOrg method, of class OrgDaoJdbcImpl.
     */
    @Test
    public void testUpdateOrg() {
        System.out.println("updateOrg");
        Organization org = new Organization();
        org.setOrgName("Org 1");
        org.setDescription("Description");
        org.setStreetAddress("Org 1");
        org.setCity("Org 1");
        org.setState("O1");
        org.setZipcode("11212");
        orgDao.addOrg(org);
        int orgID = org.getOrgID();
        org.setOrgName("Org 2");
        org.setDescription("Description");
        org.setStreetAddress("Org 2");
        org.setCity("Org 2");
        org.setState("O2");
        org.setZipcode("22321");
        orgDao.updateOrg(org);
        Organization result = orgDao.getOrgByID(orgID);
        assertEquals(org, result);
    }
    
    /**
     * Test of deleteOrg method, of class OrgDaoJdbcImpl.
     */
    @Test
    public void testDeleteOrg() {
        System.out.println("deleteOrg");
        Organization org = new Organization();
        org.setOrgName("Org 1");
        orgDao.addOrg(org);
        int orgID = org.getOrgID();
        Organization result = orgDao.getOrgByID(orgID);
        assertEquals(org, result);
        orgDao.deleteOrg(orgID);
        assertNull(orgDao.getOrgByID(orgID));
    }
    
}
