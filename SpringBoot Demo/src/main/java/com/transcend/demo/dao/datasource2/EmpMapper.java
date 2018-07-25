package com.transcend.demo.dao.datasource2;

import com.transcend.demo.entity.datasource2.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpMapper {
    int deleteByPrimaryKey(Short empno);

    int insert(Emp record);

    Emp selectByPrimaryKey(Short empno);

    List<Emp> selectAll();

    int updateByPrimaryKey(Emp record);
}