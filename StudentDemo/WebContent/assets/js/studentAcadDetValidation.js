$.validator.addMethod("academicYear", function(value, element) {
	return this.optional(element) || /^[0-9]{4}-[0-9]{2,4}$/i.test(value);
}, "Please enter a valid Academic Year eg:2019-2020 ");

$.validator.addMethod("section", function(value, element) {
	return this.optional(element) || /^[A-Z]$/i.test(value);
}, "Please enter a valid Section  eg:A ");


$("#acadDetForm").validate({
	ignore : ":hidden",

	rules : {
		academicYear : {
			required : true,
			minlength : 7,
			maxlength : 9,
			academicYear : academicYear

		},
		standard : {
			required : true,
			maxlength : 25
		},
		section : {
			required : true,
			maxlength : 1,
			section : section
		},
		schoolName : {
			required : true,
			minlength : 2,
			maxlength : 150

		}

	},
	submitHandler : function() {
		$("#acadDetForm").submit(function(e) {
			e.preventDefault();
			e.stopImmediatePropagation();
			var data = new FormData();
			data.append('detId', $("#detId").val());
			data.append('academicYear', $("#academicYear").val());
			data.append('standard', $("#standard").val());
			data.append('section', $("#section").val());
			data.append('schoolName', $("#schoolName").val());
			data.append('studentId', $("#studentId").val());

			/*
			 * var data = { 'detId' : $("#detId").val(), 'academicYear' :
			 * $("#academicYear").val(), 'standard' : $("#standard").val(),
			 * 'section' : $("#section").val(), 'schoolName' :
			 * $("#schoolName").val(), 'studentId' : $("#studentId").val() };
			 */

			jQuery.ajax({
				type : "post",
				async : true,
				url : "saveAcadDet.action",
				data : data,
				dataType : "json",
				cache : true,
				contentType : false,
				processData : false,
				success : function(response) {
					var jsonObject = JSON.parse(response);
					alert(jsonObject.message);
					$("#acadDetailsModal").modal("toggle");
				},
				error : function(error) {
					alert("Error Occured" + JSON.stringify(error));
				}
			});

			return false;
		})
	}
});