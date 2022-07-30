package com.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class TempUploadControllerServlet
 */
@WebServlet(name ="TempUploadControllerServlet",asyncSupported = true,  urlPatterns = {"/upload"})
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)	
public class TempUploadControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempUploadControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	
    	 String url = request.getRequestURL().toString();
    	 String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
    	 response.sendRedirect(baseURL + "dashboard");
    	 
    	 /*ServletContext servletContext = getServletContext();
  		String contextPath = servletContext.getRealPath("/"+"file_upload" +File.separator);
  		PrintWriter out = response.getWriter();
  		out.println("<br/>File system context path (in TestServlet): " + contextPath);*/
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Step 1: set the content type
		response.setContentType("text/html");
		
		//Step 2: get the printwriter
		PrintWriter out = response.getWriter();
					
		// Create parh components to save the file
		Part filePart = request.getPart("uploaded_file");
		
		//fileName will be the displayname
		String fileName = filePart.getSubmittedFileName();
		
		// Access session
		HttpSession session = request.getSession(true);
		
		//get user id
		int userId = (int) session.getAttribute("id");
		
		//get folder id
		int folder_id = Integer.parseInt(request.getParameter("folder_id")); 
		
		//generate random characters
		String randomChars = generateRandomChars(
	            "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 17);
		
		//get file path
		Path filePath = Paths.get(getServletContext().getRealPath("/"+"file_upload" +File.separator + fileName));
		
		File file = new File(getServletContext().getRealPath("/"+"file_upload" +File.separator + fileName));
		
		//get file type
		String fileType = Files.probeContentType(file.toPath().toAbsolutePath());
		
		//get file extension
		String fileExtension  = fileName.substring(fileName.lastIndexOf("."));
		
		//get file size
		int fileSize = (int) filePart.getSize();
		
		//get file new name
		String fileNewName  = randomChars+fileExtension;
		
		//get timestamp
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		String currentTimeStamp = Integer.toString((int) timestamp.getTime());
		
		//get file hash
		String fileHash =  currentTimeStamp+generateRandomChars(
	            "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 10);
		
		//Encrypt files here before upload
		//
		
		for (Part part : request.getParts()) {
		      part.write(getServletContext().getRealPath("/"+"file_upload" +File.separator + fileName));
		    }
				
		//Insert file data into the database
		
	   
		
		/*long fileSize = filePart.getSize();
	     
	    String  path = getServletContext().getRealPath("/"+"file_upload" +File.separator + fileName);
	    
	    InputStream inputStream = filePart.getInputStream();
	    
	    boolean upload_status = uploadFile(request, path);*/
	    
	    boolean upload_status = true;
	    
	    if(upload_status) {
	    	out.println("File Upload to this directory: "+fileName+' ' + fileType + ' '+fileExtension+' ' + fileSize + ' ' + fileNewName + ' '+ currentTimeStamp + ' '+fileHash);
	    }else {
	    	out.println("error");
	    }
		   
	}
	
	public String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        sb.append(candidateChars.charAt(random.nextInt(candidateChars
	                .length())));
	    }

	    return sb.toString();
	}
	
	public boolean uploadFile(HttpServletRequest request, String fileName) {
		boolean upload_status = false;
		
		try {
			
			
			
			upload_status = true;
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		return upload_status;
	} 
	
	
	public boolean uploadFileDumped(InputStream inputStream, String path) {
		boolean upload_status = false;
		
		try {
			
			byte[] new_byte = new byte[inputStream.available()];
			
			inputStream.read();
			
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			
			fileOutputStream.write(new_byte);
			fileOutputStream.flush();
			fileOutputStream.close();
			
			upload_status = true;
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		return upload_status;
	} 

}
