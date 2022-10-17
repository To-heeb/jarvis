
    
    <div class="dropdown">
       <button class="btn p-0 buy-now" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <div class=" btn btn-primary btn-buy-now" data-bs-toggle="modal" data-bs-target="#uploadModal">
	       Upload
	    </div>
       </button>
     </div>

    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src="../dashboard_assets/vendor/libs/jquery/jquery.js"></script>
    <script src="../dashboard_assets/vendor/libs/popper/popper.js"></script>
    <script src="../dashboard_assets/vendor/js/bootstrap.js"></script>
    <script src="../dashboard_assets/libs/dropzone/dropzone.js"></script>
    <script src="../dashboard_assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="../dashboard_assets/js/sweetalert2.min.js"></script>
	<!--  <script src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/5.9.2/min/dropzone-amd-module.min.js" integrity="sha512-VQQXLthlZQO00P+uEu4mJ4G4OAgqTtKG1hri56kQY1DtdLeIqhKUp9W/lllDDu3uN3SnUNawpW7lBda8+dSi7w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>-->
    <script src="../dashboard_assets/vendor/js/menu.js"></script>
    <!-- endbuild -->

    <!-- Vendors JS -->
    <script src="../dashboard_assets/vendor/libs/apex-charts/apexcharts.js"></script>

    <!-- Main JS -->
    <script src="../dashboard_assets/js/main.js"></script>

    <!-- Page JS -->
    <script src="../dashboard_assets/js/dashboards-analytics.js"></script>
    <script src="../dashboard_assets/js/ui-modals.js"></script>
    <script type="text/javascript">
    
	    document.addEventListener("DOMContentLoaded", function(){
	    	
	    	
	    	// get base url
	    	//let base_url = document.getElementById("base_url").value
	    	//console.log(base_url);
		 	// Get a reference to the file input element
		    const inputElement = document.querySelector('input[type="file"]');
		
		 	
		 	// Dropzone instance
			var myDropzone = new Dropzone(".dropzone", {
		    	url : "/dms/upload_encrypted",
		    	autoDiscover: false,
		        paramName: "uploaded_file",
		        maxFilesize: 1000,
		        parallelUploads: 100,
		        maxFiles: 1,
		        autoProcessQueue: true,
		        uploadMultiple: false,
		        addRemoveLinks: true,
		        createImageThumbnails: true,
		        timeout: 100000,
		        init: function() {
	        	  this.on("sending", function(file, xhr, formData) {
	        		  // append folder_id here
	        		  var folder_id = document.querySelector("#folder_id_ajax").value;
	        		  console.log(folder_id)
	        		  formData.append("folder_id", folder_id);
	   
	        		 Swal.fire({
	                        html: "Encrypting file....",
	                        allowOutsideClick: false,
	                        didOpen: () => {
	                            Swal.showLoading();
	                        },
	                    });
	        	    });
	        	    this.on("success", function(files, response) {
	        	    	console.log(response)
	        	    	 var obj = jQuery.parseJSON(response);
	        	    	 console.log(obj);
	        	    	if (obj.status == "success") {
	                        console.log(obj.message)
	                        Swal.fire({
	                        	position: 'top-end',
	                            icon: "success",
	                            title: "Great!!",
	                            showConfirmButton: false,
	                            allowOutsideClick: false,
	                            text: "Encryption successfully completed",
	                            timer: 2500,
	                        }).then((result) => {
	                          location.reload();
	                        });
	                    } else {
	                        console.log(obj.message)
	                        Swal.fire({
	                            icon: "error",
	                            title: "Oops...",
	                            allowOutsideClick: false,
	                            showConfirmButton: false,
	                            text: "Encryption failed, Something went wrong please try again later",
	                            timer: 2500,
	                        }).then((result) => {
	                          location.reload();
	                        });
	                    }
	        	    });
	        	    this.on("error", function(files, response) {
	        	    	Swal.fire({
	                        icon: "error",
	                        title: "Oops...",
	                        allowOutsideClick: false,
	                        text: "Something went wrong!",
	                    }).then((result) => {
	                        /* Read more about isConfirmed, isDenied below */
	                        if (result.isConfirmed) {
	                            location.reload();
	                        }
	                    });
	        	    });
		        }
		    })
			
		 	
			var myDropzone = new Dropzone(".modal_dropzone", {
		    	url : "/dms/upload_encrypted",
		    	autoDiscover: false,
		        paramName: "uploaded_file",
		        maxFilesize: 1000,
		        parallelUploads: 100,
		        maxFiles: 1,
		        autoProcessQueue: true,
		        uploadMultiple: false,
		        addRemoveLinks: true,
		        createImageThumbnails: true,
		        timeout: 100000,
		        init: function() {
	        	  this.on("sending", function(file, xhr, formData) {
	        		  // append folder_id here
	        		  var folder_id = document.querySelector("#folder_id_ajax").value;
	        		  console.log(folder_id)
	        		  formData.append("folder_id", folder_id);
	        		  $("#uploadModal").modal('hide')
	        		  Swal.fire({
	                        html: "Encrypting file....",
	                        allowOutsideClick: false,
	                        didOpen: () => {
	                            Swal.showLoading();
	                        },
	                    });
	        	    });
	        	    this.on("success", function(files, response) {
	        	    	console.log(response)
	        	    	 var obj = jQuery.parseJSON(response);
	        	    	 //console.log(obj);
	        	    	if (obj.status == "success") {
	                        console.log(obj.message)
	                        console.log(obj.encryption)
	                        Swal.fire({
	                        	position: 'top-end',
	                            icon: "success",
	                            title: "Great!!",
	                            showConfirmButton: false,
	                            allowOutsideClick: false,
	                            text: "Encryption successfully completed",
	                            timer: 2500,
	                        }).then((result) => {
	                          location.reload();
	                        });
	                    } else {
	                        console.log(obj.message)
	                        Swal.fire({
	                            icon: "error",
	                            title: "Oops...",
	                            allowOutsideClick: false,
	                            showConfirmButton: false,
	                            text: "Encryption failed, Something went wrong please try again later",
	                            timer: 2500,
	                        }).then((result) => {
	                          location.reload();
	                        });
	                    }
	        	    });
	        	    this.on("error", function(files, response) {
	        	    	Swal.fire({
	                        icon: "error",
	                        title: "Oops...",
	                        allowOutsideClick: false,
	                        text: "Something went wrong!",
	                    }).then((result) => {
	                        /* Read more about isConfirmed, isDenied below */
	                        if (result.isConfirmed) {
	                            location.reload();
	                        }
	                    });
	        	    });
		        }
		    })
		    
		    
		 	// make value for renaming folder show in form field
		    document.querySelector(".folder_row").addEventListener("click", (event) => {
		    	document.getElementById('rename_modal_value').value = event.target.parentElement.previousElementSibling.lastElementChild.value; 
		    	document.getElementById('folder_id').value = event.target.nextElementSibling.value;
		    })
		    
		    // make value for renaming file show in form field
		    document.querySelector(".file_row").addEventListener("click", (event) => {
		    	//alert(event.target.nextElementSibling.value)
		    	document.getElementById('rename_file_modal_value').value = event.target.parentElement.parentElement.parentElement.parentElement.lastElementChild.previousElementSibling.value; 
		    	document.getElementById('file_id').value = event.target.nextElementSibling.value;
		    })
		    
		    // make the preview modal show file type
		    document.querySelector(".file_row").addEventListener("click", (event) => {
		    	var file_type = event.target.parentElement.parentElement.parentElement.parentElement.lastElementChild.previousElementSibling.previousElementSibling.previousElementSibling.value; 
		    	//alert(file_type)
		    	if(file_type == 'image'){
		    		var image_link = event.target.parentElement.parentElement.parentElement.parentElement.lastElementChild.previousElementSibling.previousElementSibling.value;
		    		document.getElementById('preview_title').innerHTML = event.target.parentElement.parentElement.parentElement.parentElement.lastElementChild.previousElementSibling.value; 
		    		document.getElementById("preview_div").innerHTML = "<img src='"+image_link+"' width='90%' height='300'/>"
		    	}else{
			    	document.getElementById('preview_title').innerHTML = event.target.parentElement.parentElement.parentElement.parentElement.lastElementChild.previousElementSibling.value; 
			    	var iframe_link = event.target.parentElement.parentElement.parentElement.parentElement.lastElementChild.previousElementSibling.previousElementSibling.value;
			    	document.getElementById("preview_div").innerHTML = "<iframe src='"+iframe_link+"' width='100%' height='300' class=''  id='preview_iframe' loading='lazy' allowtransparency=true' allowfullscreen ></iframe>";
			    	//console.dir(document.getElementById('preview_iframe'))
		    	}
		    })
			
		    //
		      
		    <%

			// get dashboard url
			String url = request.getRequestURL().toString();
			String dashboardURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/dashboard/home";
			String settingsURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/dashboard/settings";
			
			%>
	
			
		    // message response on file creation
			let status = document.querySelector("#folder_status").value;
        	
        	if(status == "error"){
        		Swal.fire({
        			  icon: 'error',
        			  title: 'Oops...',
        			  text: 'File name can\'t be empty',
        			  showConfirmButton: false,
        			  showClass: {
      				    popup: 'animate__animated animate__fadeInDown'
      				  },
     				  hideClass: {
     				    popup: 'animate__animated animate__fadeOutUp'
     				  },
        			  timer: 2000
        		}).then(() => {
        		    
        			 location.href = "<%= dashboardURL %>";
                });
        	}
        	
        	if(status == "failed"){
        		Swal.fire({
        			  icon: 'error',
        			  title: 'Oops...',
        			  text: 'Something went wrong please try again later.',
        			  showConfirmButton: false,
        			  showClass: {
      				    popup: 'animate__animated animate__fadeInDown'
      				  },
     				  hideClass: {
     				    popup: 'animate__animated animate__fadeOutUp'
     				  },
        			  timer: 2000
        		}).then(() => {
        			 location.href = "<%= dashboardURL %>";
                });
        	}
        	
        	if(status == "success"){
        		Swal.fire({
        			  icon: 'success',
        			  title: 'Great',
        			  text: 'File successfully created',
        			  showConfirmButton: false,
        			  showClass: {
      				    popup: 'animate__animated animate__fadeInDown'
      				  },
     				  hideClass: {
     				    popup: 'animate__animated animate__fadeOutUp'
     				  },
        			  timer: 2000
        		}).then(() => {
                    location.href = "<%= dashboardURL %>";
                });
        	}
        	
        	if(status == "update_success"){
        		Swal.fire({
        			  icon: 'success',
        			  title: 'Great',
        			  text: 'File Name successfully changed',
        			  showConfirmButton: false,
        			  showClass: {
      				    popup: 'animate__animated animate__fadeInDown'
      				  },
     				  hideClass: {
     				    popup: 'animate__animated animate__fadeOutUp'
     				  },
        			  timer: 2000
        		}).then(() => {
                    location.href = "<%= dashboardURL %>";
                });
        	}
        	
        	if(status == "star_success"){
        		Swal.fire({
        			  icon: 'success',
        			  title: 'Great',
        			  text: 'Starred successfully',
        			  showConfirmButton: false,
        			  showClass: {
      				    popup: 'animate__animated animate__fadeInDown'
      				  },
     				  hideClass: {
     				    popup: 'animate__animated animate__fadeOutUp'
     				  },
        			  timer: 2000
        		}).then(() => {
                    location.href = "<%= dashboardURL %>";
                });
        	}
        	
        	
        	if(status == "unstar_success"){
        		Swal.fire({
        			  icon: 'success',
        			  title: 'Great',
        			  text: 'Unstarred successfully',
        			  showConfirmButton: false,
        			  showClass: {
      				    popup: 'animate__animated animate__fadeInDown'
      				  },
     				  hideClass: {
     				    popup: 'animate__animated animate__fadeOutUp'
     				  },
        			  timer: 2000
        		}).then(() => {
                    location.href = "<%= dashboardURL %>";
                });
        	}
        	
        	if(status == "trash_success"){
        		Swal.fire({
        			  icon: 'success',
        			  title: 'Great',
        			  text: 'Trashed successfully',
        			  showConfirmButton: false,
        			  showClass: {
      				    popup: 'animate__animated animate__fadeInDown'
      				  },
     				  hideClass: {
     				    popup: 'animate__animated animate__fadeOutUp'
     				  },
        			  timer: 2000
        		}).then(() => {
                    location.href = "<%= dashboardURL %>";
                });
        	}
        	
        	if(status == "untrash_success"){
        		Swal.fire({
        			  icon: 'success',
        			  title: 'Great',
        			  text: 'Restored successfully',
        			  showConfirmButton: false,
        			  showClass: {
      				    popup: 'animate__animated animate__fadeInDown'
      				  },
     				  hideClass: {
     				    popup: 'animate__animated animate__fadeOutUp'
     				  },
        			  timer: 2000
        		}).then(() => {
                    location.href = "<%= dashboardURL %>";
                });
        	}
        	
        	
        	if(status == "delete_success"){
        		Swal.fire({
        			  icon: 'success',
        			  title: 'Great',
        			  text: 'File successfully deleted',
        			  showConfirmButton: false,
        			  showClass: {
      				    popup: 'animate__animated animate__fadeInDown'
      				  },
     				  hideClass: {
     				    popup: 'animate__animated animate__fadeOutUp'
     				  },
        			  timer: 2000
        		}).then(() => {
                    location.href = "<%= dashboardURL %>";
                });
        	}
      
        	
       	//copy file link
       	document.querySelector(".file_row").addEventListener("click", (event) => {
       		var copyText = event.target.querySelector("span");
       		
       		if(event.target.innerHTML == "Get Link" || copyText.innerHTML == "Get Link"){
       			
       			if(event.target.innerHTML == "Get Link" ){
       				var value = event.target.parentElement.href;
       			}else if(copyText.innerHTML == "Get Link"){
       				var value = event.target.href;
       			}
       			
       			const input = document.createElement('input');
           		input.value = value;
           		console.log(event.target.parentElement.href)
   				document.body.appendChild(input);
   				input.select();
   				document.execCommand('copy');
   			    document.body.removeChild(input);
       			
       		}
       		
       	});
        	
       	//download file
       	document.querySelector(".file_row").addEventListener("click", (event) => {
       		var download = event.target.querySelector("span");
       		
       		
       		if(event.target.innerHTML == "Download" || download.innerHTML == "Download" ){
       			if(event.target.innerHTML == "Download"){
       				var file_name = event.target.getAttribute('data-name');
       			}else if(download.innerHTML == "Download"){
       				var file_name = download.getAttribute('data-name');
       			}
       			//alert(file_name)
       			$.ajax({
   			      url: '/dms/download',
   			      timeout: 30000,
   			      method: 'POST',
   			      dataType: 'JSON',
   			      data : {
   			        filename: file_name,
   			      },
   			      success: function (response) {
   			        alert(response.message)
   			      },
   			      error: function(){
   			       
   			      }
   			  });
       			
       		}
       		
       		});
		    
	    })
	</script>
	<script type="text/javascript">
	
		 document.addEventListener("DOMContentLoaded", function(){
			 
			// make the preview modal show file type 
		    document.querySelector(".file_div").addEventListener("click", (event) => {
		    	
		    	var file_type = event.target.parentElement.parentElement.parentElement.parentElement.lastElementChild.previousElementSibling.previousElementSibling.previousElementSibling.value;
		    	//alert(file_type)
		    	if(file_type == 'image'){
		    		var image_link = event.target.parentElement.parentElement.parentElement.parentElement.lastElementChild.previousElementSibling.previousElementSibling.value;
		    		document.getElementById('preview_title').innerHTML = event.target.parentElement.parentElement.parentElement.parentElement.lastElementChild.previousElementSibling.value;  
		    		document.getElementById("preview_div").innerHTML = "<img src='"+image_link+"' width='90%' height='300'/>"
		    	}else{
			    	document.getElementById('preview_title').innerHTML = event.target.parentElement.parentElement.parentElement.parentElement.lastElementChild.previousElementSibling.value;  
			    	var iframe_link = event.target.parentElement.parentElement.parentElement.parentElement.lastElementChild.previousElementSibling.previousElementSibling.value;
			    	document.getElementById("preview_div").innerHTML = "<iframe src='"+iframe_link+"' width='100%' height='300' class=''  id='preview_iframe' loading='lazy' allowtransparency=true' allowfullscreen ></iframe>";
		    	}
		    })
			    
			    
		  // make value for renaming file show in form field
	      document.querySelector(".file_div").addEventListener("click", (event) => {
	    	//alert(event.target.nextElementSibling.value)
	    	document.getElementById('rename_file_modal_value').value = event.target.parentElement.parentElement.parentElement.parentElement.lastElementChild.previousElementSibling.value; 
	    	document.getElementById('file_id').value = event.target.nextElementSibling.value;
	     })
		    
	  	 //copy file link
         document.querySelector(".file_div").addEventListener("click", (event) => {
       		var copyText = event.target.querySelector("span");
       		
			if(event.target.innerHTML == "Get Link" || copyText.innerHTML == "Get Link"){
       			
       			if(event.target.innerHTML == "Get Link" ){
       				var value = event.target.parentElement.href;
       			}else if(copyText.innerHTML == "Get Link"){
       				var value = event.target.href;
       			}
       			
       			const input = document.createElement('input');
           		input.value = value;
           		console.log(event.target.parentElement.href)
   				document.body.appendChild(input);
   				input.select();
   				document.execCommand('copy');
   			    document.body.removeChild(input);
       			
       		}
			
       	});
			
	  	//download file
       	document.querySelector(".file_row").addEventListener("click", (event) => {
       		var download = event.target.querySelector("span");
       		
       		
      		if(event.target.innerHTML == "Download" || download.innerHTML == "Download" ){
      			
      			if(event.target.innerHTML == "Download"){
      				var file_name = event.target.getAttribute('data-name');
      			}else if(download.innerHTML == "Download"){
      				var file_name = download.getAttribute('data-name');
      			}
      			//alert(file_name)
     			$.ajax({
     			      url: '/dms/download',
     			      timeout: 30000,
     			      method: 'POST',
     			      dataType: 'JSON',
     			      data : {
     			        filename: file_name,
     			      },
     			      success: function (response) {
     			        alert(response.message)
     			      },
     			      error: function(){
     			       
     			      }
     			    });
       			
       			}
	        		
	        });
        	
			 
		 })
	
	</script>
	<script type="text/javascript">
	
	document.addEventListener("DOMContentLoaded", function(){
		//alert("I am here");
		let setting_status = document.querySelector("#status").value;
	
		if(setting_status == "settings_error"){
			Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'All Fields are compulsory ',
				  showConfirmButton: false,
				  showClass: {
					    popup: 'animate__animated animate__fadeInDown'
					  },
					  hideClass: {
					    popup: 'animate__animated animate__fadeOutUp'
					  },
				  timer: 2000
			}).then(() => {
			    
				 location.href = "<%= settingsURL %>";
	        });
		}
		
		if(setting_status == "settings_failed"){
    		Swal.fire({
    			  icon: 'error',
    			  title: 'Oops...',
    			  text: 'Something went wrong please try again later.',
    			  showConfirmButton: false,
    			  showClass: {
  				    popup: 'animate__animated animate__fadeInDown'
  				  },
 				  hideClass: {
 				    popup: 'animate__animated animate__fadeOutUp'
 				  },
    			  timer: 2000
    		}).then(() => {
    			 location.href = "<%= settingsURL %>";
            });
    	}
    	
    	if(setting_status == "settings_success"){
    		Swal.fire({
    			  icon: 'success',
   			  	  title: 'Great',
   			  	  text: 'Settings Updated Successfully',
    			  showConfirmButton: false,
    			  showClass: {
  				    popup: 'animate__animated animate__fadeInDown'
  				  },
 				  hideClass: {
 				    popup: 'animate__animated animate__fadeOutUp'
 				  },
    			  timer: 2000
    		}).then(() => {
                location.href = "<%= settingsURL %>";
            });
    	}
    	
    	if(setting_status == "deactivate_error"){
    		Swal.fire({
    			  icon: 'error',
  			  	  title: 'Oops...',
  			      text: 'Something went wrong please try again later.',
    			  showConfirmButton: false,
    			  showClass: {
  				    popup: 'animate__animated animate__fadeInDown'
  				  },
 				  hideClass: {
 				    popup: 'animate__animated animate__fadeOutUp'
 				  },
    			  timer: 2000
    		}).then(() => {
                location.href = "<%= settingsURL %>";
            });
    	}
    	
    	if(setting_status == "password_error"){
    		Swal.fire({
    			  icon: 'error',
  			  	  title: 'Oops...',
  			      text: "Password mismatch, make sure password and confirm password don't match",
    			  showConfirmButton: false,
    			  showClass: {
  				    popup: 'animate__animated animate__fadeInDown'
  				  },
 				  hideClass: {
 				    popup: 'animate__animated animate__fadeOutUp'
 				  },
    			  timer: 2500
    		}).then(() => {
                location.href = "<%= settingsURL %>";
            });
    	}
    	
    	if(setting_status == "password_success"){
    		Swal.fire({
    			  icon: 'success',
 			  	  title: 'Great',
 			  	  text: 'Password Updated Successfully',
    			  showConfirmButton: false,
    			  showClass: {
  				    popup: 'animate__animated animate__fadeInDown'
  				  },
 				  hideClass: {
 				    popup: 'animate__animated animate__fadeOutUp'
 				  },
    			  timer: 2000
    		}).then(() => {
                location.href = "<%= settingsURL %>";
            });
    	}
    	
    	if(setting_status == "password_failed"){
    		Swal.fire({
    			  icon: 'error',
  			  	  title: 'Oops...',
  			      text: 'Something went wrong please try again later.',
    			  showConfirmButton: false,
    			  showClass: {
  				    popup: 'animate__animated animate__fadeInDown'
  				  },
 				  hideClass: {
 				    popup: 'animate__animated animate__fadeOutUp'
 				  },
    			  timer: 2000
    		}).then(() => {
                location.href = "<%= settingsURL %>";
            });
    	}
	})
	</script>
    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
  </body>
</html>