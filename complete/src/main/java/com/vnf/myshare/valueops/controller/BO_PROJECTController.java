package com.vnf.myshare.valueops.controller;

import com.vnf.myshare.valueops.dao.BO_PROJECTRepository;
import com.vnf.myshare.valueops.model.BO_PROJECT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin
@RestController
public class BO_PROJECTController {
    @Autowired
    BO_PROJECTRepository bo_projectRepository;

    //search all project
    @GetMapping(value = "/getallproject")
    public List<BO_PROJECT> findProject() {
        return bo_projectRepository.findAll();
    }

    //add one project
    @PostMapping(value = "/newproject")
    public BO_PROJECT addProject(@RequestParam("projectname") String projectname, @RequestParam("priority") String priority, @RequestParam("enddate") String enddate, @RequestParam("headcount") String headcount, @RequestParam("description") String description) throws ParseException {
        BO_PROJECT project = new BO_PROJECT();
        project.setProjectname(projectname);
        project.setPriority(priority);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        project.setEnddate(new Date(simpleDateFormat.parse(enddate).getTime()));
        project.setHeadcount(headcount);
        project.setDescription(description);
        return bo_projectRepository.save(project);
    }
}
