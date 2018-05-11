<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
<h1>Employees List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Id</th><th>Name</th><th>Salary</th><th>Edit</th><th>Delete</th></tr>
   <c:forEach var="emp" items="${list}">   
   <tr>  
   <td>${emp.employeeId}</td>
   <td>${emp.employeeName}</td>
   <td>${emp.salary}</td>
   <td><a href="editemp/${emp.employeeId}">Edit</a></td>
   <td><a href="deleteemp/${emp.employeeId}">Delete</a></td>
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
   <a href="empform">Add New Employee</a> 