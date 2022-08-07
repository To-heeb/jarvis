package com.dashboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import com.authentication.User;




/**
 * Servlet implementation class DashboardControllerServlet
 */
@WebServlet("/DashboardControllerServlet")
public class DashboardControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private DashboardDbUtil dashboardDbUtil;
	
	// Define datasource/connection pool for Resource Injection
	@Resource(name = "jdbc/dms_db")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		// create our student db util ... and pass in the conn pool / datasource
		try {
			dashboardDbUtil = new DashboardDbUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String servletPath = request.getServletPath();
		response.getWriter().append( servletPath);
		
		String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
		
		// come back to list file type
		
		if(servletPath == null) {
			servletPath = "/dashboard/home";
		}
		
		switch(servletPath) {
		case "/dashboard/recent":
			try {
				listRecent(request, response, "recent.jsp");
			} catch (Exception e) {
				//e.printStackTrace();
				response.sendRedirect(baseURL + "dashboard/home");
			}
			break;
			
		case "/dashboard/starred":
			try {
				listStarred(request, response, "starred.jsp");
			} catch (Exception e) {
				//e.printStackTrace();
				response.sendRedirect(baseURL + "dashboard/home");
			}
			break;
			
		case "/dashboard/trash":
			try {
				listTrash(request, response, "trash.jsp");
			} catch (Exception e) {
				//e.printStackTrace();
				response.sendRedirect(baseURL + "dashboard/home");
			}
			break;
			
		case "/dashboard/folders":
			try {
				listFolders(request, response, "folders.jsp");
			} catch (Exception e) {
				//e.printStackTrace();
				response.sendRedirect(baseURL + "dashboard/home");
			}
			break;
		case "/dashboard/folder":
			try {
				showFolder(request, response, "folder_page.jsp");
			} catch (Exception e) {
				//e.printStackTrace();
				response.sendRedirect(baseURL + "dashboard/home");
			}
			break;
			
		case "/dashboard/settings":
			try {
				showSettings(request, response, "profile-settings.jsp");
			} catch (Exception e) {
				//e.printStackTrace();
				response.sendRedirect(baseURL + "dashboard/home");
			}
			break;
			
		case "/dashboard/documents":
			try {
				listDocuments(request, response, "documents.jsp");
			} catch (Exception e) {
				//e.printStackTrace();
				response.sendRedirect(baseURL + "dashboard/home");
			}
			break;
			
		case "/dashboard/videos":
			try {
				listVideos(request, response, "videos.jsp");
			} catch (Exception e) {
				//e.printStackTrace();
				response.sendRedirect(baseURL + "dashboard/home");
			}
			break;
			
		case "/dashboard/audios":
			try {
				listAudios(request, response, "audios.jsp");
			} catch (Exception e) {
				//e.printStackTrace();
				response.sendRedirect(baseURL + "dashboard/home");
			}
			break;
			
		case "/dashboard/images":
			try {
				listImages(request, response, "images.jsp");
			} catch (Exception e) {
				//e.printStackTrace();
				response.sendRedirect(baseURL + "dashboard/home");
			}
			break;
			
		case "/dashboard/others":
			try {
				listOthers(request, response, "others.jsp");
			} catch (Exception e) {
				//e.printStackTrace();
				response.sendRedirect(baseURL + "dashboard/home");
			}
			break;
			
		case "/dashboard/home":
			try {
				showIndex(request, response, "home.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				
				 response.sendRedirect(baseURL + "auth/login.jsp");
			}
			break;
			
		default:
				try {
					showIndex(request, response, "home.jsp");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
		}
		
	}

	private void showFolder(HttpServletRequest request, HttpServletResponse response, String page) throws Exception {
			
			// Access session
			HttpSession session = request.getSession(true);
			
			//get user id
			int userId = (int) session.getAttribute("id");
			
			String folderIdString = request.getParameter("folder").trim();
			
			String parentFolderIdString = request.getParameter("parent_folder").trim();
			
			if(folderIdString.isEmpty() || parentFolderIdString.isEmpty()) {
				// return to dashboard
				String url = request.getRequestURL().toString();
				String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
				response.sendRedirect(baseURL + "dashboard/home");
				return;
			}
			
			int folderId = Integer.parseInt(request.getParameter("folder").trim());

			int parentFolderId = Integer.parseInt(request.getParameter("parent_folder").trim());
			
			Folder theFolder = new Folder(folderId, parentFolderId, userId);
			
			// get folder data from db util
			Folder folderData =  dashboardDbUtil.getFolder(theFolder);
			
			// get folders from db util
			List<Folder> folderList =  dashboardDbUtil.getFolders(theFolder);
			
			Filex theFile = new Filex(folderId, userId);
			
			List<Filex> fileList =  dashboardDbUtil.getFiles(theFile);
			
			//List<Folder> folderFiles =  dashboardDbUtil.getFiles(theFolder);
			
			//send variable from here to view
			request.setAttribute("folder_data", folderData);
			request.setAttribute("folder_list", folderList);
			request.setAttribute("file_list", fileList);
					
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	
							
			// forward the request to JSP
			dispatcher.forward(request, response);
		
	}
	

	private void listOthers(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// Access session
			HttpSession session = request.getSession(true);
			
			//get user id
			int userId = (int) session.getAttribute("id");
			
			Filex allFiles = new Filex(0, userId);
			
			// get students from db util
			List<Filex> documents =  dashboardDbUtil.getOtherFiles(allFiles);
			
			//send variable from here to view
			request.setAttribute("files", documents);
				
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			
			// forward the request to JSP
			dispatcher.forward(request, response);	
	}
	

	private void listImages(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// Access session
			HttpSession session = request.getSession(true);
			
			//get user id
			int userId = (int) session.getAttribute("id");
			
			Filex allImages = new Filex(0, userId);
			
			// get students from db util
			List<Filex> documents =  dashboardDbUtil.getImages(allImages);
			
			//send variable from here to view
			request.setAttribute("images", documents);
				
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			
			// forward the request to JSP
			dispatcher.forward(request, response);	
	}
	

	private void listAudios(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// Access session
			HttpSession session = request.getSession(true);
			
			//get user id
			int userId = (int) session.getAttribute("id");
			
			Filex allAudios = new Filex(0, userId);
			
			// get students from db util
			List<Filex> documents =  dashboardDbUtil.getAudios(allAudios);
			
			//send variable from here to view
			request.setAttribute("audios", documents);
				
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			
			// forward the request to JSP
			dispatcher.forward(request, response);	
	}
	

	private void listVideos(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// Access session
			HttpSession session = request.getSession(true);
			
			//get user id
			int userId = (int) session.getAttribute("id");
			
			Filex allVidoes = new Filex(0, userId);
			
			// get students from db util
			List<Filex> documents =  dashboardDbUtil.getVideos(allVidoes);
			
			//send variable from here to view
			request.setAttribute("videos", documents);
				
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			
			// forward the request to JSP
			dispatcher.forward(request, response);	
	}
	

	private void listDocuments(HttpServletRequest request, HttpServletResponse response, String page) throws Exception {
			
			// Access session
			HttpSession session = request.getSession(true);
			
			//get user id
			int userId = (int) session.getAttribute("id");
			
			Filex allDocument = new Filex(0, userId);
			
			// get students from db util
			List<Filex> documents =  dashboardDbUtil.getDocuments(allDocument);
			
			//send variable from here to view
			request.setAttribute("documents", documents);
				
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			
			// forward the request to JSP
			dispatcher.forward(request, response);	
	}

	
	private void showIndex(HttpServletRequest request, HttpServletResponse response, String page) throws Exception {
			
			// Access session
			HttpSession session = request.getSession(true);
			
			//get user id
			int userId = (int) session.getAttribute("id");
			
			Folder theFolder = new Folder(0, userId);
			
			// get folders from db util
			List<Folder> folders =  dashboardDbUtil.getFolders(theFolder);
			
			Filex theFile = new Filex(0, userId);
			
			// get files from db util
			List<Filex> fileList =  dashboardDbUtil.getFiles(theFile);
			
			//send variable from here to view
			request.setAttribute("folder_list", folders);
			request.setAttribute("file_list", fileList);
			
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	
							
			// forward the request to JSP
			dispatcher.forward(request, response);	
	}
	
	
	public void showSettings (HttpServletRequest request, HttpServletResponse response, String page) throws Exception {
			// Access session
			HttpSession session = request.getSession(true);
			
			//get user id
			int userId = (int) session.getAttribute("id");
			
			// get user_info from db util
			User user_info =  dashboardDbUtil.getUserInfo(userId);
			
			//send variable from here to view
			request.setAttribute("user_info", user_info);
			
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	
							
			// forward the request to JSP
			dispatcher.forward(request, response);
	}

	
	private void listStarred(HttpServletRequest request, HttpServletResponse response, String page) throws Exception {
			
			// Access session
			HttpSession session = request.getSession(true);
			
			//get user id
			int userId = (int) session.getAttribute("id");
			
			Folder theFolder = new Folder(0, userId);
			
			// get starred folders from db util
			List<Folder> folders =  dashboardDbUtil.getStarredFolders(theFolder);
			
			Filex theFile = new Filex(0, userId);
			
			// get starred files from db util
			List<Filex> files =  dashboardDbUtil.getStarredFiles(theFile);
			
			//send variable from here to view
			request.setAttribute("starred_folder_list", folders);
			request.setAttribute("starred_file_list", files);
			
		 	// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);				
							
			// forward the request to JSP
			dispatcher.forward(request, response);
	}
	

	private void listFolders(HttpServletRequest request, HttpServletResponse response, String page) throws Exception {
		
			// Access session
			HttpSession session = request.getSession(true);
			
			//get user id
			int userId = (int) session.getAttribute("id");
			
			Folder theFolder = new Folder(0, userId);
			
			// get students from db util
			List<Folder> folders =  dashboardDbUtil.getFoldersByUser(theFolder);
			
			//send variable from here to view
			request.setAttribute("folder_list", folders);
					
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	
					
			// forward the request to JSP
			dispatcher.forward(request, response);
	}
	

	private void listTrash(HttpServletRequest request, HttpServletResponse response, String page) throws Exception {
		
		    // Access session
			HttpSession session = request.getSession(true);
			
			//get user id
			int userId = (int) session.getAttribute("id");
			
			Folder theFolder = new Folder(0, userId);
			
			// get trashed from db util
			List<Folder> folders =  dashboardDbUtil.getTrashedFolders(theFolder);
			
			Filex theFile = new Filex(0, userId);
			
			// get trashed files from db util
			List<Filex> files =  dashboardDbUtil.getTrashedFiles(theFile);
			
			//send variable from here to view
			request.setAttribute("trash_folder_list", folders);
			request.setAttribute("trash_file_list", files);
				
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			
			// forward the request to JSP
			dispatcher.forward(request, response);
	}
	

	private void listRecent(HttpServletRequest request, HttpServletResponse response, String page) throws Exception {
		// Access session
		HttpSession session = request.getSession(true);
		
		//get user id
		int userId = (int) session.getAttribute("id");
		
		Folder theFolder = new Folder(0, userId);
		
		// get students from db util
		List<Folder> folders =  dashboardDbUtil.getFolders(theFolder);
		
		Filex theFile = new Filex(0, userId);
		
		// get files from db util
		List<Filex> fileList =  dashboardDbUtil.getFiles(theFile);
		
		//send variable from here to view
		request.setAttribute("folder_list", folders);
		request.setAttribute("file_list", fileList);
		
		//	initiate dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		
		// forward the request to JSP
		dispatcher.forward(request, response);
	}
	
//	
//	public static void downloadFile(URL url, String fileName) throws Exception {
//        try (InputStream in = url.openStream()) {
//            Files.copy(in, Paths.get(fileName));
//        }
//    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
