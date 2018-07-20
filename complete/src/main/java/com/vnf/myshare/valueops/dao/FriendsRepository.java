package com.vnf.myshare.valueops.dao;

import com.vnf.myshare.valueops.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends,Long>{

    @Transactional
    @Query(value = "select * from firends  where myuid = ?1 union select sequence, bfuid as myuid, myuid as bfuid, bfdisplayname, bfemail, bfphotourl,bfdate from firends  where bfuid = ?1", nativeQuery = true)
    List<Friends> findByUid(String uid);

    Integer countByMyuidAndBfuid(String uid, String bfuid);

    @Transactional
    @Modifying
    @Query("update Friends u set u.bfphotourl = ?1 where u.bfuid =?2")
    Integer setBfphotourl(String bfphotourl, String bfuid);

}



