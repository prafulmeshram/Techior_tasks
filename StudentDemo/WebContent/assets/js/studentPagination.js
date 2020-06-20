$("#btnStudentPageList").click(function() {
	loadPage(3, 1);
});

function limitChange() {

	loadPage($("#limitSelect").val(), 1);
}

function previousPage(limit, currentPage) {
	if (limit == "" || limit == undefined || limit == 0 || isNaN(limit)) {
		limit = 3;
	}

	if (currentPage == "" || currentPage == undefined || currentPage == 0
			|| isNaN(currentPage)) {
		currentPage = 1;
	}

	if (currentPage > 1) {
		currentPage = currentPage - 1;
		loadPage(limit, currentPage);
	}

}

function nextPage(limit, currentPage, totalPages) {

	if (limit == "" || limit == undefined || limit == 0 || isNaN(limit)) {
		limit = 3;
	}

	if (currentPage == "" || currentPage == undefined || currentPage == 0
			|| isNaN(currentPage)) {
		currentPage = 1;
	}

	if (currentPage < totalPages) {
		currentPage = currentPage + 1;
		loadPage(limit, currentPage);
	}

}

function loadPage(limit, pageNumber) {
	/*
	 * $("#normalResults").empty(); $("#paginationRow").css('display', 'block');
	 */

	if (limit == "" || limit == undefined || limit == 0 || isNaN(limit)) {
		limit = 3;
	}

	if (pageNumber == "" || pageNumber == undefined || pageNumber == 0
			|| isNaN(pageNumber)) {
		pageNumber = 1;
	}

	$
			.ajax({
				type : "get",
				url : "studentPage?pageNumber=" + pageNumber + "&limit="
						+ limit,
				dataType : "json",
				async : true,
				success : function(response) {

					$("#paginatedResults").empty();
					$("#pageUl").empty();
					var jsonResponse = JSON.parse(response);
					$("#studentListCard").css('display', 'block');
					$("#registerCard").css('display', 'none');

					var students = jsonResponse.students;

					$
							.each(
									students,
									function(index, student) {

										var gridHtml = '<div class="row form-group">'
												+ '<div class="col-md-1"><img src="'
												+ student.imageName
												+ '" height="100px" width="80px"/></div>'
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
										$("#paginatedResults").append(gridHtml);

									})

					var totalPages = jsonResponse.totalPages;
					var currentPage = jsonResponse.currentPage;
					var totalResults = jsonResponse.totalResults;
					var actualLimit = jsonResponse.limit;
					var infoHtml = '';

					if (currentPage == totalPages) {
						infoHtml = '<div class="row form-group"><h5>Showing '
								+ totalResults + ' Of ' + totalResults
								+ '</h5></div>';
					} else {
						infoHtml = '<div class="row form-group"><h5>Showing '
								+ actualLimit * currentPage + ' Of '
								+ totalResults + '</h5></div>';
					}

					$("#paginatedResults").append(infoHtml);

					var innerPage = "";
					var previousElement = '<li class="page-item"><a class="page-link" onclick=previousPage('
							+ $("#limitSelect").val()
							+ ','
							+ currentPage
							+ ')>Previous</a></li>';

					var nextElement = '<li class="page-item"><a class="page-link" onclick = nextPage('
							+ $("#limitSelect").val()
							+ ','
							+ currentPage
							+ ','
							+ totalPages + ') >Next</a></li>';
					for (var i = 1; i <= totalPages; i++) {

						if (currentPage == i) {
							innerPage = innerPage
									.concat('<li class="page-item active"><a class="page-link" onclick=loadPage('
											+ $("#limitSelect").val()
											+ ','
											+ i
											+ ')>' + i + '</a></li>');
						} else {
							innerPage = innerPage
									.concat('<li class="page-item"><a class="page-link" onclick=loadPage('
											+ $("#limitSelect").val()
											+ ','
											+ i
											+ ')>' + i + '</a></li>');
						}
					}

					$("#pageUl").append(previousElement).append(innerPage)
							.append(nextElement);

				},
				error : function(error) {
					alert("Error Occured While Fetching List");

				}
			});

}