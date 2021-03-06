package com.vnf.myshare.valueops.controller;

import com.vnf.myshare.valueops.dao.BO_FILEDSVALUEIDMapper;
import com.vnf.myshare.valueops.model.BO_FILEDSVALUE;
import com.vnf.myshare.valueops.model.BO_FILEDSVALUEID;
import com.vnf.myshare.valueops.service.RaiseHandStatus;
import com.vnf.myshare.valueops.singleton.SingletonMybatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import com.vnf.myshare.valueops.model.SubOrder;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import com.vnf.myshare.valueops.model.VALUEBYFIELD;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
/*@RequestMapping("/fieldsvalue") //在类上使用RequestMapping，里面设置的value就是方法的父路径*/
public class FILEDSVALUEIDController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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


    //獲取所有的類型的數據
    @RequestMapping(method = RequestMethod.GET, value = "/fieldvaluemulticond/{projectid}/{field1}/{value1}/{field2}/{value2}/{field3}/{value3}")
    public List<BO_FILEDSVALUEID> selectByMultiFieldValue(
            @PathVariable Integer projectid,
            @PathVariable String field1,
            @PathVariable String value1,
            @PathVariable String field2,
            @PathVariable String value2,
            @PathVariable String field3,
            @PathVariable String value3) {
        List<BO_FILEDSVALUEID> BO_FILEDSVALUEIDs;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            BO_FILEDSVALUEIDs = userOperation.selectByMultiFieldValue(projectid,field1,value1,field2,value2,field3,value3);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

        return BO_FILEDSVALUEIDs;

    }

    //新增舉手數據
    @RequestMapping(method = RequestMethod.POST,value = "/fieldvalueid")
    public boolean insert(@RequestBody BO_FILEDSVALUEID record){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            userOperation.insert(record);
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
                System.out.println("Friends count:"+userOperation.selectCountByField(record));
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
    public String deleteByField(@PathVariable String shareid,@PathVariable String createby,@RequestBody Object[] grouppeople,@PathVariable String status){

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
                BO_FILEDSVALUEID NewStatusCond = new BO_FILEDSVALUEID();
                NewStatusCond.setProjectid(0);
                NewStatusCond.setField1(shareid);
                NewStatusCond.setField2(peoplejsonarray.getJSONObject(i).get("uid").toString());
                NewStatusCond.setStatus("2");
                System.out.println("xxxxxxxxx");
                int checkind1 = userOperation.selectCountByField(NewStatusCond);
                if(checkind1 > 0) {
                    System.out.println(peoplejsonarray.getJSONObject(i).get("uid").toString());
                    return "{\"res\":\""+peoplejsonarray.getJSONObject(i).get("uid").toString()+"\"}";

                }

                NewStatusCond.setStatus("3");
                int checkind2 = userOperation.selectCountByField(NewStatusCond);
                if(checkind2 > 0)
                    return "{\"res\":\""+peoplejsonarray.getJSONObject(i).get("uid").toString()+"\"}";
            }

            for(int i=0; i< peoplejsonarray.length(); i++) {
                record.setProjectid(1);
                record.setField1(shareid);
                record.setField2(peoplejsonarray.getJSONObject(i).get("uid").toString());
                record.setStatus(status);
                record.setDatetime(sdf.format(currentTime));
                userOperation.deleteByField(record);
                sqlSession.commit();
            }
            System.out.println("Success");
            return "{\"res\":0}";

        }finally {
            sqlSession.close();
        }
    }

    //根據活動號獲取關注人數量
    @RequestMapping(method = RequestMethod.GET, value = "/valuecountbyid")
    public Integer selectCountByField(@RequestBody BO_FILEDSVALUEID record) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int count =0;
        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            count = userOperation.selectCountByField(record);
        } finally {
            sqlSession.close();
        }

        return count;

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

    //根據拼單號獲取舉手數量
    @RequestMapping(method = RequestMethod.GET, value = "/valuecountid/{projectid}/{shareid}/{status}")
    public Integer selectCount(@PathVariable int projectid, @PathVariable int shareid, @PathVariable String status) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int count =0;
        try {
            System.out.println(projectid);
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            count = userOperation.selectCount(projectid,shareid,status);
        } finally {
            sqlSession.close();
        }

        return count;

    }

    //獲取當前用戶的拼單狀態
    @RequestMapping(method = RequestMethod.GET, value = "/getstatusid/{projectid}/{shareid}/{username}")
    public String getStatus(@PathVariable int projectid,@PathVariable int shareid,@PathVariable String username) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String status ="0";
        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            status = userOperation.getStatus(projectid,shareid, username);
        } finally {
            sqlSession.close();
        }

        return status;

    }

    //獲取子單成團時間
    public String getDatetime(@PathVariable int projectid,@PathVariable int shareid,@PathVariable String username) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String status ="0";
        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            status = userOperation.getDatetime(projectid,shareid, username);
        } finally {
            sqlSession.close();
        }

        return status;

    }

    public List<String> getSubOrderKing(@PathVariable int projectid,@PathVariable int shareid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<String> status;
        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            status = userOperation.getSubOrderKing(projectid,shareid);
        } finally {
            sqlSession.close();
        }

        return status;

    }

    public List<RaiseHandStatus> getRaiseHandStatus(@PathVariable int projectid,@PathVariable int shareid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<RaiseHandStatus> status;
        try {
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            status = userOperation.getRaiseHandStatus(projectid,shareid);
        } finally {
            sqlSession.close();
        }

        return status;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getprojectdetail/{projectid}")
    public List getProjectDetail(@PathVariable String projectid) {
        String sql =
                "select * from ( "+
                "select t1.field2 as createTime, t3.field4 des, t1.field4 money,t1.field5 cnt " +
                "from bo_filedsvalue4 t1 " +
                "inner join bo_filedsvalue2 t2 on t1.field7 = t2.field6 " +
                "inner join bo_filedsvalue5 t3 on t1.field3 = t3.field3 " +
                "where t1.field3 in (4,5,9) and t2.field1 = " + projectid +
                "  union all " +
                "select t1.field2 as createTime,  t3.field4 des, t1.field4 money,t1.field5 cnt " +
                "from bo_filedsvalue4 t1 " +
                "inner join bo_project t2 on t1.field7 = t2.PROJECTID " +
                "inner join bo_filedsvalue5 t3 on t1.field3 = t3.field3 " +
                "where t1.field3 = 1  and t2.PROJECTID =" + projectid +
                " ) a  order by a.createTime desc";

        System.out.println("sql:"+sql);
        List result = jdbcTemplate.queryForList(sql);
        return result;

    }
}
