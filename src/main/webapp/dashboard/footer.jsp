
    
    <div class="dropdown">
       <button class="btn p-0 buy-now" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <div class=" btn btn-primary btn-buy-now" data-bs-toggle="modal" data-bs-target="#uploadModal">
	       Upload
	    </div>
       </button>
       <div class="dropdown-menu" aria-labelledby="cardOpt6">
         <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-show'></i> Preview</a>
         <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-star'></i> Add to Starred</a>
         <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-link-alt'></i> Get Link</a>
       </div>
     </div>

    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src="../dashboard_assets/vendor/libs/jquery/jquery.js"></script>
    <script src="../dashboard_assets/vendor/libs/popper/popper.js"></script>
    <script src="../dashboard_assets/vendor/js/bootstrap.js"></script>
    <script src="../dashboard_assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="../dashboard_assets/js/sweetalert2.min.js"></script>

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
	    	let base_url = document.getElementById("base_url").value
	    	console.log(base_url);
		 	// Get a reference to the file input element
		    const inputElement = document.querySelector('input[type="file"]');
		
		    // Create a FilePond instance
		    const pond = FilePond.create(
		    		inputElement 
		    		
		    		
		    );
		    
		    pond.setOptions({
		    		server: {
		    			url: base_url + "/upload",
		    			method : "POST",
		    		}
		    });
		    
		    pond.on('addfile', (error, file) => {
		        if (error) {
		            console.log('Oh no');
		            return;
		        }
			
		        console.log('File added', file);
		    });
		    
		    
		    document.querySelector(".folder_row").addEventListener("click", (event) => {
		    	document.getElementById('rename_modal_value').value = event.target.parentElement.previousElementSibling.firstElementChild.innerHTML; 
		    	document.getElementById('folder_id').value = event.target.nextElementSibling.value;
		    })
			
		    <%

			// get dashboard url
			String url = request.getRequestURL().toString();
			String dashboardURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/dashboard/";
			
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
        			  text: 'Trash successfully',
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
        			  text: 'Untrash successfully',
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
		    
	    })
	</script>

    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
  </body>
</html>