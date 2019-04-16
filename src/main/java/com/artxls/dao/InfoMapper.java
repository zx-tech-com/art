package com.artxls.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.artxls.entity.Info;
import com.artxls.entity.InfoExample;

public interface InfoMapper {
    long countByExample(InfoExample example);

    int deleteByExample(InfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Info record);

    int insertSelective(Info record);

    List<Info> selectByExampleWithBLOBs(InfoExample example);

    List<Info> selectByExample(InfoExample example);

    Info selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Info record, @Param("example") InfoExample example);

    int updateByExampleWithBLOBs(@Param("record") Info record, @Param("example") InfoExample example);

    int updateByExample(@Param("record") Info record, @Param("example") InfoExample example);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKeyWithBLOBs(Info record);

    int updateByPrimaryKey(Info record);
}