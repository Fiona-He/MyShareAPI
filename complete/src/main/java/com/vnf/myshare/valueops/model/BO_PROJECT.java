package com.vnf.myshare.valueops.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class BO_PROJECT {
    @Id
    @GeneratedValue
    private Integer projectid;
    private String projectname;
    private String priority;
    private String headcount;
    private Date startdate;
    private Date enddate;
    private String description;
    private String plandesc;
    private String language;
    private String incharge1;
    private String incharge2;
    private String incharge3;
    private String createby;
    private String pm;
    private String status;
    private Date createdate;
    private String server;
    private String branch;
    private Integer projecttype;
    private String authdept;
    private String fileind;
    private String processind;
    private String seeonlyind;
    private String flowgraphic4;
    private String graphichight;
    private String specialind;
    private String backctrlscript;
    private String fordctrlscript;
    private String flowgraphic2;
    private String bgtype;
    private String printgraphic;
    private String insertctrlscript1;
    private String updateind;
    private String qryfovaind;
    private String flowgraphic;
    private String initctrlscript;
    private String insertctrlscript;
    private String alter1;
    private String alter2;
    private String alter3;
    private String alter4;
    private String alter5;
    private String front1;
    private String front2;
    private String front3;
    private String front4;
    private String shopind;
    private String shopreferid;
    private String folind;
    private String datecountrefer;
    private String datebaseline;
    private String commonind;
    private Integer relProjectid;
    private String relCondFieldid1;
    private String relCondFieldvalueId1;
    private String relCondFieldid2;
    private String relCondFieldvalueId2;
    private String relCondFieldid3;
    private String relCondFieldvalueId3;
    private String relCondFieldid4;
    private String relCondFieldvalueId4;
    private String relCondFieldid5;
    private String relCondFieldvalueId5;
    private String relResultFieldid1;
    private String specialchecking;

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getHeadcount() {
        return headcount;
    }

    public void setHeadcount(String headcount) {
        this.headcount = headcount;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlandesc() {
        return plandesc;
    }

    public void setPlandesc(String plandesc) {
        this.plandesc = plandesc;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIncharge1() {
        return incharge1;
    }

    public void setIncharge1(String incharge1) {
        this.incharge1 = incharge1;
    }

    public String getIncharge2() {
        return incharge2;
    }

    public void setIncharge2(String incharge2) {
        this.incharge2 = incharge2;
    }

    public String getIncharge3() {
        return incharge3;
    }

    public void setIncharge3(String incharge3) {
        this.incharge3 = incharge3;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(Integer projecttype) {
        this.projecttype = projecttype;
    }

    public String getAuthdept() {
        return authdept;
    }

    public void setAuthdept(String authdept) {
        this.authdept = authdept;
    }

    public String getFileind() {
        return fileind;
    }

    public void setFileind(String fileind) {
        this.fileind = fileind;
    }

    public String getProcessind() {
        return processind;
    }

    public void setProcessind(String processind) {
        this.processind = processind;
    }

    public String getSeeonlyind() {
        return seeonlyind;
    }

    public void setSeeonlyind(String seeonlyind) {
        this.seeonlyind = seeonlyind;
    }

    public String getFlowgraphic4() {
        return flowgraphic4;
    }

    public void setFlowgraphic4(String flowgraphic4) {
        this.flowgraphic4 = flowgraphic4;
    }

    public String getGraphichight() {
        return graphichight;
    }

    public void setGraphichight(String graphichight) {
        this.graphichight = graphichight;
    }

    public String getSpecialind() {
        return specialind;
    }

    public void setSpecialind(String specialind) {
        this.specialind = specialind;
    }

    public String getBackctrlscript() {
        return backctrlscript;
    }

    public void setBackctrlscript(String backctrlscript) {
        this.backctrlscript = backctrlscript;
    }

    public String getFordctrlscript() {
        return fordctrlscript;
    }

    public void setFordctrlscript(String fordctrlscript) {
        this.fordctrlscript = fordctrlscript;
    }

    public String getFlowgraphic2() {
        return flowgraphic2;
    }

    public void setFlowgraphic2(String flowgraphic2) {
        this.flowgraphic2 = flowgraphic2;
    }

    public String getBgtype() {
        return bgtype;
    }

    public void setBgtype(String bgtype) {
        this.bgtype = bgtype;
    }

    public String getPrintgraphic() {
        return printgraphic;
    }

    public void setPrintgraphic(String printgraphic) {
        this.printgraphic = printgraphic;
    }

    public String getInsertctrlscript1() {
        return insertctrlscript1;
    }

    public void setInsertctrlscript1(String insertctrlscript1) {
        this.insertctrlscript1 = insertctrlscript1;
    }

    public String getUpdateind() {
        return updateind;
    }

    public void setUpdateind(String updateind) {
        this.updateind = updateind;
    }

    public String getQryfovaind() {
        return qryfovaind;
    }

    public void setQryfovaind(String qryfovaind) {
        this.qryfovaind = qryfovaind;
    }

    public String getFlowgraphic() {
        return flowgraphic;
    }

    public void setFlowgraphic(String flowgraphic) {
        this.flowgraphic = flowgraphic;
    }

    public String getInitctrlscript() {
        return initctrlscript;
    }

    public void setInitctrlscript(String initctrlscript) {
        this.initctrlscript = initctrlscript;
    }

    public String getInsertctrlscript() {
        return insertctrlscript;
    }

    public void setInsertctrlscript(String insertctrlscript) {
        this.insertctrlscript = insertctrlscript;
    }

    public String getAlter1() {
        return alter1;
    }

    public void setAlter1(String alter1) {
        this.alter1 = alter1;
    }

    public String getAlter2() {
        return alter2;
    }

    public void setAlter2(String alter2) {
        this.alter2 = alter2;
    }

    public String getAlter3() {
        return alter3;
    }

    public void setAlter3(String alter3) {
        this.alter3 = alter3;
    }

    public String getAlter4() {
        return alter4;
    }

    public void setAlter4(String alter4) {
        this.alter4 = alter4;
    }

    public String getAlter5() {
        return alter5;
    }

    public void setAlter5(String alter5) {
        this.alter5 = alter5;
    }

    public String getFront1() {
        return front1;
    }

    public void setFront1(String front1) {
        this.front1 = front1;
    }

    public String getFront2() {
        return front2;
    }

    public void setFront2(String front2) {
        this.front2 = front2;
    }

    public String getFront3() {
        return front3;
    }

    public void setFront3(String front3) {
        this.front3 = front3;
    }

    public String getFront4() {
        return front4;
    }

    public void setFront4(String front4) {
        this.front4 = front4;
    }

    public String getShopind() {
        return shopind;
    }

    public void setShopind(String shopind) {
        this.shopind = shopind;
    }

    public String getShopreferid() {
        return shopreferid;
    }

    public void setShopreferid(String shopreferid) {
        this.shopreferid = shopreferid;
    }

    public String getFolind() {
        return folind;
    }

    public void setFolind(String folind) {
        this.folind = folind;
    }

    public String getDatecountrefer() {
        return datecountrefer;
    }

    public void setDatecountrefer(String datecountrefer) {
        this.datecountrefer = datecountrefer;
    }

    public String getDatebaseline() {
        return datebaseline;
    }

    public void setDatebaseline(String datebaseline) {
        this.datebaseline = datebaseline;
    }

    public String getCommonind() {
        return commonind;
    }

    public void setCommonind(String commonind) {
        this.commonind = commonind;
    }

    public Integer getRelProjectid() {
        return relProjectid;
    }

    public void setRelProjectid(Integer relProjectid) {
        this.relProjectid = relProjectid;
    }

    public String getRelCondFieldid1() {
        return relCondFieldid1;
    }

    public void setRelCondFieldid1(String relCondFieldid1) {
        this.relCondFieldid1 = relCondFieldid1;
    }

    public String getRelCondFieldvalueId1() {
        return relCondFieldvalueId1;
    }

    public void setRelCondFieldvalueId1(String relCondFieldvalueId1) {
        this.relCondFieldvalueId1 = relCondFieldvalueId1;
    }

    public String getRelCondFieldid2() {
        return relCondFieldid2;
    }

    public void setRelCondFieldid2(String relCondFieldid2) {
        this.relCondFieldid2 = relCondFieldid2;
    }

    public String getRelCondFieldvalueId2() {
        return relCondFieldvalueId2;
    }

    public void setRelCondFieldvalueId2(String relCondFieldvalueId2) {
        this.relCondFieldvalueId2 = relCondFieldvalueId2;
    }

    public String getRelCondFieldid3() {
        return relCondFieldid3;
    }

    public void setRelCondFieldid3(String relCondFieldid3) {
        this.relCondFieldid3 = relCondFieldid3;
    }

    public String getRelCondFieldvalueId3() {
        return relCondFieldvalueId3;
    }

    public void setRelCondFieldvalueId3(String relCondFieldvalueId3) {
        this.relCondFieldvalueId3 = relCondFieldvalueId3;
    }

    public String getRelCondFieldid4() {
        return relCondFieldid4;
    }

    public void setRelCondFieldid4(String relCondFieldid4) {
        this.relCondFieldid4 = relCondFieldid4;
    }

    public String getRelCondFieldvalueId4() {
        return relCondFieldvalueId4;
    }

    public void setRelCondFieldvalueId4(String relCondFieldvalueId4) {
        this.relCondFieldvalueId4 = relCondFieldvalueId4;
    }

    public String getRelCondFieldid5() {
        return relCondFieldid5;
    }

    public void setRelCondFieldid5(String relCondFieldid5) {
        this.relCondFieldid5 = relCondFieldid5;
    }

    public String getRelCondFieldvalueId5() {
        return relCondFieldvalueId5;
    }

    public void setRelCondFieldvalueId5(String relCondFieldvalueId5) {
        this.relCondFieldvalueId5 = relCondFieldvalueId5;
    }

    public String getRelResultFieldid1() {
        return relResultFieldid1;
    }

    public void setRelResultFieldid1(String relResultFieldid1) {
        this.relResultFieldid1 = relResultFieldid1;
    }

    public String getSpecialchecking() {
        return specialchecking;
    }

    public void setSpecialchecking(String specialchecking) {
        this.specialchecking = specialchecking;
    }


}
