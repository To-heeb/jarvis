package com.dashboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DashboardControllerServlet
 */
@WebServlet("/DashboardControllerServlet")
public class DashboardControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		
		if(servletPath == null) {
			servletPath = "/dashboard";
		}
		
		switch(servletPath) {
		case "/dashboard/recent":
			listRecent(request, response, "recent.jsp");
			break;
			
		case "/dashboard/starred":
			listStarred(request, response, "starred.jsp");
			break;
			
		case "/dashboard/trash":
			listTrash(request, response, "trash.jsp");
			break;
			
		case "/dashboard/folders":
			listFolders(request, response, "folders.jsp");
			break;
			
		case "/dashboard/settings":
			showSettings(request, response, "settings.jsp");
			break;
			
		case "/dashboard/documents":
			listDocuments(request, response, "documents.jsp");
			break;
			
		case "/dashboard/videos":
			listVideos(request, response, "videos.jsp");
			break;
			
		case "/dashboard/audios":
			listAudios(request, response, "audios.jsp");
			break;
			
		case "/dashboard/images":
			listImages(request, response, "images.jsp");
			break;
			
		case "/dashboard/others":
			listOthers(request, response, "others.jsp");
			break;
			
		case "/dashboard":
			showIndex(request, response, "index.jsp");
			break;
			
		default:
			showIndex(request, response, "index.jsp");
			break;
		}
		
	}

	private void listOthers(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	
							
			// forward the request to JSP
			dispatcher.forward(request, response);
		
	}

	private void listImages(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	
							
			// forward the request to JSP
			dispatcher.forward(request, response);
		
	}

	private void listAudios(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	
							
			// forward the request to JSP
			dispatcher.forward(request, response);
		
	}

	private void listVideos(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	
							
			// forward the request to JSP
			dispatcher.forward(request, response);
		
	}

	private void listDocuments(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	
							
			// forward the request to JSP
			dispatcher.forward(request, response);	
	}

	private void showIndex(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	
							
			// forward the request to JSP
			dispatcher.forward(request, response);
	}

	private void showSettings(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	
							
			// forward the request to JSP
			dispatcher.forward(request, response);
		
	}

	private void listStarred(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
		 	// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);				
							
			// forward the request to JSP
			dispatcher.forward(request, response);
		
	}

	private void listFolders(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	
					
			// forward the request to JSP
			dispatcher.forward(request, response);
		
	}

	private void listTrash(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
			// initiate dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			
			// forward the request to JSP
			dispatcher.forward(request, response);
		
	}

	private void listRecent(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
		//	initiate dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		
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
