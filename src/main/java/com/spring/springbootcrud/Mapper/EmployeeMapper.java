package com.spring.springbootcrud.Mapper;

import com.spring.springbootcrud.entities.Company;
import com.spring.springbootcrud.entities.Employee;
import com.spring.springbootcrud.entities.User;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.Date;

@Mapper
public interface EmployeeMapper {
    @Select("select * from employee")
    @Results({
            @Result(column="employee_id",property="id"),
            @Result(column="employee_name",property="lastName"),
            @Result(column="employee_email",property="email"),
            @Result(column="gender",property="gender"),
            @Result(column="company_id",property="company",one=@One(select="com.spring.springbootcrud.Mapper.CompanyMapper.findById"))
    })
    public Collection<Employee> getAllEmployee();
    @Select("select * from employee where employee_id=#{id}")
    @Results({
            @Result(column="employee_id",property="id"),
            @Result(column="employee_name",property="lastName"),
            @Result(column="employee_email",property="email"),
            @Result(column="gender",property="gender"),
            @Result(column="company_id",property="company",one=@One(select="com.spring.springbootcrud.Mapper.CompanyMapper.findById"))
    })

    public  Employee getEmployeeById(Integer id);
    @Delete("delete from employee where employee_id=#{id}")
    public int deleteEmployeeById(Integer id);
    @Options(useGeneratedKeys = true,keyProperty = "employee.id")
    @Insert("insert into employee values(null,#{employee.lastName},#{employee.email},#{employee.gender},#{employee.company.company_id},#{employee.birth})")
    public int insertDept(@Param("employee")Employee employee);
//    要有@param
    @Update("update employee set employee_name=#{name},employee_email=#{email},gender=#{gender},birth=#{birth},company_id=#{cid} where employee_id=#{id}")
    public int updateDept(String name, String email, Integer gender, Integer cid, Date birth,Integer id);

}
