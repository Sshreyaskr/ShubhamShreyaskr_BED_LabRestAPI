<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>Student Directory</title>
</head>
<body>
    <div class="container">
    
        <h3 style="text-align:center;"><u>This is the student directory page</u></h2>
        <hr>
        
        <form action="/StudentManagement/students/search" class="form-inline">
        	<a href="/StudentManagement/students/showFormForAdd"
				class="btn btn-primary btn-sm mb-3"> Add Student </a> 
				
		<input type="search" name="firstName" placeholder="First Name"
		   class="form-control-sm ml-5 mr-2 mb-3">
		   
		<button type="submit" class="btn btn-success btn-sm mb-3">Search</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
		 <a href="/StudentManagement/logout"
				class="btn btn-primary btn-sm mb-3"> Logout </a> 	
        
        </form> 
        		
											
	   <table class="table table-bordered table-hover table-striped">
	   	<caption>List of students enrolled for the college fest</caption>
			<thead class="thead-light">
				<tr>
				    <th style="text-align:center;">Student Id</th>
					<th style="text-align:center;">First Name</th>
					<th style="text-align:center;">Last Name</th>
					<th style="text-align:center;">Course</th>
					<th style="text-align:center;">Country</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${Students}" var="tempStudent">
					<tr>
					    <td style="text-align:center;"><c:out value="${tempStudent.studentId}" /></td>
						<td style="text-align:center;"><c:out value="${tempStudent.firstName}" /></td>
						<td style="text-align:center;"><c:out value="${tempStudent.lastName}" /></td>
						<td style="text-align:center;"><c:out value="${tempStudent.course}" /></td>
						<td style="text-align:center;"><c:out value="${tempStudent.country}" /></td>
						<td>
							<!-- Add "update" button/link --> 
							<a href="/StudentManagement/students/showFormForUpdate?studentId=${tempStudent.studentId}"
							class="btn btn-info btn-sm"> Update </a> 
							
							<!-- Add "delete" button/link -->
							<a href="/StudentManagement/students/delete?studentId=${tempStudent.studentId}"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
								Delete </a>

						</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>	
    
    
    
    
    
    
    </div>
</body>
</html>