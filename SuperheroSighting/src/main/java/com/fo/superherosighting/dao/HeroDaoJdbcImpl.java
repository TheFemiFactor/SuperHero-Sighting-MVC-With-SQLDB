/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.dao;

import com.fo.superherosighting.dto.Hero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TheFemiFactor
 */
public class HeroDaoJdbcImpl implements HeroDao {
    
    private static final String SQL_INSERT_HERO = "insert into Hero (HeroName,Description) values (?,?)";
    private static final String SQL_SELECT_HERO_BY_ID = "select * from Hero where HeroID = ?";
    private static final String SQL_SELECT_ALL_HEROS = "select * from Hero order by Hero.HeroName";
    private static final String SQL_SELECT_HEROS_BY_LOCATION_ID =
            "select Hero.* from Hero" +
            " inner join Sighting on Hero.HeroID = Sighting.HeroID" +
            " inner join Location on Location.LocationID = Sighting.LocationID" +
            " where Location.LocationID = ? order by Hero.HeroName";
    private static final String SQL_SELECT_HEROS_BY_ORGANIZATION_ID =
            "select Hero.* from Hero" +
            " inner join HeroOrganization on HeroOrganization.HeroID = Hero.HeroID" +
            " inner join Organization on Organization.OrganizationID = HeroOrganization.OrganizationID" +
            " where Organization.OrganizationID = ? order by Hero.HeroName";
    private static final String SQL_UPDATE_HERO_BY_ID = "update Hero set HeroName = ?, Description = ? where HeroID = ?";
    private static final String SQL_DELETE_HERO_BY_ID = "delete from Hero where HeroID = ?";
    
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHero(Hero hero) {
        if (hero != null && hero.getHeroName() != null && hero.getHeroName().trim().length() > 0) {
            jdbcTemplate.update(SQL_INSERT_HERO, hero.getHeroName(), hero.getDescription());
            int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
            hero.setHeroID(id);
        }
    }
    @Override
    public Hero getHeroByID(int heroID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HERO_BY_ID, new HeroMapper(), heroID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Hero> getAllHeros() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HEROS, new HeroMapper());
    }
    @Override
    public List<Hero> getHerosByLocationID(int locationID) {
        return jdbcTemplate.query(SQL_SELECT_HEROS_BY_LOCATION_ID, new HeroMapper(), locationID);
    }
    @Override
    public List<Hero> getHerosByOrganizationID(int organizationID) {
        return jdbcTemplate.query(SQL_SELECT_HEROS_BY_ORGANIZATION_ID, new HeroMapper(), organizationID);
    }
    @Override
    public void updateHero(Hero hero) {
        if (hero != null && hero.getHeroName() != null && hero.getHeroID() > 0 && hero.getHeroName().trim().length() > 0) {
            jdbcTemplate.update(SQL_UPDATE_HERO_BY_ID, hero.getHeroName(), hero.getDescription(), hero.getHeroID());
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteHeroByID(int heroID) {
        deleteHeroOrgsByHeroID(heroID);
        deleteSightingsByHeroID(heroID);
        deleteHeroSuperPowersByHeroID(heroID);
        jdbcTemplate.update(SQL_DELETE_HERO_BY_ID, heroID);
    }
    
    private static final class HeroMapper implements RowMapper<Hero> {
        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroID(rs.getInt("HeroID"));
            hero.setHeroName(rs.getString("HeroName"));
            hero.setDescription(rs.getString("Description"));
            return hero;
        }
    }
    
    //HeroSuperPower bridge table
    private static final String SQL_INSERT_HERO_SUPER_POWER = "insert into HeroSuperPower (HeroID, SuperPowerID) values (?,?)";
    private static final String SQL_DELETE_HERO_SUPER_POWER = "delete from HeroSuperPower where HeroID = ? and SuperPowerID = ?";
    private static final String SQL_DELETE_HERO_SUPER_POWERS_BY_HERO_ID = "delete from HeroSuperPower where HeroID = ?";
    
    @Override
    public void addHeroSuperPower(int heroID, int superPowerID) {
        jdbcTemplate.update(SQL_INSERT_HERO_SUPER_POWER, heroID, superPowerID);
    }
    @Override
    public void deleteHeroSuperPower(int heroID, int superPowerID) {
        jdbcTemplate.update(SQL_DELETE_HERO_SUPER_POWER, heroID, superPowerID);
    }
    private void deleteHeroSuperPowersByHeroID(int heroID) {
        jdbcTemplate.update(SQL_DELETE_HERO_SUPER_POWERS_BY_HERO_ID, heroID);
    }
    
    //HeroOrganization bridge table
    private static final String SQL_INSERT_HERO_ORG = "insert into HeroOrganization (HeroID,OrganizationID) values (?,?)";
    private static final String SQL_DELETE_HERO_ORG = "delete from HeroOrganization where HeroID = ? and OrganizationID = ?";
    private static final String SQL_DELETE_HERO_ORG_BY_HERO_ID = "delete from HeroOrganization where HeroID = ?";
    @Override
    public void addHeroOrg(int heroID, int orgID) {
        jdbcTemplate.update(SQL_INSERT_HERO_ORG, heroID, orgID);
    }
    @Override
    public void deleteHeroOrg(int heroID, int orgID) {
        jdbcTemplate.update(SQL_DELETE_HERO_ORG, heroID, orgID);
    }
    private void deleteHeroOrgsByHeroID(int heroID) {
        jdbcTemplate.update(SQL_DELETE_HERO_ORG_BY_HERO_ID, heroID);
    }
    
    //Sighting table
    private static final String SQL_DELETE_SIGHTINGS_BY_HERO_ID = "delete from Sighting where HeroID = ?";
    private void deleteSightingsByHeroID(int heroID) {
        jdbcTemplate.update(SQL_DELETE_SIGHTINGS_BY_HERO_ID, heroID);
    }
}
