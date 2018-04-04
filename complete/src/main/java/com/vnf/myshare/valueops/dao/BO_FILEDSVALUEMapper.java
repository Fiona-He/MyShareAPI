package com.vnf.myshare.valueops.dao;

import com.vnf.myshare.valueops.model.BO_FILEDSVALUE;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BO_FILEDSVALUEMapper {
    int deleteByPrimaryKey( @Param("projectid") Integer projectid, @Param("sequence") Integer sequence);

    int insert(BO_FILEDSVALUE record);

    List<BO_FILEDSVALUE> selectAll( @Param("projectid") Integer projectid );

    int updateByPrimaryKey(BO_FILEDSVALUE record);
}