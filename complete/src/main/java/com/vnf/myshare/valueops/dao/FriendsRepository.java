package com.vnf.myshare.valueops.dao;

import com.vnf.myshare.valueops.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends,Long>{

    @Modifying
    @Transactional

    @Query("select u from Friends u where u.myuid = ?1")
    List<Friends> findByUid(String uid);

}



