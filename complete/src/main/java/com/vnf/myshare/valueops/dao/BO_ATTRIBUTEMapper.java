package com.vnf.myshare.valueops.dao;

import com.vnf.myshare.valueops.model.BO_ATTRIBUTE;
import java.util.List;

public interface BO_ATTRIBUTEMapper {
    int insert(BO_ATTRIBUTE record);

    List<BO_ATTRIBUTE> selectAll();
}