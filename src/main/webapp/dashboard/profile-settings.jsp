<%

	// get base url
	String url = request.getRequestURL().toString();
	String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
	if(session.getAttribute("firstname") == null){
	    response.sendRedirect(baseURL + "auth/login.jsp");
	}
	
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>

  <!-- Header -->
  <jsp:include page="header.jsp"></jsp:include>
  
	<!-- Layout wrapper -->
    <div class="layout-wrapper layout-content-navbar">
      <div class="layout-container">
      
        <!-- Menu -->
       
        <jsp:include page="sidebar.jsp"></jsp:include>
        
        <!-- / Menu -->
        
         <!-- Modal -->
       
        <jsp:include page="modal.jsp"></jsp:include>
        
        <!-- / Modal -->
        
        <!-- Layout container -->
        <div class="layout-page">
        
          <!-- Navbar -->

         <jsp:include page="navbar.jsp"></jsp:include>

          <!-- / Navbar -->

          <!-- Content wrapper -->
          <div class="content-wrapper">
            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
              <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Account Settings </span> </h4>

              <div class="row">
                <div class="col-md-12">
                  
                  <div class="card mb-4">
                    <h5 class="card-header">Profile Details</h5>
                    <!-- Account -->
                    <div class="card-body">
                      <div class="d-flex align-items-start align-items-sm-center gap-4">
                        <img
                          src="../dashboard_assets/img/avatars/avatar1.png"
                          alt="user-avatar"
                          class="d-block rounded"
                          height="100"
                          width="100"
                          id="uploadedAvatar"
                        />
                        <div class="button-wrapper">
                          <label for="upload" class="btn btn-primary me-2 mb-4" tabindex="0">
                            <span class="d-none d-sm-block">Upload new photo</span>
                            <i class="bx bx-upload d-block d-sm-none"></i>
                            <input
                              type="file"
                              id="upload"
                              class="account-file-input"
                              name="profile_image"
                              hidden
                              accept="image/png, image/jpeg, image/jpg"
                            />
                          </label>
                          <!--  <button type="button" class="btn btn-outline-secondary account-image-reset mb-4">
                            <i class="bx bx-reset d-block d-sm-none"></i>
                            <span class="d-none d-sm-block">Reset</span>
                          </button>-->

                          <p class="text-muted mb-0">Allowed JPG, GIF or PNG. Max size of 800K</p>
                        </div>
                      </div>
                    </div>
                    <hr class="my-0" />
                    <div class="card-body">
                      <form id="formAccountSettings" action="update_settings" method="POST">
                      	<input type="hidden" name="form_type" value="settings" />
                        <div class="row">
                          <div class="mb-3 col-md-6">
                            <label for="firstName" class="form-label">First Name</label>
                            <input
                              class="form-control"
                              type="text"
                              id="firstName"
                              name="first_name" 
                              value="${user_info.firstname}"
                              autofocus
                              required
                            />
                          </div>
                          <div class="mb-3 col-md-6">
                            <label for="lastName" class="form-label">Last Name</label>
                            <input class="form-control" type="text" name="last_name" id="lastName" value="${user_info.lastname}" required/>
                          </div>
                          <div class="mb-3 col-md-6">
                            <label for="email" class="form-label">E-mail</label>
                            <input
                              class="form-control"
                              type="text"
                              id="email"
                              name="email"
                              value="${user_info.email}"
                              placeholder="john.doe@example.com"
                              required
                            />
                          </div>
                          <!--  <div class="mb-3 col-md-6">
                            <label for="organization" class="form-label">Organization</label>
                            <input
                              type="text"
                              class="form-control"
                              id="organization"
                              name="organization"
                              value="ThemeSelection"
                            />
                          </div>-->
                        </div>
                        <div class="mt-2">
                          <button type="submit" class="btn btn-primary me-2">Save changes</button>
                          <!-- <button type="reset" class="btn btn-outline-secondary">Cancel</button> -->
                        </div>
                      </form>
                    </div>
                    <!-- /Account -->
                  </div>
                  <div class="card mb-4">
                    <h5 class="card-header">Password Update</h5>
                    <div class="card-body">
                      <form id="password_update" action="password" method="POST">
                      <input type="hidden" name="form_type" value="password" />
                        <div class="row">
                          <div class="mb-3 col-md-6">
                            <label for="password" class="form-label">New Password</label>
                            
                            <div class="input-group input-group-merge">
                            	<input
	                              class="form-control"
	                              type="password"
	                              id="password"
	                              name="password"
	                              aria-describedby="password"
	                              required
	                            />
	                            <span class="input-group-text cursor-pointer">
	                            <i class="bx bx-hide" 
	                            onclick="
	                            	
	                            	if(this.parentElement.previousElementSibling.type == 'password'){
	                            		this.classList.remove('bx-hide')
	                            		this.classList.add('bx-show')
	                            		this.parentElement.previousElementSibling.type = 'text'
	                            		//alert(this.parentElement.previousElementSibling.type)
	                            	}else{
	                            		this.classList.remove('bx-show')
	                            		this.classList.add('bx-hide')
	                            		this.parentElement.previousElementSibling.type = 'password'
	                            	}
	                            "
	                            ></i></span>
                            </div>
                          </div>
                          <div class="mb-3 col-md-6">
                            <label for="confirm_password" class="form-label">Confirm New Password</label>
                            <div class="input-group input-group-merge">
                            	<input class="form-control" type="password" name="confirm_password" id="confirm_password"  aria-describedby="confirm_password" required/>
                            	<span class="input-group-text cursor-pointer">
                            	<i class="bx bx-hide" 
                            	onclick="
                            	
                            		if(this.parentElement.previousElementSibling.type == 'password'){
	                            		this.classList.remove('bx-hide')
	                            		this.classList.add('bx-show')
	                            		this.parentElement.previousElementSibling.type = 'text'
	                            	}else{
	                            		this.classList.remove('bx-show')
	                            		this.classList.add('bx-hide')
	                            		this.parentElement.previousElementSibling.type = 'password'
	                            	}	
                            	
	                            "
	                            ></i></span>
                            </div>
                          </div>
                        </div>
                        <button type="submit" id="" class="btn btn-primary" >Update</button>
                      </form>
                    </div>
                  </div>
                  <div class="card">
                    <h5 class="card-header">Deactivation Account</h5>
                    <div class="card-body">
                      <div class="mb-3 col-12 mb-0">
                        <div class="alert alert-warning">
                          <h6 class="alert-heading fw-bold mb-1">Are you sure you want to deactivate your account?</h6>
                          <p class="mb-0">Once you deactivate your account, it can only be reactivated by the admin. Please be certain.</p>
                        </div>
                      </div>
                      <form id="formAccountDeactivation" action="deactivate" method="POST">
                       <input type="hidden" name="form_type" value="deactivate" />
                        <div class="form-check mb-3">
                          <input
                            class="form-check-input"
                            type="checkbox"
                            name="accountActivation"
                            id="accountActivation"
                            onclick="
                            if(this.checked){
                            	document.getElementById('deactivate-btn').classList.remove('disabled')
                           	}else{
                           		document.getElementById('deactivate-btn').classList.add('disabled')
                           	}
                          "
                          />
                          <label class="form-check-label" for="accountActivation"
                            >I confirm my account deactivation</label
                          >
                        </div>
                        <button type="submit" id="deactivate-btn" class="btn btn-danger deactivate-account disabled" >Deactivate Account</button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- / Content -->
            
            <!-- Modal for renaming folder-->
       
        		<jsp:include page="rename_modal.jsp"></jsp:include>
        
       		 <!-- / Modal for renaming folder -->
       		 
       		 
       		 
             <!-- Modal for uploading files -->
       
        		<jsp:include page="upload_modal.jsp"></jsp:include>
        
       		 <!-- / Modal for uploading files -->
       		 
       		 <!-- Modal for previewing files -->
       
        		<jsp:include page="preview_modal.jsp"></jsp:include>
        
       		 <!-- / Modal for previewing files -->

			<input type="hidden" id="status" value="<%= request.getParameter("status") %>" />
			
            <!-- Footer -->
            	<jsp:include page="footnav.jsp"></jsp:include>
            <!-- / Footer -->

            <div class="content-backdrop fade"></div>
          </div>
          <!-- Content wrapper -->
        </div>
        <!-- / Layout page -->
      </div>

      <!-- Overlay -->
      <div class="layout-overlay layout-menu-toggle"></div>
    </div>
     <!-- / Layout wrapper -->
     
     <!-- Footer -->
     <jsp:include page="footer.jsp"></jsp:include>