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
							      	<h4>Documents</h4>
							    </div>
							    <div class="col-2">
							      <a href="javascript:history.back()"><i class='bx bxs-arrow-from-right' style="font-size: 30px; color:#8789ff"></i></a>
							    </div>
						  	</div>
						</div>
						<hr>
						
						<!-- If documents is empty show this -->
						<c:if test="${fn:length(documents) < 1}">
							<div class="col-lg-12">
	           					<div class="d-flex justify-content-center mt-md-5 mt-5">
	           						<img src="../dashboard_assets/img/icons/unicons/google-docs.png" width="" height=""><br>
	           					</div>
	           					<h3 class="text-center mt-md-4 mt-4">No Document Yet</h3>
							</div>
						</c:if>
						
				  	 
				  	 	<!-- else show this -->
				  	 	<div class="row file_div">
					  	 	<!--<c:if test="${fn:length(documents) > 1}"><h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Documents</span></h4></c:if>-->
					  	 	<c:forEach var="documentItem" items="${documents}">
							  	 <div class="col-lg-2 col-md-3 col-6 mb-3">
							        <div class="card">
							          <div class="card-body">
							            <div class="card-title d-flex align-items-start justify-content-between" style="margin-bottom: 0px !important;">
							               <div class="flex-shrink-0">
							                 <c:if test="${documentItem.favStatus == 1}" ><i class='bx bxs-star' style="color: #8588FF;"></i></c:if><c:if test="${documentItem.favStatus != 1}" ><i class='bx bxs-star' style="color: #FFFFFF;"></i></c:if>
							              </div>
							              <div class="dropdown mb-1">
							                <button class="btn p-0" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							                  <i class="bx bx-dots-vertical-rounded"></i>
							                </button>
							                <div class="dropdown-menu" aria-labelledby="cardOpt6">
							                  <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#preview_modal"><i class='bx bx-show'></i> Preview</a>
							                  <c:if test="${documentItem.favStatus == 1}" ><a class="dropdown-item" href="route_file?command=UNSTARRED&file_id=${documentItem.id}"><i class='bx bx-star'></i> Remove Starred</a></c:if>
					                  		  <c:if test="${documentItem.favStatus != 1}" ><a class="dropdown-item" href="route_file?command=STARRED&file_id=${documentItem.id}"><i class='bx bx-star'></i> Add to Starred</a></c:if>
							                  <a class="dropdown-item getlink" href="${documentItem.filePath}"><i class='bx bx-link-alt'></i> <span>Get Link</span></a>
							                  <a class="dropdown-item rename_link" href="#" data-bs-toggle="modal" data-bs-target="#rename_file_modal"><i class='bx bx-edit-alt'></i> Rename</a>
							                  <input type="hidden" class="file_id" value="${documentItem.id}">
							                  <a class="dropdown-item" href="${documentItem.filePath}" download="${documentItem.displayName}"><i class='bx bx-cloud-download' ></i> Download</a>
							                  <hr>
							                  <a class="dropdown-item" href="route_file?command=TRASH&file_id=${documentItem.id}"><i class='bx bx-trash'></i> Delete</a>
							                </div>
							              </div>
							            </div>
								            <!-- <span class="d-block">Sales</span>
								            <h4 class="card-title mb-1">$4,679</h4> -->
			
							              <c:set var="mimeTypePdf" value="${{'application/pdf', 'application/x-pdf', 'application/x-gzpdf', 'applications/vnd.pdf', 'application/acrobat', 'application/x-google-chrome-pdf', 'text/pdf', 'text/x-pdf'}}"  scope="application" />
							              <c:choose>
									         <c:when test = "${fn:contains( mimeTypePdf, documentItem.fileType )}">
									            <span><a href="<%= baseURL+"file_upload/"%>${documentItem.newName}" target="_blank"><img src="../dashboard_assets/img/demo/pdf-file.png" class="w-100 mb-1 border border-primary p-3 border-2 rounded" width="" height="100px"></a></span>
									         </c:when>
									         
									         <c:when test = "${fileItem.fileType == 'application/x-httpd-php' }">
									            <span><a href="<%= baseURL+"file_upload/"%>${documentItem.newName}" target="_blank"><img src="../dashboard_assets/img/demo/php.png" class="w-100 mb-1 border border-primary p-3 border-2 rounded" width="" height="100px"></a></span>
									         </c:when>
									         
									         <c:when test = "${salary > 1000}">
									            Salary is very good.
									         </c:when>
									         
									         <c:when test = "${salary > 1000}">
									            Salary is very good.
									         </c:when>
									         
									         <c:when test = "${salary > 1000}">
									            Salary is very good.
									         </c:when>
									         
									         <c:when test = "${salary > 1000}">
									            Salary is very good.
									         </c:when>
									         
									         <c:when test = "${salary > 1000}">
									            Salary is very good.
									         </c:when>
									         
									         <c:otherwise>
									            <span><a href="<%= baseURL+"file_upload/"%>${documentItem.newName}" target="_blank"><img src="../dashboard_assets/img/demo/files.png" class="w-100 mb-1 border border-primary p-3 border-2 rounded" width="" height="100px"></a></span>
									         </c:otherwise>
									      </c:choose>
									      <input type="hidden" class="file_type" value="${documentItem.fileCategory}"/>
							            <input type="hidden" class="file_link" value="<%= baseURL+"file_upload/"%>${documentItem.newName}"/>
							            <small class="text-success fw-semibold">${documentItem.displayName}</small>
							          </div>
							        </div>
							      </div>	
					  	 	</c:forEach>
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
     