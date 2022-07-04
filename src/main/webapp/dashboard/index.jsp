<%
	//get base url
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
              <div class="row">
                <div class="col-lg-8 mb-4 order-0">
                  <div class="card">
                    <div class="d-flex align-items-end row">
                      <div class="col-sm-7">
                        <div class="card-body">
                          <h5 class="card-title text-primary">Welcome ${sessionScope.firstname} ${sessionScope.lastname}! 👋</h5>
                          <p class="mb-4">
                            You have done <span class="fw-bold">72%</span> more sales today. Check your new badge in
                            your profile.
                          </p>

                          <!--  --><a href="javascript:;" class="btn btn-sm btn-outline-primary">View Badges</a>
                        </div>
                      </div>
                      <div class="col-sm-5 text-center text-sm-left">
                        <div class="card-body pb-0 px-0 px-md-4">
                          <img
                            src="../dashboard_assets/img/illustrations/man-with-laptop-light.png"
                            height="140"
                            alt="View Badge User"
                            data-app-dark-img="illustrations/man-with-laptop-dark.png"
                            data-app-light-img="illustrations/man-with-laptop-light.png"
                          />
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4 order-1">
                  <div class="row">
                      <div class="col-lg-12 col-md-12 col-12 mb-4">
                      <div class="card">
                        <div class="card-body">
                          <div class="card-title d-flex align-items-start justify-content-between">
                            <div id="growthChart" class=""></div>
                          </div>
                          <div class="text-center fw-semibold pt-3 mb-2">62% Space Available</div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- Total Revenue -->
                <div class="col-12 col-lg-12 order-2 order-md-3 order-lg-2 mb-4">
                  <div class="row">
                    <div class="col-lg-2 col-md-3 col-4 mb-4">
                      <div class="card" style='background-color:#e7e7ff;'>
                        <div class="card-body">
                          <div class="card-title d-flex align-items-start justify-content-between">
                            <div class="avatar flex-shrink-0">
                               <i class='bx bxs-folder-open' style="font-size: 40px; color:#8789ff"></i>
                              <!-- <img src="../dashboard_assets/img/icons/unicons/paypal.png" alt="Credit Card" class="rounded" />-->
                            </div>
                          </div>
                          <span class="d-block mb-1">Documents</span>
                        </div>
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-3 col-4 mb-4">
                      <div class="card" style='background-color:#e7e7ff;'>
                        <div class="card-body">
                          <div class="card-title d-flex align-items-start justify-content-between">
                            <div class="avatar flex-shrink-0">
                            <i class='bx bxs-videos' style="font-size: 40px; color:#8789ff"></i>
                               <!-- <img src="../dashboard_assets/img/icons/unicons/paypal.png" alt="Credit Card" class="rounded" />-->
                            </div>
                          </div>
                          <span class="d-block mb-1">Videos</span>
                        </div>
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-3 col-4 mb-4">
                      <div class="card" style='background-color:#e7e7ff;'>
                        <div class="card-body">
                          <div class="card-title d-flex align-items-start justify-content-between">
                            <div class="avatar flex-shrink-0">
                            	<i class='bx bxs-music' style="font-size: 40px; color:#8789ff"></i>
                                <!-- <img src="../dashboard_assets/img/icons/unicons/paypal.png" alt="Credit Card" class="rounded" />-->
                            </div>
                          </div>
                          <span class="d-block mb-1">Audios</span>
                        </div>
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-3 col-4 mb-4">
                      <div class="card" style='background-color:#e7e7ff;'>
                        <div class="card-body">
                          <div class="card-title d-flex align-items-start justify-content-between">
                            <div class="avatar flex-shrink-0">
                              <i class='bx bx-images' style="font-size: 40px; color:#8789ff"></i>
                              <!-- <img src="../dashboard_assets/img/icons/unicons/paypal.png" alt="Credit Card" class="rounded" />-->
                            </div>
                          </div>
                          <span class="d-block mb-1">Images</span>
                        </div>
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-3 col-4 mb-4">
                      <div class="card" style='background-color:#e7e7ff;'>
                        <div class="card-body">
                          <div class="card-title d-flex align-items-start justify-content-between">
                            <div class="avatar flex-shrink-0">
                            	<i class='bx bxs-file-blank' style="font-size: 40px; color:#8789ff"></i>
                                <!-- <img src="../dashboard_assets/img/icons/unicons/paypal.png" alt="Credit Card" class="rounded" />-->
                            </div>
                          </div>
                          <span class="d-block mb-1">Others</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <!--/ Total Revenue -->
                
              </div>
              <div class="row">
              	<h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Folders</span></h4>
              	<div class="col-lg-2 col-md-3 col-6 mb-3">
              		<a href=""><i class='bx bxs-star'></i><i class='bx bxs-folder' style='color:#8588ff; font-size: 155px;'></i></a>
              		<button class="btn" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              			<span class="me-1">File Name </span><i class="bx bx-dots-vertical-rounded"></i>
					</button>
              		<div class="dropdown-menu" aria-labelledby="cardOpt6">
	                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-folder-open'></i> Open</a>
	                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-star'></i> Add to Starred</a>
	                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-edit-alt'></i> Rename</a>
	                  <hr>
	                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-trash'></i> Delete</a>
	                </div>
              	</div>
              	<div class="col-lg-2 col-md-3 col-6 mb-3">
              		<a href=""><i class='bx bxs-star'></i><i class='bx bxs-folder' style='color:#8588ff; font-size: 155px;'></i></a>
              		<button class="btn" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              			<span class="me-1" >File Name </span><i class="bx bx-dots-vertical-rounded"></i>
					</button>
              		<div class="dropdown-menu" aria-labelledby="cardOpt6">
	                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-folder-open'></i> Open</a>
	                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-star'></i> Add to Starred</a>
	                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-edit-alt'></i> Rename</a>
	                  <hr>
	                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-trash'></i> Delete</a>
	                </div>
              	</div>
              	<div class="col-lg-2 col-md-3 col-6 mb-3">
              		<a href=""><i class='bx bxs-star' style='color:#F5F5F9;'></i><i class='bx bxs-folder' style='color:#8588ff; font-size: 155px;'></i></a>
              		<button class="btn" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              			<span class="me-1">File Name </span><i class="bx bx-dots-vertical-rounded"></i>
					</button>
              		<div class="dropdown-menu" aria-labelledby="cardOpt6">
	                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-folder-open'></i> Open</a>
	                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-star'></i> Add to Starred</a>
	                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-edit-alt'></i> Rename</a>
	                  <hr>
	                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-trash'></i> Delete</a>
	                </div>
              	</div>
              </div>
              <div class="row">
              	<h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Files</span></h4>
                <div class="col-lg-2 col-md-3 col-6 mb-3">
			        <div class="card">
			          <div class="card-body">
			            <div class="card-title d-flex align-items-start justify-content-between">
			              <div class="avatar flex-shrink-0">
			                <img src="../dashboard_assets/img/icons/unicons/wallet-info.png" alt="Credit Card" class="rounded">
			              </div>
			              <div class="dropdown">
			                <button class="btn p-0" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                  <i class="bx bx-dots-vertical-rounded"></i>
			                </button>
			                <div class="dropdown-menu" aria-labelledby="cardOpt6">
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-show'></i> Preview</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-star'></i> Add to Starred</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-link-alt'></i> Get Link</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-cloud-download'></i> Download</a>
			                  <hr>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-trash'></i> Delete</a>
			                </div>
			              </div>
			            </div>
			            <span class="d-block">Sales</span>
			            <h4 class="card-title mb-1">$4,679</h4>
			            <small class="text-success fw-semibold">File name</small>
			          </div>
			        </div>
			      </div>
			      <div class="col-lg-2 col-md-3 col-6 mb-3">
			        <div class="card">
			          <div class="card-body">
			            <div class="card-title d-flex align-items-start justify-content-between">
			              <div class="avatar flex-shrink-0">
			                <img src="../dashboard_assets/img/icons/unicons/wallet-info.png" alt="Credit Card" class="rounded">
			              </div>
			              <div class="dropdown">
			                <button class="btn p-0" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                  <i class="bx bx-dots-vertical-rounded"></i>
			                </button>
			                <div class="dropdown-menu" aria-labelledby="cardOpt6">
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-show'></i> Preview</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-star'></i> Add to Starred</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-link-alt'></i> Get Link</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-cloud-download'></i> Download</a>
			                  <hr>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-trash'></i> Delete</a>
			                </div>
			              </div>
			            </div>
			            <span class="d-block">Sales</span>
			            <h4 class="card-title mb-1">$4,679</h4>
			            <small class="text-success fw-semibold">File name</small>
			          </div>
			        </div>
			      </div>
			     <div class="col-lg-2 col-md-3 col-6 mb-3">
			        <div class="card">
			          <div class="card-body">
			            <div class="card-title d-flex align-items-start justify-content-between">
			              <div class="avatar flex-shrink-0">
			                <img src="../dashboard_assets/img/icons/unicons/wallet-info.png" alt="Credit Card" class="rounded">
			              </div>
			              <div class="dropdown">
			                <button class="btn p-0" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                  <i class="bx bx-dots-vertical-rounded"></i>
			                </button>
			                <div class="dropdown-menu" aria-labelledby="cardOpt6">
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-show'></i> Preview</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-star'></i> Add to Starred</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-link-alt'></i> Get Link</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-cloud-download'></i> Download</a>
			                  <hr>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-trash'></i> Delete</a>
			                </div>
			              </div>
			            </div>
			            <span class="d-block">Sales</span>
			            <h4 class="card-title mb-1">$4,679</h4>
			            <small class="text-success fw-semibold">File name</small>
			          </div>
			        </div>
			      </div>
			      <div class="col-lg-2 col-md-3 col-6 mb-3">
			        <div class="card">
			          <div class="card-body">
			            <div class="card-title d-flex align-items-start justify-content-between">
			              <div class="avatar flex-shrink-0">
			                <img src="../dashboard_assets/img/icons/unicons/wallet-info.png" alt="Credit Card" class="rounded">
			              </div>
			              <div class="dropdown">
			                <button class="btn p-0" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                  <i class="bx bx-dots-vertical-rounded"></i>
			                </button>
			                <div class="dropdown-menu" aria-labelledby="cardOpt6">
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-show'></i> Preview</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-star'></i> Add to Starred</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-link-alt'></i> Get Link</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-cloud-download'></i> Download</a>
			                  <hr>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-trash'></i> Delete</a>
			                </div>
			              </div>
			            </div>
			            <span class="d-block">Sales</span>
			            <h4 class="card-title mb-1">$4,679</h4>
			            <small class="text-success fw-semibold">File name</small>
			          </div>
			        </div>
			      </div>
			      <div class="col-lg-2 col-md-3 col-6 mb-3">
			        <div class="card">
			          <div class="card-body">
			            <div class="card-title d-flex align-items-start justify-content-between">
			              <div class="avatar flex-shrink-0">
			                <img src="../dashboard_assets/img/icons/unicons/wallet-info.png" alt="Credit Card" class="rounded">
			              </div>
			              <div class="dropdown">
			                <button class="btn p-0" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                  <i class="bx bx-dots-vertical-rounded"></i>
			                </button>
			                <div class="dropdown-menu" aria-labelledby="cardOpt6">
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-show'></i> Preview</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-star'></i> Add to Starred</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-link-alt'></i> Get Link</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-cloud-download'></i> Download</a>
			                  <hr>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-trash'></i> Delete</a>
			                </div>
			              </div>
			            </div>
			            <span class="d-block">Sales</span>
			            <h4 class="card-title mb-1">$4,679</h4>
			            <small class="text-success fw-semibold">File name</small>
			          </div>
			        </div>
			      </div>
			      <div class="col-lg-2 col-md-3 col-6 mb-3">
			        <div class="card">
			          <div class="card-body">
			            <div class="card-title d-flex align-items-start justify-content-between">
			              <div class="avatar flex-shrink-0">
			                <img src="../dashboard_assets/img/icons/unicons/wallet-info.png" alt="Credit Card" class="rounded">
			              </div>
			              <div class="dropdown">
			                <button class="btn p-0" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                  <i class="bx bx-dots-vertical-rounded"></i>
			                </button>
			                <div class="dropdown-menu" aria-labelledby="cardOpt6">
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-show'></i> Preview</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-star'></i> Add to Starred</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-link-alt'></i> Get Link</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-cloud-download'></i> Download</a>
			                  <hr>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-trash'></i> Delete</a>
			                </div>
			              </div>
			            </div>
			            <span class="d-block">Sales</span>
			            <h4 class="card-title mb-1">$4,679</h4>
			            <small class="text-success fw-semibold">File name</small>
			          </div>
			        </div>
			      </div>
			      <div class="col-lg-2 col-md-3 col-6 mb-3 mb-md-4">
			        <div class="card">
			          <div class="card-body">
			            <div class="card-title d-flex align-items-start justify-content-between">
			              <div class="avatar flex-shrink-0">
			                <img src="../dashboard_assets/img/icons/unicons/wallet-info.png" alt="Credit Card" class="rounded">
			              </div>
			              <div class="dropdown">
			                <button class="btn p-0" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                  <i class="bx bx-dots-vertical-rounded"></i>
			                </button>
			                <div class="dropdown-menu" aria-labelledby="cardOpt6">
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-show'></i> Preview</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-star'></i> Add to Starred</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-link-alt'></i> Get Link</a>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-cloud-download'></i> Download</a>
			                  <hr>
			                  <a class="dropdown-item" href="javascript:void(0);"><i class='bx bx-trash'></i> Delete</a>
			                </div>
			              </div>
			            </div>
			            <span class="d-block">Sales</span>
			            <h4 class="card-title mb-1">$4,679</h4>
			            <small class="text-success fw-semibold">File name</small>
			          </div>
			        </div>
			      </div>
                  
              </div>
            </div>
            <!-- / Content -->
            
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
