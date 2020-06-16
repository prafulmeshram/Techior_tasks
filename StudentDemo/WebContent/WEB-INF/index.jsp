<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Page</title>
<link href="assets/bootstrap-4.4.1-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

</head>
<sx:head />
<s:head />
<body>

	<div class="container mt-2">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="card" id="registerCard" style="display: block;">
					<div class="card-header">
						<h4 class="card-title" style="display: inline-block;">Register
							Student</h4>
						<button class="btn btn-primary" id="btnStudentList"
							style="display: inline-block; float: right;">Student
							List</button>
					</div>
					<div class="card-body">

						<div class="alert alert-success" id="successAlert"
							style="display: none"></div>

						<s:form action="saveStudent" method="post" id="studentForm"
							cssClass="form-horizontal" theme="simple" validate="true"
							enctype="multipart/form-data">

							<div class="row form-group">
								<div class="col-md-4">
									<label class="control-label" for="firstName">First Name</label>
								</div>
								<div class="col-md-8">
									<s:textfield name="firstName" id="firstName"
										placeholder="Enter First Name" class="form-control" />
									<s:fielderror fieldName="firstName"></s:fielderror>
								</div>
							</div>

							<div class="row form-group">
								<div class="col-md-4">
									<label class="control-label" for="lastName">Last Name</label>
								</div>
								<div class="col-md-8">
									<s:textfield name="lastName" id="lastName"
										placeholder="Enter Last Name" class="form-control" />
									<s:fielderror fieldName="lastName"></s:fielderror>
								</div>
							</div>

							<div class="row form-group">
								<div class="col-md-4">
									<label class="control-label" for="mobileNumber">Mobile
										Number</label>
								</div>
								<div class="col-md-8">
									<s:textfield name="mobileNumber" id="mobileNumber"
										placeholder="Enter Mobile Number" class="form-control" />
									<s:fielderror fieldName="mobileNumber"></s:fielderror>
								</div>
							</div>

							<div class="row form-group">
								<div class="col-md-4">
									<label class="control-label" for="emailId">Email Id</label>
								</div>
								<div class="col-md-8">
									<s:textfield name="emailId" id="emailId"
										placeholder="Enter Email Id" class="form-control" />
									<s:fielderror fieldName="emailId"></s:fielderror>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-md-4">
									<label class="control-label" for="imageName">Image</label>
								</div>
								<div class="col-md-8">
									<s:file id="image" name="image" class="form-control"
										accept="image/*" />
								</div>



							</div>
							<hr />

							<div class="row form-group">
								<div class="col-md-8"></div>
								<div class="col-md-4">
									<sx:submit name="submit" value="REGISTER" id="submit"
										cssClass="btn btn-primary btn-block" />
								</div>
							</div>
						</s:form>
					</div>
				</div>




				<div class="card" id="studentListCard" style="display: none;">
					<div class="card-header">
						<h4 class="card-title" style="display: inline-block;">Students
							List</h4>
						<button class="btn btn-primary"
							style="display: inline-block; float: right;" id="showRegister">Regsiter
							Student</button>
					</div>
					<div class="card-body">
						<table id="listTable" class="table table-striped">
							<thead>
								<tr>
									<th>Image</th>
									<th>Student Name</th>
									<th>Mobile Number</th>
									<th>Email Id</th>
								</tr>
							</thead>
							<tbody id="listTableBody">

							</tbody>

						</table>
					</div>
				</div>


			</div>
			<div class="col-md-2"></div>
		</div>
	</div>

</body>

<script type="text/javascript" src="assets/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript"
	src="assets/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>




<script type="text/javascript">
	$(document).ready(function() {
		$("#submit").click(function() {
			var data = new FormData();

			var firstName = $("#firstName").val();
			var lastName = $("#lastName").val();
			var mobileNumber = $("#mobileNumber").val();
			var emailId = $("#emailId").val();

			data.append('firstName', firstName);
			data.append('lastName', lastName);
			data.append('mobileNumber', mobileNumber);
			data.append('emailId', emailId);
			data.append('imageName', $("#image")[0].files[0].name);
			data.append('image', $("#image")[0].files[0]);

			$.ajax({
				type : "post",
				async : true,
				url : "saveStudent.action",
				data : data,
				enctype : "multipart/form-data",
				cache : false,
				contentType : false,
				processData : false,
				success : function(response) {
					var jsonObject = JSON.parse(response);
					$("#successAlert").css('display', 'block');
					$("#successAlert").html(jsonObject.message);
				},
				error : function(error) {
					alert("Error Occured" + JSON.stringify(error));

				}
			});

		});

	});
</script>


<script type="text/javascript">
	$("#btnStudentList")
			.click(
					function() {
						$
								.ajax({
									type : "get",
									url : "studentList.action",
									dataType : "json",
									async : true,
									success : function(response) {
										$("#listTableBody").empty();
										var jsonResponse = JSON.parse(JSON
												.stringify(response));
										$("#studentListCard").css('display',
												'block');
										$("#registerCard").css('display',
												'none');

										$
												.each(
														jsonResponse,
														function(index, student) {
															var html = "<tr><td><img src='"+student.imageName+"' height='100px' width='80px'/></td><td>"
																	+ student.firstName
																	+ " "
																	+ student.lastName
																	+ "</td><td>"
																	+ student.mobileNumber
																	+ "</td><td>"
																	+ student.emailId
																	+ "</td></tr>";

															$("#listTableBody")
																	.append(
																			html);

														})
									},
									error : function(error) {
										alert("Error Occured While Fetching List");

									}
								});

					});

	$("#showRegister").click(function() {
		$("#studentListCard").css('display', 'none');
		$("#registerCard").css('display', 'block');

	});
</script>


</html>