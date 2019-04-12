package com.spring.springbootcrud.Mapper;

import com.spring.springbootcrud.entities.Company;
import org.apache.ibatis.annotations.*;

import java.util.Collection;

@Mapper
public interface CompanyMapper {
    @Select("select * from company where company_id=#{id}")
    @Results(@Result(property = "company_name", column = "company_name"))
    public Company findById(@Param(value = "id") Integer id);
    @Select("select * from company")
    public Collection<Company> findAll();
}
