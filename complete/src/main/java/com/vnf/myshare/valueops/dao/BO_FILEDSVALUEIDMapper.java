package com.vnf.myshare.valueops.dao;

import com.vnf.myshare.valueops.model.BO_FILEDSVALUEID;
import com.vnf.myshare.valueops.service.RaiseHandStatus;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface BO_FILEDSVALUEIDMapper {
    int deleteByPrimaryKey(@Param("projectid") Integer projectid, @Param("sequence") Integer sequence);

    void deleteByField(BO_FILEDSVALUEID record);

    int insert(BO_FILEDSVALUEID record);

    List<BO_FILEDSVALUEID> selectAll(@Param("projectid") Integer projectid);

    List<BO_FILEDSVALUEID> selectByFieldValue(@Param("projectid") Integer projectid, @Param("fieldname") String fieldname, @Param("fieldvalue") String fieldvalue);

    List<BO_FILEDSVALUEID> selectByMultiFieldValue(Integer projectid, String fieldname1, String fieldvalue1, String fieldname2, String fieldvalue2, String fieldname3, String fieldvalue3);

    int updateByPrimaryKey(BO_FILEDSVALUEID record);

    int updateByField(@Param("projectid") Integer projectid,@Param("fieldid1") Integer fieldid1,@Param("fieldvalue1") String fieldvalue1,@Param("fieldid2") Integer fieldid2,@Param("fieldvalue2") String fieldvalue2);

    int selectCount(@Param("projectid") Integer projectid, @Param("shareid") Integer shareid, @Param("status") String status);

    int selectCountByField(BO_FILEDSVALUEID record);

    List<BO_FILEDSVALUEID> selectByField(BO_FILEDSVALUEID record);

    int updateStatusByField(BO_FILEDSVALUEID record);

    String getStatus(@Param("projectid") Integer projectid, @Param("shareid") Integer shareid, @Param("username") String username);

    List<RaiseHandStatus> getRaiseHandStatus(@Param("projectid") Integer projectid, @Param("shareid") Integer shareid);

    List<String> getSubOrderKing(@Param("projectid") Integer projectid, @Param("shareid") Integer shareid);

    String getDatetime(@Param("projectid") Integer projectid, @Param("shareid") Integer shareid, @Param("username") String username);
}