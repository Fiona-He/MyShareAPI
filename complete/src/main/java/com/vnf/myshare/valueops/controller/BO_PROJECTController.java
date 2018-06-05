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
    @GetMapping(value = "/getallproject/{uid}")
    public List<BO_PROJECT> findProject(@PathVariable String uid) {
        return bo_projectRepository.findByCreateby(uid);
    }

    @GetMapping(value = "/findByProjectid/{projectid}")
    public List<BO_PROJECT> findProjectById(@PathVariable int projectid) {
        return bo_projectRepository.findProjectById(projectid);
    }

    @GetMapping(value = "/findProjects/{uid}")
    public List<BO_PROJECT> findProjects(@PathVariable String uid) {
        return bo_projectRepository.findProjects(uid);
    }

    //add one project
    @PostMapping(value = "/newproject")
    public BO_PROJECT addProject(@RequestBody BO_PROJECT record) throws ParseException {
        return bo_projectRepository.save(record);
    }

    //RequestBody这个注解可以接收json数据
    @RequestMapping(method = RequestMethod.PUT,value = "/updateproject/{projectid}/{projectdesc}")
    public int updateProjectDesc(@PathVariable String projectdesc,@PathVariable int projectid){
        return bo_projectRepository.setProjectDesc(projectdesc,projectid);
    }
}
