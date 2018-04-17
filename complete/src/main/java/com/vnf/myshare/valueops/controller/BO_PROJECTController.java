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
    public BO_PROJECT addProject(@RequestBody BO_PROJECT record) throws ParseException {
        return bo_projectRepository.save(record);
    }

    //RequestBody这个注解可以接收json数据
    @RequestMapping(method = RequestMethod.PUT,value = "/updateproject")
    public BO_PROJECT updateProject(@RequestBody BO_PROJECT record){
        return bo_projectRepository.save(record);
    }
}
