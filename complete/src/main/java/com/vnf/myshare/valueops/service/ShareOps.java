package com.vnf.myshare.valueops.service;

import com.vnf.myshare.valueops.dao.BO_FILEDSVALUEIDMapper;
import com.vnf.myshare.valueops.dao.BO_PROJECTRepository;
import com.vnf.myshare.valueops.model.BO_FILEDSVALUEID;
import com.vnf.myshare.valueops.model.BO_PROJECT;
import com.vnf.myshare.valueops.controller.FILEDSVALUEIDController;
import com.vnf.myshare.valueops.singleton.SingletonMybatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class ShareOps {
    @Autowired
    BO_PROJECTRepository bo_projectRepository;
    @Autowired
    FILEDSVALUEIDController filedsvalueidController;
    private static final Logger log = LoggerFactory.getLogger(SubOrderOps.class);
    private static SqlSessionFactory sqlSessionFactory;
    static {
        sqlSessionFactory = SingletonMybatis.getSqlSessionFactory();
    }

    //新建拼單
    @PostMapping(value = "/share")
    public BO_PROJECT addProject(@RequestBody BO_PROJECT record) {
        //新增拼單信息
        BO_PROJECT Share = bo_projectRepository.save(record);
        //將拼單創建人的信息加入拼單關注人列表
        BO_FILEDSVALUEID creater = new BO_FILEDSVALUEID();
        creater.setProjectid(1);
        creater.setField1(Share.getProjectid().toString());
        creater.setField2(Share.getCreateby());
        creater.setField4(Share.getCreateby());
        creater.setStatus("1");
        filedsvalueidController.insert(creater);

        return Share;
    }
}
