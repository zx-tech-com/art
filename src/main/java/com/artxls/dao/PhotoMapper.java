package com.artxls.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.artxls.entity.Photo;
import com.artxls.entity.PhotoExample;

public interface PhotoMapper {
    long countByExample(PhotoExample example);

    int deleteByExample(PhotoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Photo record);

    int insertSelective(Photo record);

    List<Photo> selectByExample(PhotoExample example);

    Photo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Photo record, @Param("example") PhotoExample example);

    int updateByExample(@Param("record") Photo record, @Param("example") PhotoExample example);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);
    
    List<Map<String,Object>> selectDistinctYears(Integer ntype);
}