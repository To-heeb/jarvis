package com.dashboard;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class FileControllerServlet
 */
@WebServlet("/FileControllerServlet")
public class FileControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FileDbUtil fileDbUtil;
	
	// Define datasource and connection pool for Resource Injection
	@Resource(name="jdbc/dms_db")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		// create folder db util ... and pass in the conn pool / datasource
		try {
			fileDbUtil = new FileDbUtil(dataSource);
		}
		catch(Exception exc){
			throw new ServletException(exc);
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		   // read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// route to the appropriate method
			switch (theCommand) {
				case "RENAME":
					renameFile(request, response);
					break;
				case "DELETE":
					deleteFile(request, response);
					break;
				case "STARRED":
					starFile(request, response);
					break;
				case "UNSTARRED":
					unstarFile(request, response);
					break;
				case "TRASH":
					trashFile(request, response);
					break;
				case "UNTRASH":
					untrashFile(request, response);
					break;
				default:
					redirectToDashboard(request, response);;
					break;
			}
	}

	private void redirectToDashboard(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void untrashFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileId = request.getParameter("file_id").trim();
		
		if(fileId.isEmpty()) {
			// return to dashboard and throw an error
			sendMessage(request, response, "/dashboard/home", null, "failed");
			return;
		}
		
		// Access session
		HttpSession session = request.getSession(true);
		
		int userId = (int) session.getAttribute("id");
		
		int fileIdInt = Integer.parseInt(request.getParameter("file_id").trim());
		
		// create new folder object here;
		Filex theFile = new Filex(fileIdInt, userId);
		
		// add the folder to the database;
		String status;
		try {
			status = fileDbUtil.untrashFile(theFile);
			
			// if folder successfully created redirect to dashboard with success message else send error to dashboard
			sendMessage(request, response, "/dashboard/home", null, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void trashFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileId = request.getParameter("file_id").trim();
		
		if(fileId.isEmpty()) {
			// return to dashboard and throw an error
			sendMessage(request, response, "/dashboard/home", null, "failed");
			return;
		}
		
		// Access session
		HttpSession session = request.getSession(true);
		
		int userId = (int) session.getAttribute("id");
		
		int fileIdInt = Integer.parseInt(request.getParameter("file_id").trim());
		
		// create new folder object here;
		Filex theFile = new Filex(fileIdInt, userId);
		
		// add the folder to the database;
		String status;
		try {
			status = fileDbUtil.trashFile(theFile);
			
			// if folder successfully created redirect to dashboard with success message else send error to dashboard
			sendMessage(request, response, "/dashboard/home", null, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void unstarFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fileId = request.getParameter("file_id").trim();
		
		if(fileId.isEmpty()) {
			// return to dashboard and throw an error
			sendMessage(request, response, "/dashboard/home", null, "failed");
			return;
		}
		
		// Access session
		HttpSession session = request.getSession(true);
		
		int userId = (int) session.getAttribute("id");
		
		int fileIdInt = Integer.parseInt(request.getParameter("file_id").trim());
		
		// create new folder object here;
		Filex theFile = new Filex(fileIdInt, userId);
		
		// add the folder to the database;
		String status;
		try {
			status = fileDbUtil.unstarFile(theFile);
			
			// if folder successfully created redirect to dashboard with success message else send error to dashboard
			sendMessage(request, response, "/dashboard/home", null, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void starFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fileId = request.getParameter("file_id").trim();
		
		if(fileId.isEmpty()) {
			// return to dashboard and throw an error
			sendMessage(request, response, "/dashboard/home", null, "failed");
			return;
		}
		
		// Access session
		HttpSession session = request.getSession(true);
		
		int userId = (int) session.getAttribute("id");
		
		int fileIdInt = Integer.parseInt(request.getParameter("file_id").trim());
		
		// create new folder object here;
		Filex theFile = new Filex(fileIdInt, userId);
		
		// add the folder to the database;
		String status;
		try {
			status = fileDbUtil.starFile(theFile);
			
			// if folder successfully created redirect to dashboard with success message else send error to dashboard
			sendMessage(request, response, "/dashboard/home", null, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void deleteFile(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void renameFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// read file name here
		String fileName = request.getParameter("file_name").trim();
		
		if(fileName.isEmpty()) {
			// return to dashboard and throw an error
			sendMessage(request, response, "/dashboard/home", null, "error");
			return;
		}
		
		// Access session
		HttpSession session = request.getSession(true);
		
		int userId = (int) session.getAttribute("id");
		
		int fileId = Integer.parseInt(request.getParameter("file_id").trim());
		
		// create new folder object here;
		Filex theFile = new Filex(fileId, userId, fileName);
		
		// add the folder to the database;
		String status;
		try {
			status = fileDbUtil.updateFile(theFile);
			
			// if file successfully created redirect to dashboard with success message else send error to dashboard
			sendMessage(request, response, "/dashboard/home", null, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void sendMessage(HttpServletRequest request, HttpServletResponse response, String page, List<String> userData,
			String status) throws ServletException, IOException {
		
		//	initiate dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		
		// set attribute
		request.setAttribute("status", status);
		request.setAttribute("user_data", userData);
		
		// forward the request to JSP
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
