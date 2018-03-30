package com.vnf.flow.run;

import java.io.Reader;
import java.math.BigDecimal;
import java.util.*;
import java.text.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.vnf.flow.dao.*;
import com.vnf.flow.model.*;

public class BO_FILEDSVALUERun {

	//----------------------------------------------------------------------------
	// 这部分不变
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    private String icbc3DeskeyPath="";

    static{
        try{
        	reader    = Resources.getResourceAsReader("Configuration.xml");
        	sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    //----------------------------------------------------------------------------

    public List<BO_FILEDSVALUE> selectAll(int projectid){
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
    		BO_FILEDSVALUEMapper userOperation = session.getMapper(BO_FILEDSVALUEMapper.class);
    		List<BO_FILEDSVALUE> heros = userOperation.selectAll(projectid);
    		return heros;
    	}finally{
    		session.close();
    	}
    }


    //----------------------------------------------------------------------------
	// 这部分不变
    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
        } finally {
        	session.close();
        }
    }
    //----------------------------------------------------------------------------

}
