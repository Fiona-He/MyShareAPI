package com.vnf.myshare.valueops.service;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vnf.myshare.valueops.model.BO_PROJECT;
import com.vnf.myshare.valueops.service.RaiseHandStatus;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ShareDetail {

    public BO_PROJECT Project;
    public int RaiseHandCount1;
    public int RaiseHandCount2;
    public int RaiseHandCount3;
    public int JoinUsers;
    public String UserStatus;
    public List<RaiseHandStatus> RaiseHandStatus;
    public String DateTime;


}