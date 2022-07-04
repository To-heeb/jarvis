package com.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class TempUploadControllerServlet
 */
@WebServlet(name ="TempUploadControllerServlet",asyncSupported = true,  urlPatterns = {"/upload"})
@MultipartConfig
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
				
				
		// Create path components to save the file
		Part filePart = request.getPart("filepond");
		
		String fileName = filePart.getSubmittedFileName();
		
		long fileSize = filePart.getSize();
	     
	    String  path = getServletContext().getRealPath("/"+"file_upload" +File.separator + fileName);
	    
	    InputStream inputStream = filePart.getInputStream();
	    
	    boolean upload_status = uploadFile(inputStream, path);
	    
	    if(upload_status) {
	    	out.println("File Upload to this directory: "+path);
	    }else {
	    	out.println("error");
	    }
		
		
	     
	     
	}
	
	public boolean uploadFile(InputStream inputStream, String path) {
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
