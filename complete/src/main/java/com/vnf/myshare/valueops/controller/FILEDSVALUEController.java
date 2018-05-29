package com.vnf.myshare.valueops.controller;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.*;

import com.vnf.myshare.valueops.dao.*;
import com.vnf.myshare.valueops.model.*;
import com.vnf.myshare.valueops.singleton.SingletonMybatis;

@CrossOrigin
@RestController
/*@RequestMapping("/fieldsvalue") //在类上使用RequestMapping，里面设置的value就是方法的父路径*/
public class FILEDSVALUEController {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        sqlSessionFactory = SingletonMybatis.getSqlSessionFactory();
    }

    //这里体现了restful风格的请求，按照请求的类型，来进行增删查改。
    //设计restful api（其实也就是URL），不要有冗余，例如不要写成getUsers，URL中最好不要有动词。
    // 这里用的是路径变量，就是{}括起来的，会当做变量读进来
    //獲取所有的舉手數據
    @RequestMapping(method = RequestMethod.GET, value = "/fieldvalue/{projectid}")
    public List<BO_FILEDSVALUE> selectAll(@PathVariable int projectid) {
        List<BO_FILEDSVALUE> bo_filedsvalues;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BO_FILEDSVALUEMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEMapper.class);
            bo_filedsvalues = userOperation.selectAll(projectid);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

        return bo_filedsvalues;

    }

    //新增舉手數據
    @RequestMapping(method = RequestMethod.POST,value = "/fieldvalue")
    public boolean insert(@RequestBody BO_FILEDSVALUE record){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BO_FILEDSVALUEMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEMapper.class);
            userOperation.insert(record);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
        return true;
    }

    //更新舉手數據
    @RequestMapping(method = RequestMethod.PUT,value = "/fieldvalue")
    public boolean updateByPrimaryKey(@RequestBody BO_FILEDSVALUE record){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BO_FILEDSVALUEMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEMapper.class);
            userOperation.updateByPrimaryKey(record);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
        return true;
    }

    //刪除舉手數據
    @RequestMapping(method = RequestMethod.DELETE, value = "/fieldvalue/{projectid}/{sequence}")
    public boolean deleteByPrimaryKey(@PathVariable int projectid,@PathVariable int sequence){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BO_FILEDSVALUEMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEMapper.class);
            userOperation.deleteByPrimaryKey(projectid,sequence);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
        return true;
    }

    //根據拼單號獲取舉手數量
    @RequestMapping(method = RequestMethod.GET, value = "/valuecount/{projectid}/{status}")
    public Integer selectCount(@PathVariable int projectid, @PathVariable String status) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int count =0;
        try {
            System.out.println(projectid);
            BO_FILEDSVALUEMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEMapper.class);
            count = userOperation.selectCount(projectid,status);
        } finally {
            sqlSession.close();
        }

        return count;

    }

    //獲取當前用戶的拼單狀態
    @RequestMapping(method = RequestMethod.GET, value = "/getstatus/{projectid}/{username}")
    public String getStatus(@PathVariable int projectid,@PathVariable String username) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String status ="0";
        try {
            BO_FILEDSVALUEMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEMapper.class);
            status = userOperation.getStatus(projectid, username);
        } finally {
            sqlSession.close();
        }

        return status;

    }

}
