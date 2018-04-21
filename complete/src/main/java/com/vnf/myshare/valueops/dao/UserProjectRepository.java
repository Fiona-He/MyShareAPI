package com.vnf.myshare.valueops.dao;

import com.vnf.myshare.valueops.model.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectRepository extends JpaRepository<UserProject,Long> {

    Integer countByProjectid(Integer Projectid);
}
