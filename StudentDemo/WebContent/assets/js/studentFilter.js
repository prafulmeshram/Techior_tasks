$("#fieldSearch").click(function() {
	$("#filterResultArea").empty();
	$("#fieldSearchDiv").show();
	$("#pagedResultArea").hide();
	$("#dateSearchDiv").hide();
});

$("#dateSearch").click(function() {
	$("#filterResultArea").empty();
	$("#fieldSearchDiv").hide();
	$("#pagedResultArea").hide();
	$("#dateSearchDiv").show();
});

$("#allPagedResults").click(function() {
	$("#filterResultArea").empty();
	$("#fieldSearchDiv").hide();
	$("#dateSearchDiv").hide();
	$("#pagedResultArea").show();
});

$("#searchFieldSubmit")
		.click(
				function(e) {

					e.stopImmediatePropagation();
					var formData = new FormData();
					formData.append("firstNameSearch", $("#firstNameSearch")
							.val());
					formData.append("lastNameSearch", $("#lastNameSearch")
							.val());
					formData.append("emailIdSearch", $("#emailIdSearch").val());
					formData.append("mobileNumberSearch", $(
							"#mobileNumberSearch").val());

					$
							.ajax({
								type : "post",
								async : true,
								url : "fieldSearch.action",
								data : formData,
								cache : false,
								contentType : false,
								processData : false,
								success : function(response) {
									$("#filterResultArea").empty();
									$("#filterResultArea").show();
									var jsonArray = JSON.parse(response);

									var tableHtml = "<table class='table table-striped' id='resultTable'>"
											+ "<thead>"
											+ "<tr>"
											+ "<td>Image</td> "
											+ "<td>Full Name</td>"
											+ "<td>Email Id</td>"
											+ "<td>Mobile Number</td>"
											+ "<td>Reg.Date</td>"
											+ " <td>Acad. Det.</td>"
											+ "</tr><thead>";
									var rowsHtml = "<tbody>";

									$
											.each(
													jsonArray,
													function(index, student) {
														rowsHtml += "<tr><td><img src='"
																+ student.imageName
																+ "' height='100px' width='80px'/></td><td>"
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
																+ "' onclick='loadAcadDetails(this.id);'>OPEN</button></td></tr>";
													})

									rowsHtml += "</tbody></table>";

									tableHtml += rowsHtml;

									$("#filterResultArea").append(tableHtml);

									$("#resultTable").DataTable();

								},
								error : function(error) {
									alert("Error = " + error);
								}
							});
					return false;
				});

$("#searchDateSubmit")
		.click(
				function(e) {

					e.stopImmediatePropagation();
					var formData = new FormData();
					formData.append("fromDate", $("#fromDate").val());
					formData.append("toDate", $("#toDate").val());

					$
							.ajax({
								type : "post",
								async : true,
								url : "dateSearch.action",
								data : formData,
								cache : false,
								contentType : false,
								processData : false,
								success : function(response) {

									$("#filterResultArea").empty();
									$("#filterResultArea").show();
									var jsonArray = JSON.parse(response);

									var tableHtml = "<table class='table table-striped' id='resultTable'>"
											+ "<thead>"
											+ "<tr>"
											+ "<td>Image</td> "
											+ "<td>Full Name</td>"
											+ "<td>Email Id</td>"
											+ "<td>Mobile Number</td>"
											+ "<td>Reg.Date</td>"
											+ " <td>Acad. Det.</td>"
											+ "</tr><thead>";
									var rowsHtml = "<tbody>";

									$
											.each(
													jsonArray,
													function(index, student) {
														rowsHtml += "<tr><td><img src='"
																+ student.imageName
																+ "' height='100px' width='80px'/></td><td>"
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
																+ "' onclick='loadAcadDetails(this.id);'>OPEN</button></td></tr>";
													})

									rowsHtml += "</tbody></table>";

									tableHtml += rowsHtml;

									$("#filterResultArea").append(tableHtml);
									$("#resultTable").DataTable();
								},
								error : function(error) {
									alert("Error = " + error);
								}
							});
					return false;
				});