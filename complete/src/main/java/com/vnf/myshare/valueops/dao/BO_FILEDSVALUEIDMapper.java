package com.vnf.myshare.valueops.dao;

import com.vnf.myshare.valueops.model.BO_FILEDSVALUEID;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BO_FILEDSVALUEIDMapper {
    int deleteByPrimaryKey(@Param("projectid") Integer projectid, @Param("sequence") Integer sequence);

    int insert(BO_FILEDSVALUEID record);

    List<BO_FILEDSVALUEID> selectAll(@Param("projectid") Integer projectid);

    List<BO_FILEDSVALUEID> selectByFieldValue(@Param("projectid") Integer projectid, @Param("fieldname") String fieldname, @Param("fieldvalue") String fieldvalue);

    int updateByPrimaryKey(BO_FILEDSVALUEID record);

    int selectCount(@Param("projectid") Integer projectid, @Param("status") String status);

    String getStatus(@Param("projectid") Integer projectid, @Param("username") String username);
}