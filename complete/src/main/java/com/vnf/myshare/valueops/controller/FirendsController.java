package com.vnf.myshare.valueops.controller;

import com.vnf.myshare.valueops.model.Friends;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vnf.myshare.valueops.dao.FriendsRepository;
import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
public class FirendsController {

    Date currentTime = new java.sql.Date(new java.util.Date().getTime());

    @Autowired
    FriendsRepository friendsRepository;

    //search all user
    @GetMapping(value = "/getallfriends/{uid}")
    public List<Friends> findFriends(@PathVariable String uid) {
        return friendsRepository.findByUid(uid);
    }

    //add one user
    @PostMapping(value = "/addfriend")
    public Friends addFriend(@RequestBody Friends record){
        Friends friend = new Friends();
        friend.setMyuid(record.getMyuid());
        friend.setBfuid(record.getBfuid());
        friend.setBfdisplayname(record.getBfdisplayname());
        friend.setBfemail(record.getBfemail());
        friend.setBfphotourl(record.getBfphotourl());
        friend.setBfdate(currentTime);
        return friendsRepository.save(friend);
    }

    //search all user
    @GetMapping(value = "/checkfriend/{myuid}/{bfuid}")
    public Integer findUsers(@PathVariable String myuid, @PathVariable String bfuid) {
        if(friendsRepository.countByMyuidAndBfuid(myuid, bfuid)>0)
            return friendsRepository.countByMyuidAndBfuid(myuid, bfuid);
        else
            return -1;
    }

    @PutMapping(value = "/updatefriendphotourl")
    public Integer setBfphotourl(@RequestBody Friends myfriend){
        friendsRepository.setBfphotourl(myfriend.getBfphotourl(),myfriend.getBfuid());
        return 0;
    }

}
