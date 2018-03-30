package com.vnf.flow.dao;

import com.vnf.flow.model.BO_FILEDSVALUE;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BO_FILEDSVALUEMapper {
    int deleteByPrimaryKey(Integer projectid,Integer sequence);

    int insert(BO_FILEDSVALUE record);

    List<BO_FILEDSVALUE> selectAll( @Param("projectid") Integer projectid );

    int updateByPrimaryKey(BO_FILEDSVALUE record);
}