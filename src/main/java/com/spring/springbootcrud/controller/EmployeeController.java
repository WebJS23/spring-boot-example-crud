package com.spring.springbootcrud.controller;

import com.spring.springbootcrud.Mapper.CompanyMapper;
import com.spring.springbootcrud.Mapper.EmployeeMapper;
import com.spring.springbootcrud.dao.DepartmentDao;
import com.spring.springbootcrud.dao.EmployeeDao;
import com.spring.springbootcrud.entities.Company;
import com.spring.springbootcrud.entities.Department;
import com.spring.springbootcrud.entities.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@Controller
public class EmployeeController {
    EmployeeDao employeeDao;
    DepartmentDao departmentDao=new DepartmentDao();
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    CompanyMapper companyMapper;
    //查询所有员工
    @GetMapping("/emps")
    public String list(Model model){
       Collection<Employee> employees= employeeMapper.getAllEmployee();
       System.out.println(employees);
       model.addAttribute("emps",employees);
        return "emp/list" ;
    }


    //员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //把所有部门添加到页面
        Collection<Company> company=companyMapper.findAll();
        model.addAttribute("depts",company);
        System.out.println(company);
        return "emp/add";
    }
    //员工添加
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //redirect 表示重定向
        //forward 表示转发到一个地址
        System.out.println(employee);
       employeeMapper.insertDept(employee);
        System.out.println(employee);
        return "redirect:/emps";
    }
    //修改员工页面
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
    Employee employee=employeeMapper.getEmployeeById(id);
    model.addAttribute("emp",employee);
        System.out.println(employee);
    Collection<Company> company=companyMapper.findAll();
    System.out.println(company);
    model.addAttribute("depts",company);
    return "emp/add";
    }
    //员工修改
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println(employee.getLastName());
        employeeMapper.updateDept(employee.getLastName(),employee.getEmail(),employee.getGender(),employee.getCompany().getCompany_id(),employee.getBirth(),employee.getId());

        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String toDeleteEmployee(@PathVariable("id") Integer id){
        employeeMapper.deleteEmployeeById(id);
        return "redirect:/emps";
    }
}
