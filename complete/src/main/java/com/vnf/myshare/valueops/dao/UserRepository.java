package com.vnf.myshare.valueops.dao;

import com.vnf.myshare.valueops.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {


}
