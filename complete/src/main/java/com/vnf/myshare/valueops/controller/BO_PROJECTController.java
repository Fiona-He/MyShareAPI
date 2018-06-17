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
    public BO_PROJECT findProjectById(@PathVariable int projectid) {
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

    @RequestMapping(method = RequestMethod.PUT,value = "/updateproject/{projectid}/{projectdesc}")
    public int updateProjectDesc(@PathVariable String projectdesc,@PathVariable int projectid){
        return bo_projectRepository.setProjectDesc(projectdesc,projectid);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/projectname/{projectid}/{projectname}")
    public int updateProjectName(@PathVariable String projectname,@PathVariable int projectid){
        return bo_projectRepository.setProjectName(projectname,projectid);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/updateprojectfront1")
    public int updateProjectFront1(@RequestBody BO_PROJECT project){
        return bo_projectRepository.setFont1(project.getFront1(),project.getProjectid());
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/updateprojectfront2")
    public int updateProjectFront2(@RequestBody BO_PROJECT project){
        return bo_projectRepository.setFont2(project.getFront2(),project.getProjectid());
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/updateprojectfront3")
    public int updateProjectFront3(@RequestBody BO_PROJECT project){
        return bo_projectRepository.setFont3(project.getFront3(),project.getProjectid());
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/updateprojectfront4")
    public int updateProjectFront4(@RequestBody BO_PROJECT project){
        return bo_projectRepository.setFont4(project.getFront4(),project.getProjectid());
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/updateprojectspecialind")
    public int updateProjectSpecialInd(@RequestBody BO_PROJECT project){
        return bo_projectRepository.setSpecialInd(project.getSpecialind(),project.getProjectid());
    }
}
