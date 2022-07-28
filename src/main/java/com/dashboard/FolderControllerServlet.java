package com.dashboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.authentication.User;
import com.authentication.UserDbUtil;

/**
 * Servlet implementation class FolderControllerServlet
 */
@WebServlet("/FolderControllerServlet")
public class FolderControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	private FolderDbUtil folderDbUtil;
	
	// Define datasource and connection pool for Resource Injection
	@Resource(name="jdbc/dms_db")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		// create folder db util ... and pass in the conn pool / datasource
		try {
			folderDbUtil = new FolderDbUtil(dataSource);
		}
		catch(Exception exc){
			throw new ServletException(exc);
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FolderControllerServlet() {
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
			case "CREATE":
			try {
				createFolder(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "RENAME":
				renameFolder(request, response);
				break;
			case "DELETE":
				//deleteFolder(request, response);
				break;
			case "STARRED":
				starFolder(request, response);
				break;
			case "UNSTARRED":
				unstarFolder(request, response);
				break;
			case "TRASH":
				trashFolder(request, response);
				break;
			case "UNTRASH":
				untrashFolder(request, response);
				break;
			default:
				redirectToDashboard(request, response);;
				break;
		}
		
	}

	private void untrashFolder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String folderId = request.getParameter("folder_id").trim();
		
		if(folderId.isEmpty()) {
			// return to dashboard and throw an error
			sendMessage(request, response, "/dashboard/home", null, "failed");
			return;
		}
		
		// Access session
		HttpSession session = request.getSession(true);
		
		int userId = (int) session.getAttribute("id");
		
		int folderIdInt = Integer.parseInt(request.getParameter("folder_id").trim());
		
		// create new folder object here;
		Folder newFolder = new Folder(folderIdInt, userId);
		
		// add the folder to the database;
		String status;
		try {
			status = folderDbUtil.untrashFolder(newFolder);
			
			// if folder successfully created redirect to dashboard with success message else send error to dashboard
			sendMessage(request, response, "/dashboard/home", null, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void trashFolder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String folderId = request.getParameter("folder_id").trim();
		
		if(folderId.isEmpty()) {
			// return to dashboard and throw an error
			sendMessage(request, response, "/dashboard/home", null, "failed");
			return;
		}
		
		// Access session
		HttpSession session = request.getSession(true);
		
		int userId = (int) session.getAttribute("id");
		
		int folderIdInt = Integer.parseInt(request.getParameter("folder_id").trim());
		
		// create new folder object here;
		Folder newFolder = new Folder(folderIdInt, userId);
		
		// add the folder to the database;
		String status;
		try {
			status = folderDbUtil.trashFolder(newFolder);
			
			// if folder successfully created redirect to dashboard with success message else send error to dashboard
			sendMessage(request, response, "/dashboard/home", null, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void unstarFolder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String folderId = request.getParameter("folder_id").trim();
		
		if(folderId.isEmpty()) {
			// return to dashboard and throw an error
			sendMessage(request, response, "/dashboard/home", null, "failed");
			return;
		}
		
		// Access session
		HttpSession session = request.getSession(true);
		
		int userId = (int) session.getAttribute("id");
		
		int folderIdInt = Integer.parseInt(request.getParameter("folder_id").trim());
		
		// create new folder object here;
		Folder newFolder = new Folder(folderIdInt, userId);
		
		// add the folder to the database;
		String status;
		try {
			status = folderDbUtil.unstarFolder(newFolder);
			
			// if folder successfully created redirect to dashboard with success message else send error to dashboard
			sendMessage(request, response, "/dashboard/home", null, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void starFolder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String folderId = request.getParameter("folder_id").trim();
		
		if(folderId.isEmpty()) {
			// return to dashboard and throw an error
			sendMessage(request, response, "/dashboard/home", null, "failed");
			return;
		}
		
		// Access session
		HttpSession session = request.getSession(true);
		
		int userId = (int) session.getAttribute("id");
		
		int folderIdInt = Integer.parseInt(request.getParameter("folder_id").trim());
		
		// create new folder object here;
		Folder newFolder = new Folder(folderIdInt, userId);
		
		// add the folder to the database;
		String status;
		try {
			status = folderDbUtil.starFolder(newFolder);
			
			// if folder successfully created redirect to dashboard with success message else send error to dashboard
			sendMessage(request, response, "/dashboard/home", null, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void renameFolder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// read folder name here
		String folderName = request.getParameter("folder_name").trim();
		
		if(folderName.isEmpty()) {
			// return to dashboard and throw an error
			sendMessage(request, response, "/dashboard/home", null, "error");
			return;
		}
		
		// Access session
		HttpSession session = request.getSession(true);
		
		int userId = (int) session.getAttribute("id");
		
		int folderId = Integer.parseInt(request.getParameter("folder_id").trim());
		
		// create new folder object here;
		Folder newFolder = new Folder(folderId, userId, folderName);
		
		// add the folder to the database;
		String status;
		try {
			status = folderDbUtil.updateFolder(newFolder);
			
			// if folder successfully created redirect to dashboard with success message else send error to dashboard
			sendMessage(request, response, "/dashboard/home", null, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void createFolder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read folder name here
		String folderName = request.getParameter("folder_name").trim();
		
		// will be useful somehow
		/*Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);*/
		
		// validate folder name here
		if(folderName.isEmpty()) {
			// return to dashboard and throw an error
			sendMessage(request, response, "/dashboard/home", null, "error");
			return;
		}
		
		// Access session
		HttpSession session = request.getSession(true);
		
		int userId = (int) session.getAttribute("id");
		
		int folderId = Integer.parseInt(request.getParameter("folder_id").trim());
		
		// create new folder object here;
		Folder newFolder = new Folder(folderId, userId, folderName);
		
		// add the folder to the database;
		String status = folderDbUtil.createFolder(newFolder);
		
		// if folder successfully created redirect to dashboard with success message else send error to dashboard
		sendMessage(request, response, "/dashboard/home", null, status);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*// read folder name here
		String folderName = request.getParameter("folder_name").trim();
				
		// Access session
		HttpSession session =request.getSession(true);
				
		response.setContentType("text/html");
		
		//Step 2: get the printwriter
		PrintWriter out = response.getWriter();
		
		//Step 3: get HTML content
		out.println("<html><body>");
		
		out.println("<h2>Hello World</h2>");
		out.println("<hr>");
		out.println(folderName+ " " + session.getAttribute("id"));
		
		out.println("</html></body>");*/
	}
	
	private void redirectToDashboard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		//get base url
		String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/" + "dashboard";
		
		response.sendRedirect(baseURL);
	}
	
	
	private void sendMessage(HttpServletRequest request, HttpServletResponse response, String page,
			List<String> userData, String status) throws ServletException, IOException {
		
			//	initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			
			// set attribute
			request.setAttribute("status", status);
			request.setAttribute("user_data", userData);
			
			// forward the request to JSP
			dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
