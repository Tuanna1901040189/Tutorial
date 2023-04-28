package com.example.springboot.controller;


import com.example.springboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.springboot.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "/")
    public String getAllEmployee(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employeeList";
    }

    @RequestMapping(value = "/{id}")
    public String getEmployeeById
            (@PathVariable(value = "id") Long id, Model model) {
        Employee employee = employeeRepository.getById(id);
        model.addAttribute("employee", employee);
        return "employeeDetail";
    }
    //Add a new employee
    @RequestMapping(value = "/add")
    public String addEmployee (Model model) {
        Employee empNew = new Employee();
        model.addAttribute("empAdd", empNew);
        return "employeeAdd";
    }

    //Update employee's detail
    @RequestMapping (value = "/update/{id}")
    public String updateEmployee(
            @PathVariable (value = "id") Long id, Model model)  {
        Employee employee = employeeRepository.getById(id);
        model.addAttribute(employee);
        return "employeeUpdate";
    }

    //save change to the database
    @RequestMapping(value = "/save")
    public String saveUpdate
    (@RequestParam(value = "id", required = false) Long id, Employee emp)
    {
        emp.setId(id);
        employeeRepository.save(emp);
        return "redirect:/";
    }

    //Delete employee from the database
    @RequestMapping(value = "/delete/{id}")
    public String deleteEmployee(
            @PathVariable(value = "id") Long id) {
        Employee empDelete = employeeRepository.getById(id);
        employeeRepository.delete(empDelete);
        return "redirect:/";

    }
}