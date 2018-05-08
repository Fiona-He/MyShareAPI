package com.vnf.myshare.valueops.dao;
import org.springframework.data.jpa.repository.*;
import com.vnf.myshare.valueops.model.BO_PROJECT;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface BO_PROJECTRepository extends JpaRepository<BO_PROJECT,Long>{

    @Modifying
    @Transactional
    @Query("update BO_PROJECT u set u.plandesc = ?1 where u.projectid = ?2")
    int setProjectDesc(String plandesc, Integer projectid);

    @Transactional
    @Query("select u from BO_PROJECT u where u.createby = ?1")
    List<BO_PROJECT> findByCreateby(String uid);

}



