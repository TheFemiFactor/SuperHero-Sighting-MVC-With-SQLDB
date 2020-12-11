/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.superherosighting.controller;

import com.fo.superherosighting.dao.OrgDao;
import com.fo.superherosighting.dto.Organization;
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
public class OrgController {
    OrgDao orgDao;
    
    @Inject
    public OrgController(OrgDao orgDao) {
        this.orgDao = orgDao;
    }
    public OrgController() {}
    
    @RequestMapping(value="organization", method=RequestMethod.GET)
    public String displayOrgs(Model model) {
        List<Organization> orgs = orgDao.getAllOrgs();
        model.addAttribute("orgs", orgs);
        return "/organization";
    }
    @RequestMapping(value="addOrg", method=RequestMethod.POST)
    public String addOrg(String orgName, String description, String streetAddress, String city, String state, String zipcode) {
        Organization org = new Organization(orgName, description, streetAddress, city, state, zipcode);
        orgDao.addOrg(org);
        return "redirect:/organization";
    }
    @RequestMapping(value="editOrg/{orgID}", method=RequestMethod.GET)
    public String editOrg(@PathVariable("orgID") int id, Model model) {
        Organization org = orgDao.getOrgByID(id);
        model.addAttribute("orgToEdit", org);
        return "/editOrg";
    }
    @RequestMapping(value="updateOrg", method=RequestMethod.POST)
    public String updateOrg(int orgID, String orgName, String description, String streetAddress, String city, String state, String zipcode) {
        Organization org = new Organization(orgName, description, streetAddress, city, state, zipcode);
        org.setOrgID(orgID);
        orgDao.updateOrg(org);
        return "redirect:/organization";
    }
    @RequestMapping(value="deleteOrg/{orgID}", method=RequestMethod.GET)
    public String deleteOrg(@PathVariable("orgID") int id) {
        orgDao.deleteOrg(id);
        return "redirect:/organization";
    }    
}
