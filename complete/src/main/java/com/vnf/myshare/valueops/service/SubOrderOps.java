package com.vnf.myshare.valueops.service;

import com.vnf.myshare.valueops.dao.BO_FILEDSVALUEIDMapper;
import com.vnf.myshare.valueops.model.BO_FILEDSVALUEID;
import com.vnf.myshare.valueops.singleton.SingletonMybatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vnf.myshare.valueops.model.SubOrder;

import java.util.List;
import java.util.ArrayList;

@CrossOrigin
@RestController
public class SubOrderOps {
    @Autowired
    private static final Logger log = LoggerFactory.getLogger(SubOrderOps.class);
    private static SqlSessionFactory sqlSessionFactory;
    static {
        sqlSessionFactory = SingletonMybatis.getSqlSessionFactory();
    }

    //查詢子訂單信息，包括子訂單主信息及子訂單人員列表
    @GetMapping(value = "/suborder/{uid}/{orderid}")
    public SubOrder getSubOrder(@PathVariable String uid, @PathVariable String orderid) {

        //新建一個ArrayList，不能用數組因為初始化的時候要指定大小，不能用List因為不能轉換為json
        ArrayList<BO_FILEDSVALUEID> subOrderList = new ArrayList<>();

        SubOrder subOrder = new SubOrder();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //先從fieldvalue2子訂單主表中取到子訂單主信息
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            BO_FILEDSVALUEID cond = new BO_FILEDSVALUEID();
            cond.setProjectid(2);
            cond.setField1(orderid);
            cond.setField2(uid);
            subOrder.order = userOperation.selectByField(cond).get(0);
            //再從fieldvalue3子訂單人員表中取出所有該子訂單的相關人員
            BO_FILEDSVALUEID cond2 = new BO_FILEDSVALUEID();
            cond2.setProjectid(3);
            cond2.setField1(orderid);
            cond2.setField6(subOrder.order.getField6().toString());
            List<BO_FILEDSVALUEID> templist = userOperation.selectByField(cond2);

            BO_FILEDSVALUEID[] subOrderDetail = new BO_FILEDSVALUEID[templist.size()];

            subOrder.list = templist.toArray(subOrderDetail);

        }finally {
            sqlSession.close();
        }

        //返回完整拼單數據
        return subOrder;
    }

    //新增子單數據，包括新增子訂單主信息，新增子訂單人員清單，更新舉手狀態
    @RequestMapping(method = RequestMethod.POST,value = "/suborder")
    public boolean newSubOrder(@RequestBody SubOrder record){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            System.out.println(record);
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            userOperation.insert(record.order);
            for(int i=0; i< record.list.length; i++) {
                //新增子訂單人員清單
                userOperation.insert(record.list[i]);
                BO_FILEDSVALUEID NewStatusCond = new BO_FILEDSVALUEID();
                //更新舉手狀態，更新BO_FIELDVALUE0表，根據projectid必為0；Field1活動ID；Field2舉手uid；Status必為1（還沒被拉入其他小單）
                NewStatusCond.setProjectid(0);
                NewStatusCond.setField1(record.list[i].getField1());
                NewStatusCond.setField2(record.list[i].getField2());
                NewStatusCond.setField3(record.list[i].getField3());
                NewStatusCond.setStatus("2");
                userOperation.updateStatusByField(NewStatusCond);
            }
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
        return true;
    }

    //更新子單數據，包括更新子訂單主信息，更新子訂單人員清單，更新舉手狀態
    @RequestMapping(method = RequestMethod.PUT,value = "/suborder")
    public boolean updateSubOrder(@RequestBody SubOrder record){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            System.out.println(record);
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            userOperation.updateByPrimaryKey(record.order);
            for(int i=0; i< record.list.length; i++) {
                //更新子訂單人員清單
                userOperation.updateByPrimaryKey(record.list[i]);
                BO_FILEDSVALUEID NewStatusCond = new BO_FILEDSVALUEID();
                //更新舉手狀態
                NewStatusCond.setProjectid(0);
                NewStatusCond.setField1(record.list[i].getField1());
                NewStatusCond.setField2(record.list[i].getField2());
                NewStatusCond.setField3(record.list[i].getField3());
                NewStatusCond.setStatus("3");
                userOperation.updateStatusByField(NewStatusCond);
            }
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
        return true;
    }

    //更新子單金額和狀態
    @RequestMapping(method = RequestMethod.PUT,value = "/suborderconfirm")
    public boolean confirmSubOrder(@RequestBody BO_FILEDSVALUEID record){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            System.out.println(record);
            BO_FILEDSVALUEIDMapper userOperation = sqlSession.getMapper(BO_FILEDSVALUEIDMapper.class);
            userOperation.updateByPrimaryKey(record);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
        return true;
    }
}
