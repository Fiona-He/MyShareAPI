package com.vnf.myshare.valueops.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.vnf.myshare.valueops.model.BO_FILEDSVALUEID;

public class SubOrder {
    @Id
    @GeneratedValue
    public BO_FILEDSVALUEID order;

    private BO_FILEDSVALUEID[] list;

    public BO_FILEDSVALUEID[] getList() {
        return list;
    }

    public void setList(BO_FILEDSVALUEID[] list) {
        this.list = list;
    }

    public BO_FILEDSVALUEID getOrder() {
        return order;
    }

    public void setOrder(BO_FILEDSVALUEID order) {
        this.order = order;
    }

}
