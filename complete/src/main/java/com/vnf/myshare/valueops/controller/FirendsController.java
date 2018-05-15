package com.vnf.myshare.valueops.controller;

import com.vnf.myshare.valueops.model.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vnf.myshare.valueops.dao.FriendsRepository;

import java.util.List;

@CrossOrigin
@RestController
public class FirendsController {

    @Autowired
    FriendsRepository firendsRepository;

    //search all user
    @GetMapping(value = "/getallfriends/{uid}")
    public List<Friends> findFriends(@PathVariable String uid) {
        return firendsRepository.findByUid(uid);
    }

}
