/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.dao;

import com.fo.superherosighting.dto.SuperPower;
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
public class SuperPowerDaoJdbcImpl implements SuperPowerDao {
    
    private static final String SQL_INSERT_SUPER_POWER = "insert into SuperPower (Description) values (?)";
    private static final String SQL_SELECT_SUPER_POWER_BY_ID = "select * from SuperPower where SuperPowerID = ?";
    private static final String SQL_SELECT_ALL_SUPER_POWERS = "select * from SuperPower";
    private static final String SQL_SELECT_SUPER_POWERS_BY_HERO_ID = "select SuperPower.*  from SuperPower "
            + "inner join HeroSuperPower on HeroSuperPower.SuperPowerID = SuperPower.SuperPowerID "
            + "where HeroSuperPower.HeroID = ?";
    private static final String SQL_UPDATE_SUPER_POWER_BY_ID = "update SuperPower set Description = ? where SuperPowerID = ?";
    private static final String SQL_DELETE_SUPER_POWER_BY_ID = "delete from SuperPower where SuperPowerID = ?";
    
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperPower(SuperPower sp) {
        if (sp != null && sp.getDescription() != null && sp.getDescription().trim().length() > 0) {
        jdbcTemplate.update(SQL_INSERT_SUPER_POWER, sp.getDescription());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        sp.setSuperPowerID(id);
        }
    }
    @Override
    public SuperPower getSuperPowerByID(int superPowerID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPER_POWER_BY_ID, new SuperPowerMapper(), superPowerID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<SuperPower> getAllSuperPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPER_POWERS, new SuperPowerMapper());
    }
    @Override
    public List<SuperPower> getSuperPowersByHeroID(int heroID) {
        return jdbcTemplate.query(SQL_SELECT_SUPER_POWERS_BY_HERO_ID, new SuperPowerMapper(), heroID);
    }
    @Override
    public void updateSuperPower(SuperPower sp) {
        if (sp != null && sp.getSuperPowerID() > 0 && sp.getDescription() != null && sp.getDescription().trim().length() > 0) {
        jdbcTemplate.update(SQL_UPDATE_SUPER_POWER_BY_ID, sp.getDescription(), sp.getSuperPowerID());
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuperPower(int superPowerID) {
        deleteHeroSuperPowerBySuperPowerID(superPowerID);
        jdbcTemplate.update(SQL_DELETE_SUPER_POWER_BY_ID, superPowerID);
    }
    
    private static final class SuperPowerMapper implements RowMapper<SuperPower> {
        @Override
        public SuperPower mapRow(ResultSet rs, int i) throws SQLException {
            SuperPower sp = new SuperPower();
            sp.setSuperPowerID(rs.getInt("SuperPowerID"));
            sp.setDescription(rs.getString("Description"));
            return sp;
        }
    }
    
    //HeroSuperPower bridge table
    private static final String SQL_DELETE_HERO_SUPER_POWERS_BY_SUPER_POWER_ID = "delete from HeroSuperPower where SuperPowerID = ?";
    
    private void deleteHeroSuperPowerBySuperPowerID(int superPowerID) {
        jdbcTemplate.update(SQL_DELETE_HERO_SUPER_POWERS_BY_SUPER_POWER_ID, superPowerID);
    }
}
