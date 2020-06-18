$.validator.addMethod("emailId", function(value, element) {
	return this.optional(element)
			|| /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/i.test(value);
}, "Please enter a valid email address.");

$.validator.addMethod("mobileNumber", function(value, element) {
	return this.optional(element) || /^[0-9]+$/i.test(value);
}, "Please enter a valid Mobile Number");

$("#studentForm").validate({
	ignore : ":hidden",

	rules : {
		firstName : {
			required : true,
			minlength : 3,
			maxlength : 50

		},
		lastName : {
			required : true,
			minlength : 3,
			maxlength : 50
		},
		mobileNumber : {
			required : true,
			minlength : 10,
			maxlength : 15,
			mobileNumber : mobileNumber
		},
		emailId : {
			required : true,
			minlength : 10,
			maxlength : 100,
			emailId : emailId
		},
		image : {
			required : true,
		}

	},
	submitHandler : function() {
		$("#studentForm").submit(function(e) {

			e.preventDefault();
			e.stopImmediatePropagation();
			var data = new FormData();
			data.append('firstName', $("#firstName").val());
			data.append('lastName', $("#lastName").val());
			data.append('mobileNumber', $("#mobileNumber").val());
			data.append('emailId', $("#emailId").val());
			data.append('imageName', $("#image")[0].files[0].name);
			data.append('image', $("#image")[0].files[0]);

			$
			jQuery.ajax({
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
					$("#studentForm")[0].reset();
				},
				error : function(error) {
					alert("Error Occured" + JSON.stringify(error));
				}
			});

			return false;
		})
	}

});
