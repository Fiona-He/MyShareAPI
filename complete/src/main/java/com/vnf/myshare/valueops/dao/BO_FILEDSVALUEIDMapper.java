package com.vnf.myshare.valueops.dao;

import com.vnf.myshare.valueops.model.BO_FILEDSVALUEID;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BO_FILEDSVALUEIDMapper {
    int deleteByPrimaryKey(@Param("projectid") Integer projectid, @Param("sequence") Integer sequence);

    int deleteByField(BO_FILEDSVALUEID record);

    int insert(BO_FILEDSVALUEID record);

    List<BO_FILEDSVALUEID> selectAll(@Param("projectid") Integer projectid);

    List<BO_FILEDSVALUEID> selectByFieldValue(@Param("projectid") Integer projectid, @Param("fieldname") String fieldname, @Param("fieldvalue") String fieldvalue);

    int updateByPrimaryKey(BO_FILEDSVALUEID record);

    int updateByField(@Param("projectid") Integer projectid,@Param("fieldid1") Integer fieldid1,@Param("fieldvalue1") String fieldvalue1,@Param("fieldid2") Integer fieldid2,@Param("fieldvalue2") String fieldvalue2);

    int selectCount(@Param("projectid") Integer projectid, @Param("status") String status);

    int selectCountByField(BO_FILEDSVALUEID record);

    String getStatus(@Param("projectid") Integer projectid, @Param("username") String username);
}