package com.vnf.myshare.valueops.service;

import com.vnf.myshare.valueops.controller.FILEDSVALUEIDController;
import com.vnf.myshare.valueops.controller.UserProjectController;
import com.vnf.myshare.valueops.dao.BO_FILEDSVALUEIDMapper;
import com.vnf.myshare.valueops.model.BO_FILEDSVALUEID;
import com.vnf.myshare.valueops.model.BO_PROJECT;
import com.vnf.myshare.valueops.model.SubOrder;
import com.vnf.myshare.valueops.singleton.SingletonMybatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

@CrossOrigin
@RestController
public class GetSubOrder {
    @Autowired
    private static final Logger log = LoggerFactory.getLogger(GetSubOrder.class);
    private static SqlSessionFactory sqlSessionFactory;
    static {
        sqlSessionFactory = SingletonMybatis.getSqlSessionFactory();
    }

    //search all project
    @GetMapping(value = "/getsuborder/{uid}/{orderid}")
    public SubOrder getSubOrder(@PathVariable String uid, @PathVariable String orderid) {

        //新建一個ArrayList，不能用數組因為初始化的時候要指定大小，不能用List因為不能轉換為json
        ArrayList<BO_FILEDSVALUEID> subOrderList = new ArrayList<>();

        SubOrder subOrder = new SubOrder();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {

            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            BO_FILEDSVALUEID cond = new BO_FILEDSVALUEID();
            cond.setProjectid(2);
            cond.setField1(orderid);
            cond.setField2(uid);
            subOrder.order = userOperation.selectByField(cond).get(1);

            BO_FILEDSVALUEID cond2 = new BO_FILEDSVALUEID();
            cond2.setProjectid(3);
            cond2.setField1(orderid);
            cond2.setField2(subOrder.order.getSequence().toString());
            List<BO_FILEDSVALUEID> templist = userOperation.selectByField(cond2);

            for(int i=0; i<templist.size(); i++) {
                subOrderList.add(templist.get(i));
            }


        }finally {
            sqlSession.close();
        }

        //返回完整拼單數據
        return subOrder;
    }
}
