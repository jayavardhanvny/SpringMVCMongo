    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    


       <html>
            	<body>
            		<h1>Add New Employee</h1>
            		<form method="POST" action="save">
            		<table >
                       <tr>
                          <td>Name : </td>
                             <td><input type="text" id="employeeName" name="employeeName"></td>
                       </tr>
					   <tr>
                          <td>Salary : </td>
                             <td><input type="text" id="salary" name="salary"></td>
                       </tr>
						<tr>
              			<td> </td>
              			<td><input type="submit" value="Save" /></td>
             		   </tr>

            		</form>
            	</body>
            </html>




            <h1>Add New Employee</h1>  
           <form:form method="post" action="save">    
            <table >
             <tr>    
              <td>Name : </td>   
              <td><form:input path="employeeName"  /></td>
             </tr>    
             <tr>    
              <td>Salary :</td>    
              <td><form:input path="salary" /></td>  
             </tr>   

             <tr>    
              <td> </td>    
              <td><input type="submit" value="Save" /></td>    
             </tr>    
            </table>    
           </form:form>