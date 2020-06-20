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
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/jszip-2.5.0/dt-1.10.21/af-2.3.5/b-1.6.2/b-colvis-1.6.2/b-flash-1.6.2/b-html5-1.6.2/b-print-1.6.2/cr-1.5.2/fc-3.3.1/fh-3.1.7/kt-2.5.2/r-2.2.5/rg-1.1.2/rr-1.2.7/sc-2.0.2/sp-1.1.1/sl-1.3.1/datatables.min.css" />
</head>

<s:head />
<sx:head />
<body>

	<div class="container mt-2 mb-2">
		<div class="row">

			<div class="col-md-12">
				<div class="card" id="registerCard" style="display: block;">
					<div class="card-header">
						<h4 class="card-title" style="display: inline-block;">Register
							Student</h4>
						<!-- <button class="btn btn-primary" id="btnStudentList"
							style="display: inline-block; float: right;">Student
							List</button> -->

						<button class="btn btn-primary" id="btnStudentPageList"
							style="display: inline-block; float: right;">Student
							List</button>

					</div>
					<div class="card-body">

						<div class="alert alert-success" id="successAlert"
							style="display: none"></div>

						<s:form action="saveStudent" method="post" id="studentForm"
							cssClass="form-horizontal" theme="simple"
							enctype="multipart/form-data">

							<div class="row form-group">
								<div class="col-md-3">
									<label class="control-label" for="firstName">First Name</label>
								</div>
								<div class="col-md-9">
									<s:textfield name="firstName" id="firstName"
										placeholder="Enter First Name" class="form-control" />

									<s:fielderror fieldName="firstName"></s:fielderror>
								</div>
							</div>

							<div class="row form-group">
								<div class="col-md-3">
									<label class="control-label" for="lastName">Last Name</label>
								</div>
								<div class="col-md-9">
									<s:textfield name="lastName" id="lastName"
										placeholder="Enter Last Name" class="form-control" />

									<s:fielderror fieldName="lastName"></s:fielderror>
								</div>
							</div>

							<div class="row form-group">
								<div class="col-md-3">
									<label class="control-label" for="mobileNumber">Mobile
										Number</label>
								</div>
								<div class="col-md-9">
									<s:textfield name="mobileNumber" id="mobileNumber"
										placeholder="Enter Mobile Number" class="form-control" />

									<s:fielderror fieldName="mobileNumber"></s:fielderror>
								</div>
							</div>

							<div class="row form-group">
								<div class="col-md-3">
									<label class="control-label" for="emailId">Email Id</label>
								</div>
								<div class="col-md-9">
									<s:textfield name="emailId" id="emailId"
										placeholder="Enter Email Id" class="form-control" />

									<s:fielderror fieldName="emailId"></s:fielderror>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-md-3">
									<label class="control-label" for="imageName">Image</label>
								</div>
								<div class="col-md-9">
									<s:file id="image" name="image" class="form-control"
										accept="image/*" />

								</div>
							</div>
							<hr />

							<div class="row form-group">

								<div class="col-md-12">
									<s:submit id="submit" name="submit" value="REGISTER"
										cssClass="btn btn-primary btn-md float-right" />
									<s:reset value="RESET"
										class="btn btn-danger btn-md float-right mr-2" />

								</div>
							</div>
						</s:form>
					</div>
				</div>




				<div class="card" id="studentListCard" style="display: none;">
					<div class="card-header">
						<h4 class="card-title" style="display: inline-block;">Students
							List</h4>
						<button class="btn btn-primary btn-sm float-right"
							style="display: inline-block;" id="showRegister">Regsiter
							Student</button>
					</div>
					<div class="card-body">


						<div class="row form-group">
							<div class="col-md-4">
								<input type="radio" value="fieldSearch" name="searchType"
									id="fieldSearch" />&emsp;<label for="fieldSearch">Field
									Wise Search</label>
							</div>
							<div class="col-md-4">
								<input type="radio" value="dateSearch" name="searchType"
									id="dateSearch" />&emsp;<label for="dateSearch">Registration
									Date Wise Search</label>
							</div>
							<div class="col-md-4">
								<input type="radio" value="allPagedResults" name="searchType"
									id="allPagedResults" checked="checked" />&emsp;<label
									for="allPagedResults">Show All Results</label>
							</div>
						</div>



						<div id="fieldSearchDiv" style="display: none;">
							<s:form action="fieldSearch.action" method="post"
								name="fieldSearchForm" id="fieldSearchForm"
								cssClass="form-horizontal" theme="simple">
								<div class="row form-group">
									<div class="col-md-2">
										<s:textfield name="firstNameSearch" id="firstNameSearch"
											placeholder="First Name" class="form-control" />
									</div>
									<div class="col-md-2">
										<s:textfield name="lastNameSearch" id="lastNameSearch"
											placeholder="Last Name" class="form-control" />
									</div>
									<div class="col-md-3">
										<s:textfield name="emailIdSearch" id="emailIdSearch"
											placeholder="Email Id" class="form-control" />
									</div>
									<div class="col-md-3">
										<s:textfield name="mobileNumberSearch" id="mobileNumberSearch"
											placeholder="Mobile Number" class="form-control" />
									</div>
									<div class="col-md-2">
										<sx:submit cssClass="btn btn-primary btn-md" value="SEARCH"
											id="searchFieldSubmit"></sx:submit>
									</div>

								</div>
							</s:form>
						</div>
						<div id="dateSearchDiv" style="display: none;">
							<s:form action="dateSearch.action" method="post"
								name="dateSearchForm" id="dateSearchForm"
								cssClass="form-horizontal" theme="simple">
								<div class="row form-group">
									<div class="col-md-4">
										<div class="row">
											<div class="col-md-4">
												<s:label for="fromDate">From Date</s:label>
											</div>
											<div class="col-md-8">
												<s:textfield name="fromDate" id="fromDate" type="date"
													class="form-control" />
											</div>

										</div>
									</div>
									<div class="col-md-4">
										<div class="row">
											<div class="col-md-4">
												<s:label for="toDate">To Date</s:label>
											</div>
											<div class="col-md-8">
												<s:textfield name="toDate" id="toDate" type="date"
													class="form-control" />
											</div>

										</div>
									</div>
									<div class="col-md-4">
										<sx:submit cssClass="btn btn-primary btn-block" value="SEARCH"
											id="searchDateSubmit"></sx:submit>
									</div>

								</div>
							</s:form>

						</div>



						<hr />


						<div id="pagedResultArea">

							<div class="row form-group" id="paginationRow">
								<div class="col-md-2" id="limitSelectArea">
									<div class="row">
										<div class="col-md-6">
											<label for="limitSelect">Show</label>
										</div>
										<div class="col-md-6">
											<select name="limitSelect" id="limitSelect"
												onchange="limitChange();" class="form-control">
												<option selected="selected">3</option>
												<option>5</option>
												<option>10</option>
												<option>25</option>
												<option>50</option>
											</select>
										</div>
									</div>

								</div>
								<div class="col-md-8" id="paginationArea">
									<nav class="float-right">
										<ul class="pagination pg-blue" id="pageUl">
										</ul>
									</nav>

								</div>
							</div>
							<hr />
							<div id="paginatedResults"></div>
							<div id="normalResults"></div>

						</div>



						<div id="filterResultArea"></div>


						<!-- <table id="listTable" class="table table-striped">
							<thead>
								<tr>
									<th>Image</th>
									<th>Student Name</th>
									<th>Mobile Number</th>
									<th>Email Id</th>
									<th>Reg. Date</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id="listTableBody">

							</tbody>

						</table> -->
					</div>

				</div>


			</div>
		</div>
	</div>






	<div class="modal" id="acadDetailsModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Academic Details</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="acadDetailsModalBody"></div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript" src="assets/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/js/additional-methods.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.loadscript.min.js"></script>
<script type="text/javascript"
	src="assets/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/studentValidation.js"></script>
<script type="text/javascript" src="assets/js/studentFilter.js"></script>
<script type="text/javascript" src="assets/js/studentPagination.js"></script>




<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/v/dt/jszip-2.5.0/dt-1.10.21/af-2.3.5/b-1.6.2/b-colvis-1.6.2/b-flash-1.6.2/b-html5-1.6.2/b-print-1.6.2/cr-1.5.2/fc-3.3.1/fh-3.1.7/kt-2.5.2/r-2.2.5/rg-1.1.2/rr-1.2.7/sc-2.0.2/sp-1.1.1/sl-1.3.1/datatables.min.js"></script>



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
										$("#normalResults").empty();

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

															var gridHtml = '<div class="row form-group">'
																	+ '<div class="col-md-1"><img src="'+student.imageName+'" height="100px" width="80px"/></div>'
																	+ '<div class="col-md-3">'
																	+ student.firstName
																	+ ' '
																	+ student.lastName
																	+ '</div> <div class="col-md-2">'
																	+ student.mobileNumber
																	+ '</div><div class="col-md-3">'
																	+ student.emailId
																	+ '</div> <div class="col-md-2">'
																	+ student.registrationDate
																	+ '</div><div class="col-md-1"><button class="btn btn-primary btn-sm" id="'
																	+ student.studentId
																	+ '" onclick="loadAcadDetails(this.id);">OPEN</button></div>'
																	+ '</div>';

															/* var html = "<tr><td><img src='"+student.imageName+"' height='100px' width='80px'/></td><td>"
																	+ student.firstName
																	+ " "
																	+ student.lastName
																	+ "</td><td>"
																	+ student.mobileNumber
																	+ "</td><td>"
																	+ student.emailId
																	+ "</td><td>"
																	+ student.registrationDate
																	+ "</td><td><button class='btn btn-primary btn-sm' id='"
																	+ student.studentId
																	+ "' onclick='loadAcadDetails(this.id);'>OPEN</button></td></tr>"; */

															$("#normalResults")
																	.append(
																			gridHtml);

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
		$("#pagedResultArea").show();
		$("#fieldSearchDiv").hide();
		$("#dateSearchDiv").hide();
		$("#filterResultArea").empty();
		$("#allPagedResults").prop('checked', true);

	});

	function loadAcadDetails(studentId) {
		$
				.ajax({
					type : "get",
					url : "loadAcadDetails.action?studentId=" + studentId,
					dataType : "json",
					async : true,
					success : function(response) {
						var object = JSON.parse(response);
						if (object.detId == 0) {
							$("#acadDetailsModal").modal("toggle");
							$("#acadDetailsModalBody").empty();
							$("#acadDetailsModalBody")
									.append(
											'<form id="acadDetForm" name="acadDetForm" method="post" action="saveAcadDet.action">');
							$("#acadDetailsModalBody form")
									.append(
											'<input name="detId" id="detId" type="hidden" value="'+object.detId+'"/>'
													+ '<input name="studentId" type="hidden" id="studentId" value="'+studentId+'"/>'
													+ '<div class="row form-group">'
													+ '<div class="col-md-4"><label for="academicYear">Academic Year</div><div class="col-md-8"><input name="academicYear" id="academicYear" class="form-control" placeholder="Enter Academic Year"/></div>'
													+ '</div>'
													+ '<div class="row form-group">'
													+ '<div class="col-md-4"><label for="standard">Standard</label></div><div class="col-md-8"><input name="standard" id="standard" class="form-control" placeholder="Enter Standard"/></div>'
													+ '</div>'
													+ '<div class="row form-group">'
													+ '<div class="col-md-4"><label for="section">Section</label></div><div class="col-md-8"><input name="section" id="section" class="form-control" placeholder="Enter Section"/></div>'
													+ '</div>'
													+ '<div class="row form-group">'
													+ '<div class="col-md-4"><label for="schoolName">School Name</label></div><div class="col-md-8"><input name="schoolName" id="schoolName" class="form-control" placeholder="Enter School Name"/></div>'
													+ '</div><hr/><input type="submit" value="SUBMIT" id="studentAcadFormSubmit" class="btn btn-primary btn-sm float-right"/></form>');

						} else {

							$("#acadDetailsModal").modal();
							$("#acadDetailsModalBody").empty();
							$("#acadDetailsModalBody")
									.append(
											'<form id="acadDetForm" name="acadDetForm" method="post" action="saveAcadDet.action">');
							$("#acadDetailsModalBody form")
									.append(
											'<input name="detId" id="detId" type="hidden" value="'+object.detId+'"/>'
													+ '<input name="studentId" type="hidden" id="studentId" value="'+studentId+'"/>'
													+ '<div class="row form-group">'
													+ '<div class="col-md-4"><label for="academicYear">Academic Year</div><div class="col-md-8"><input name="academicYear" id="academicYear" class="form-control" placeholder="Enter Academic Year" value="'+object.academicYear+'" /></div>'
													+ '</div>'
													+ '<div class="row form-group">'
													+ '<div class="col-md-4"><label for="standard">Standard</label></div><div class="col-md-8"><input name="standard" id="standard" class="form-control" placeholder="Enter Standard" value="'+object.standard+'"/></div>'
													+ '</div>'
													+ '<div class="row form-group">'
													+ '<div class="col-md-4"><label for="section">Section</label></div><div class="col-md-8"><input name="section" id="section" class="form-control" placeholder="Enter Section" value="'+object.section+'"/></div>'
													+ '</div>'
													+ '<div class="row form-group">'
													+ '<div class="col-md-4"><label for="schoolName">School Name</label></div><div class="col-md-8"><input name="schoolName" id="schoolName" class="form-control" placeholder="Enter School Name" value="'+object.schoolName+'"/></div>'
													+ '</div><hr/><input type="submit" value="SUBMIT" id="studentAcadFormSubmit" class="btn btn-primary btn-sm float-right"/></form>');
						}
						$.loadScript('assets/js/studentAcadDetValidation.js');
					},
					error : function(error) {
						alert("Error Occured" + JSON.stringify(error));
					}

				});

	}
</script>




</html>