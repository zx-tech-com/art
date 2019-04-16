package com.artxls.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.artxls.entity.Milestone;
import com.artxls.entity.MilestoneExample;

public interface MilestoneMapper {
    long countByExample(MilestoneExample example);

    int deleteByExample(MilestoneExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Milestone record);

    int insertSelective(Milestone record);

    List<Milestone> selectByExample(MilestoneExample example);

    Milestone selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Milestone record, @Param("example") MilestoneExample example);

    int updateByExample(@Param("record") Milestone record, @Param("example") MilestoneExample example);

    int updateByPrimaryKeySelective(Milestone record);

    int updateByPrimaryKey(Milestone record);
    
    int batchInsert(@Param("events") List<Milestone> events);
}