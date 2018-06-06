package com.vnf.myshare.valueops.controller;

import com.vnf.myshare.valueops.dao.BO_FILEDSVALUEIDMapper;
import com.vnf.myshare.valueops.model.BO_FILEDSVALUEID;
import com.vnf.myshare.valueops.singleton.SingletonMybatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import com.vnf.myshare.valueops.model.VALUEBYFIELD;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
/*@RequestMapping("/fieldsvalue") //在类上使用RequestMapping，里面设置的value就是方法的父路径*/
public class FILEDSVALUEIDController {

    private static SqlSessionFactory sqlSessionFactory;
    Date currentTime = new java.util.Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        sqlSessionFactory = SingletonMybatis.getSqlSessionFactory();
    }

    //这里体现了restful风格的请求，按照请求的类型，来进行增删查改。
    //设计restful api（其实也就是URL），不要有冗余，例如不要写成getUsers，URL中最好不要有动词。
    // 这里用的是路径变量，就是{}括起来的，会当做变量读进来
    //獲取所有的舉手數據
    @RequestMapping(method = RequestMethod.GET, value = "/fieldvalueid/{shareid}")
    public List<BO_FILEDSVALUEID> selectAll(@PathVariable String shareid) {
        List<BO_FILEDSVALUEID> BO_FILEDSVALUEIDs;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            BO_FILEDSVALUEIDs = userOperation.selectByFieldValue(1,"field1",shareid);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

        return BO_FILEDSVALUEIDs;

    }

    //獲取所有的類型的數據
    @RequestMapping(method = RequestMethod.GET, value = "/fieldvalueall/{projectid}/{field}/{value}")
    public List<BO_FILEDSVALUEID> selectAllData(
            @PathVariable Integer projectid,
            @PathVariable String field,
            @PathVariable String value) {
        List<BO_FILEDSVALUEID> BO_FILEDSVALUEIDs;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            BO_FILEDSVALUEIDs = userOperation.selectByFieldValue(projectid,field,value);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

        return BO_FILEDSVALUEIDs;

    }

    //新增活動人數據
//    @RequestMapping(method = RequestMethod.POST,value = "/fieldvalueid/{shareid}/{createby}/{grouppeople}/{status}")
//    public boolean insert(@PathVariable String shareid,@PathVariable String createby,@PathVariable String grouppeople,@PathVariable String status){
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        try {
//            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
//            BO_FILEDSVALUEID record = new BO_FILEDSVALUEID();
//
//            JSONObject peoplejson = new JSONObject(grouppeople);
//
//            System.out.println(peoplejson);
//
//            for(int i=0; i< peoplejson.getJSONArray("grouppeople").length(); i++) {
//                record.setProjectid(1);
//                record.setField1(shareid);
//                record.setField2(peoplejson.getJSONArray("grouppeople").getJSONObject(i).get("uid").toString());
//                record.setField3(peoplejson.getJSONArray("grouppeople").getJSONObject(i).get("photourl").toString());
//                System.out.println(peoplejson.getJSONArray("grouppeople").get(i).toString());
//                record.setField4(createby);
//                record.setStatus(status);
//                record.setDatetime(sdf.format(currentTime));
//                userOperation.insert(record);
//                sqlSession.commit();
//            }
//
//        }finally {
//            sqlSession.close();
//        }
//        return true;
//    }

    @RequestMapping(method = RequestMethod.POST,value = "/fieldvalueid/{shareid}/{createby}/{status}")
    public boolean insert(@PathVariable String shareid,@PathVariable String createby,@RequestBody Object[] grouppeople,@PathVariable String status){

//        System.out.println(grouppeople[0].toString());
//        JSONArray peoplejsonarray = new JSONArray(grouppeople);
//        System.out.println(peoplejsonarray.getJSONObject(0).get("uid"));
//        System.out.println(peoplejsonarray.getJSONObject(0).get("phtoturl"));

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            BO_FILEDSVALUEID record = new BO_FILEDSVALUEID();

            JSONArray peoplejsonarray = new JSONArray(grouppeople);

            System.out.println(peoplejsonarray.toString());

            for(int i=0; i< peoplejsonarray.length(); i++) {
                record.setProjectid(1);
                record.setField1(shareid);
                record.setField2(peoplejsonarray.getJSONObject(i).get("uid").toString());
                record.setField3(peoplejsonarray.getJSONObject(i).get("photourl").toString());

                System.out.println(peoplejsonarray.get(i).toString());
                record.setField4(createby);
                record.setField5(peoplejsonarray.getJSONObject(i).get("email").toString());
                record.setField6(peoplejsonarray.getJSONObject(i).get("displayname").toString());
                record.setStatus(peoplejsonarray.getJSONObject(i).get("peoplestatus").toString());
                record.setDatetime(sdf.format(currentTime));
                if(userOperation.selectCountByField(record) < 1)
                    userOperation.insert(record);
                sqlSession.commit();
            }

        }finally {
            sqlSession.close();
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/fieldvalueiddelete/{shareid}/{createby}/{status}")
    public boolean deleteByField(@PathVariable String shareid,@PathVariable String createby,@RequestBody Object[] grouppeople,@PathVariable String status){

//        System.out.println(grouppeople[0].toString());
//        JSONArray peoplejsonarray = new JSONArray(grouppeople);
//        System.out.println(peoplejsonarray.getJSONObject(0).get("uid"));
//        System.out.println(peoplejsonarray.getJSONObject(0).get("phtoturl"));

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            BO_FILEDSVALUEID record = new BO_FILEDSVALUEID();

            JSONArray peoplejsonarray = new JSONArray(grouppeople);

            System.out.println(peoplejsonarray.toString());

            for(int i=0; i< peoplejsonarray.length(); i++) {
                record.setProjectid(1);
                record.setField1(shareid);
                record.setField2(peoplejsonarray.getJSONObject(i).get("uid").toString());
                record.setStatus(status);
                record.setDatetime(sdf.format(currentTime));
                userOperation.deleteByField(record);
                sqlSession.commit();
            }

        }finally {
            sqlSession.close();
        }
        return true;
    }

    //根據任意字段更新任意字段
    @RequestMapping(method = RequestMethod.PUT,value = "/valuebyfieldid")
    public boolean updateByPrimaryKey(@RequestBody VALUEBYFIELD valuebyfield){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            userOperation.updateByField(valuebyfield.getProjectid(),valuebyfield.getFieldid1(),valuebyfield.getFieldvalue1(),valuebyfield.getFieldid2(),valuebyfield.getFieldvalue2());
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
        return true;
    }

    //刪除舉手數據
    @RequestMapping(method = RequestMethod.DELETE, value = "/fieldvalueid/{projectid}/{sequence}")
    public boolean deleteByPrimaryKey(@PathVariable int projectid,@PathVariable int sequence){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            userOperation.deleteByPrimaryKey(projectid,sequence);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
        return true;
    }

    //根據拼單號獲取舉手數量
    @RequestMapping(method = RequestMethod.GET, value = "/valuecountid/{projectid}/{status}")
    public Integer selectCount(@PathVariable int projectid, @PathVariable String status) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int count =0;
        try {
            System.out.println(projectid);
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            count = userOperation.selectCount(projectid,status);
        } finally {
            sqlSession.close();
        }

        return count;

    }

    //獲取當前用戶的拼單狀態
    @RequestMapping(method = RequestMethod.GET, value = "/getstatusid/{projectid}/{username}")
    public String getStatus(@PathVariable int projectid,@PathVariable String username) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String status ="0";
        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            status = userOperation.getStatus(projectid, username);
        } finally {
            sqlSession.close();
        }

        return status;

    }

}
