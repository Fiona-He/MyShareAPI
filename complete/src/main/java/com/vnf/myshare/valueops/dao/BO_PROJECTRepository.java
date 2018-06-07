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

    @Modifying
    @Transactional
    @Query("update BO_PROJECT u set u.front1 = ?1 where u.projectid = ?2")
    int setFont1(String front1, Integer projectid);

    @Modifying
    @Transactional
    @Query("update BO_PROJECT u set u.front2 = ?1 where u.projectid = ?2")
    int setFont2(String front2, Integer projectid);

    @Modifying
    @Transactional
    @Query("update BO_PROJECT u set u.front3 = ?1 where u.projectid = ?2")
    int setFont3(String front3, Integer projectid);

    @Modifying
    @Transactional
    @Query("update BO_PROJECT u set u.front4 = ?1 where u.projectid = ?2")
    int setFont4(String front4, Integer projectid);

    @Transactional
    @Query("select u from BO_PROJECT u where u.createby = ?1")
    List<BO_PROJECT> findByCreateby(String uid);

    @Transactional
    @Query("select u from BO_PROJECT u where u.projectid = ?1")
    List<BO_PROJECT> findProjectById(Integer projectid);

    @Transactional
    @Query(value = "select * from BO_PROJECT u where u.createby = ?1 union select * from BO_PROJECT t where t.projectid in (select field1 from bo_filedsvalue1 where field2 = ?1)", nativeQuery = true)
    List<BO_PROJECT> findProjects(String uid);

}



