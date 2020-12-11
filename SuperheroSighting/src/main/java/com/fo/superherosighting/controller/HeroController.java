/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.controller;

import com.fo.superherosighting.dao.HeroDao;
import com.fo.superherosighting.dao.SightingDao;
import com.fo.superherosighting.dao.SuperPowerDao;
import com.fo.superherosighting.dto.Hero;
import com.fo.superherosighting.dto.Sighting;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author TheFemiFactor
 */
@Controller
public class HeroController {
    HeroDao heroDao;
    SightingDao sightingDao;
    SuperPowerDao superPowerDao;
    
    @Inject
    public HeroController(HeroDao heroDao, SightingDao sightingDao, SuperPowerDao superPowerDao) {
        this.heroDao = heroDao;
        this.sightingDao = sightingDao;
        this.superPowerDao = superPowerDao;
    }
    public HeroController() {
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightingsToLimit();
        model.addAttribute("sightings", sightings);
        return "/index";
    }
    @RequestMapping(value="hero", method=RequestMethod.GET)
    public String displayHeros(Model model) {
        List<Hero> heros = heroDao.getAllHeros();
        model.addAttribute("heros", heros);
        return "/hero";
    }
    @RequestMapping(value="addHero", method=RequestMethod.POST)
    public String addHero(String heroName, String heroDescription) {
        Hero hero = new Hero(heroName, heroDescription);
        heroDao.addHero(hero);
        return "redirect:/hero";
    }
    @RequestMapping(value="/editHero/{heroID}", method=RequestMethod.GET)
    public String editHero(@PathVariable("heroID") int id, Model model) {
        Hero hero = heroDao.getHeroByID(id);
        model.addAttribute("hero", hero);
        return "/editHero";
    }
    @RequestMapping(value="updateHero", method=RequestMethod.POST)
    public String updateHero(int heroID, String heroName, String heroDescription) {
        Hero hero = new Hero(heroName, heroDescription);
        hero.setHeroID(heroID);
        heroDao.updateHero(hero);
        return "redirect:/hero";
    }
    @RequestMapping(value="/deleteHero/{heroID}", method=RequestMethod.GET)
    public String deleteHero(@PathVariable("heroID") int id) {
        heroDao.deleteHeroByID(id);
        return "redirect:/hero";
    }
}
