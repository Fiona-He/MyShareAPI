package com.vnf.myshare.valueops.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "firends", schema = "myshare", catalog = "")
public class Friends {

    @Id
    @GeneratedValue
    private int sequence;
    private String myuid;
    private String bfuid;
    private String bfdisplayname;
    private String bfemail;
    private String bfphotourl;
    private Date bfdate;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getMyuid() {
        return myuid;
    }

    public void setMyuid(String myuid) {
        this.myuid = myuid;
    }

    public String getBfuid() {
        return bfuid;
    }

    public void setBfuid(String bfuid) {
        this.bfuid = bfuid;
    }

    public String getBfdisplayname() {
        return bfdisplayname;
    }

    public void setBfdisplayname(String bfdisplayname) {
        this.bfdisplayname = bfdisplayname;
    }

    public String getBfemail() {
        return bfemail;
    }

    public void setBfemail(String bfemail) {
        this.bfemail = bfemail;
    }

    public String getBfphotourl() {
        return bfphotourl;
    }

    public void setBfphotourl(String bfphotourl) {
        this.bfphotourl = bfphotourl;
    }

    public Date getBfdate() {
        return bfdate;
    }

    public void setBfdate(Date bfdate) {
        this.bfdate = bfdate;
    }



}
