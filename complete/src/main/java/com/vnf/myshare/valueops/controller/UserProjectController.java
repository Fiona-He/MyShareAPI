package com.vnf.myshare.valueops.controller;

import com.vnf.myshare.valueops.dao.UserProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserProjectController {

    @Autowired
    UserProjectRepository userProjectRepository;

    //search all user
    @GetMapping(value = "/projectusers")
    public int findUserProjects(@RequestParam("projectid") Integer projectid) {
        return userProjectRepository.countByProjectid(projectid);
    }


}
