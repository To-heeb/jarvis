package com.upload;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

/**
 * Servlet implementation class TempDownloadControllerServlet
 */
@WebServlet(name = "/TempDownloadControllerServlet", asyncSupported = true,  urlPatterns = {"/download"})
public class TempDownloadControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempDownloadControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Step 2: get the printwriter
		PrintWriter out = response.getWriter();
		
		String filename = request.getParameter("filename");
		
		//get file path string
		String filePathString = getServletContext().getRealPath("/"+"file_upload" +File.separator + filename);
		
		//file to be downloaded
		File downloadedFile = new File(filePathString);
		
		//get file type
		String fileType = Files.probeContentType(downloadedFile.toPath().toAbsolutePath());
		
		JSONObject json = new JSONObject();
		if(downloadedFile.exists()) {
			response.setContentType(fileType);
			response.setContentLength((int) downloadedFile.length());
			
			//force to download
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment: filename=\"%s\"", downloadedFile.getName());
			response.setHeader(headerKey, headerValue);
			
			FileInputStream fileInputStream = new FileInputStream(downloadedFile);
			//ServletOutputStream  outputStream = response.getOutputStream();
			
			int bytez;
			
			while((bytez = fileInputStream.read()) != -1) {
				out.write(bytez);
			}
			fileInputStream.close();
			out.close();
						
		}else {
			
			
		}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//Step 1: set the content type
			//response.setContentType("text/html");
			
			//Step 2: get the printwriter
			PrintWriter out = response.getWriter();
			
			String filename = request.getParameter("filename");
			
			//get file path string
			String filePathString = getServletContext().getRealPath("/"+"file_upload" +File.separator + filename);
			
			//file to be downloaded
			File downloadedFile = new File(filePathString);
			
			//get file type
			String fileType = Files.probeContentType(downloadedFile.toPath().toAbsolutePath());
			
			JSONObject json = new JSONObject();
			if(downloadedFile.exists()) {
				response.setContentType("application/octet-stream");
				response.setContentLength((int) downloadedFile.length());
				
				//force to download
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment: filename=\"%s\"", downloadedFile.getName());
				response.setHeader(headerKey, headerValue);
				
				FileInputStream fileInputStream = new FileInputStream(downloadedFile);
				//ServletOutputStream  outputStream = response.getOutputStream();
				
				int bytez;
				while((bytez = fileInputStream.read()) != -1) {
					out.write(bytez);
				}
				fileInputStream.close();
				out.close();
				
				
			}else {
				
				json.put("status", "failed");
				json.put("message", "Something went wrong");
				out.println(json);
			}
					
			
			
			
	}

}
