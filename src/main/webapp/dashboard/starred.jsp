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
					<div class="row folder_row">
						
						<!-- If starred is empty show this -->
						<c:if test="${fn:length(starred_folder_list) < 1}">
	           				<div class="col-lg-12">
	           					<div class="d-flex justify-content-center mt-md-5 mt-5">
	           						<img src="../dashboard_assets/img/icons/unicons/bookmark.png" width="" height=""><br>
	           					</div>
	           					<h3 class="text-center mt-md-4 mt-4">No Starred Item Yet</h3>
							</div>
						</c:if>
			  	 
				  	 	<!-- else show this -->
				  	 	<h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Folders</span></h4>
	              	<c:forEach var="folderItem" items="${starred_folder_list}">
						<!-- <div>
							<p>${folderItem.folderName}</p>
							<p>${folderItem.folderId}</p>
							<p>${folderItem.trashStatus}</p>folderName
						</div> -->
					
		              	<div class="col-lg-2 col-md-3 col-6 mb-3">
		              		<a href="folder?folder=${folderItem.id}&parent_folder=${folderItem.folderId}"><c:if test="${folderItem.favStatus == 1}" ><i class='bx bxs-star'></i></c:if><c:if test="${folderItem.favStatus != 1}" ><i class='bx bxs-star' style="color: #F5F5F9;"></i></c:if><i class='bx bxs-folder' style='color:#8588ff; font-size: 155px;'></i></a>
		              		<button class="btn" type="button" id="cardOpt6" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		              			<span class="me-xxl-1  file_name" >${folderItem.folderName}  </span><i class="bx bx-dots-vertical-rounded"></i>
							</button>
		              		<div class="dropdown-menu" aria-labelledby="cardOpt6">
			                  <a class="dropdown-item" href="folder?folder=${folderItem.id}&parent_folder=${folderItem.folderId}"><i class='bx bx-folder-open'></i> Open</a>
			                  <!-- If starred show starred else show unstar -->
			                  <c:if test="${folderItem.favStatus == 1}" ><a class="dropdown-item" href="route_folder?command=UNSTARRED&folder_id=${folderItem.id}"><i class='bx bx-star'></i> Remove Starred</a></c:if>
			                  <c:if test="${folderItem.favStatus != 1}" ><a class="dropdown-item" href="route_folder?command=STARRED&folder_id=${folderItem.id}"><i class='bx bx-star'></i> Add to Starred</a></c:if>
			                  <a class="dropdown-item rename_link" href="#" data-bs-toggle="modal" data-bs-target="#rename_modal"><i class='bx bx-edit-alt'></i> Rename</a>
			                  <input type="hidden" class="folder_id" value="${folderItem.id}">
			                  <hr>
			                  <a class="dropdown-item" href="route_folder?command=TRASH&folder_id=${folderItem.id}"><i class='bx bx-trash'></i> Delete</a>
			                </div>
		              	</div>
	              	</c:forEach>
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