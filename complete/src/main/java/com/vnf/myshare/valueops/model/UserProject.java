package com.vnf.myshare.valueops.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserProject {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private Integer userid;

    @Column(nullable = false)
    private Integer projectid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

}
