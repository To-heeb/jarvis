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
					<div class="row">
						<div class="col-lg-12">
							<div class="row justify-content-between mb-2">
							    <div class="col-10">
							      	<h4>Images</h4>
							    </div>
							    <div class="col-2">
							      <a href="javascript:history.back()"><i class='bx bxs-arrow-from-right' style="font-size: 30px; color:#8789ff"></i></a>
							    </div>
						  	</div>
						</div>
						<hr>
						
						<!-- If images is empty show this -->
						<div class="col-lg-12">
           					<div class="d-flex justify-content-center mt-md-5 mt-5">
           						<img src="../dashboard_assets/img/icons/unicons/bookmark.png" width="" height=""><br>
           					</div>
           					<h3 class="text-center mt-md-4 mt-4">No Image Yet</h3>
						</div>
				  	 
				  	 	<!-- else show this -->
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