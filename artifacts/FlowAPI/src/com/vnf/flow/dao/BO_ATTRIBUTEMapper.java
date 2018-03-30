package com.vnf.flow.dao;

import com.vnf.flow.model.BO_ATTRIBUTE;
import java.util.List;

public interface BO_ATTRIBUTEMapper {
    int insert(BO_ATTRIBUTE record);

    List<BO_ATTRIBUTE> selectAll();
}