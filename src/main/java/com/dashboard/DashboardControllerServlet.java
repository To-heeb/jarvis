package com.dashboard;

import java.io.IOException;
import java.io.PrintWriter;
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

	
	private void showIndex(HttpServletRequest request, HttpServletResponse response, String string) throws IOException {
		// TODO Auto-generated method stub
		//Step 1: set the content type
				response.setContentType("text/html");
				
				//Step 2: get the  printwriter
				PrintWriter out = response.getWriter();
				
				//Step 3: read configuration params
				ServletContext context = getServletContext(); //inherited from HttpServlet
				String maxCartSize = context.getInitParameter("max-shopping-cart-size");
				String teamName = context.getInitParameter("project-team-name");
				
				//Step 4: generate HTML content
				out.println("<html><body>");
				//out.println("Max cart: "+ maxCartSize);
				out.println("<br><br>");
				out.println("Team Name: "+ "Toheeb");
				out.println("</body></html>");
	}
	
	public void sendIndex (HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
