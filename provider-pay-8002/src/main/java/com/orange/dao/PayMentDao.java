package com.orange.dao;


import com.orange.model.PayMent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayMentDao {

    public PayMent selectById(String id);

    public  int insert(PayMent payMent);

}
