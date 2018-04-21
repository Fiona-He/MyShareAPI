package com.vnf.myshare.valueops.service;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vnf.myshare.valueops.model.BO_FILEDSVALUE;
import com.vnf.myshare.valueops.model.BO_PROJECT;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ShareListVeryDetail {

    public BO_PROJECT project;
    public BO_FILEDSVALUE[] fieldsvalue;


    public String toString() {
        return "ShareList{" +
                "projectid='" + project.getProjectid() + '\'' +
                ", projectname=" + project.getProjectname() +
                '}';
    }
}
