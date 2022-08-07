package com.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Servlet implementation class UserControllerServlet
 */
@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDbUtil userDbUtil;
	
	// Define datasource and connection pool for Resource Injection
	@Resource(name="jdbc/dms_db")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		// create our user db util ... and pass in the conn pool / datasource
		try {
			userDbUtil = new UserDbUtil(dataSource);
		}
		catch(Exception exc){
			throw new ServletException(exc);
		}
	}
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// read the "command" parameter
		String theCommand = request.getParameter("command");
		
		//if the command is missing, then default to listing students
		if(theCommand == "logout") {
			
			try {
				//logoutUser(request, response);
				//Step 1: set the content type
				response.setContentType("text/html");
				
				//Step 2: get the printwriter
				PrintWriter out = response.getWriter();
				
				//Step 3: get HTML content
				out.println("<html><body>");
				
				out.println("<h2>Hello World</h2>");
				out.println("<hr>");
				out.println("Time on the server is: "+ new java.util.Date());
				
				out.println("</html></body>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//read the form type 
			String formType = request.getParameter("form_type");
			
			//if form_type is missing, redirect to homepage;
			if(formType == null) {
				formType = "homepage";
			}
			
			//route to appropriate formType method
			switch(formType) {
			case "login":
				loginUser(request, response);
				break;
				
			case "register":
				registerUser(request, response);
				break;
				
			case "settings":
				updateSettings(request, response);
				break;
			
			case "password":
				passwordSettings(request, response);
				break;
				
			case "deactivate":
				deactivateAccount(request, response);
				break;
				
			case "homepage":
				redirectToHomepage(request, response);
				break;
				
			default:
				redirectToHomepage(request, response);
				break;
			}
			
		}
		catch(Exception exc) {
			
			throw new ServletException(exc);
		}
	}
	
	private void passwordSettings(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
	
		// Access session
		HttpSession session = request.getSession(true);
				
		
		// read first name here
		String password = request.getParameter("password").trim();
		
		// read last name here
		String confirm_password = request.getParameter("confirm_password").trim();
		
		if(!password.equalsIgnoreCase(confirm_password)) {
			
			// return to settings page and throw an error
			response.sendRedirect(baseURL + "dashboard/settings?status=password_error");
			return;
		}
		//get user id
		int userId = (int) session.getAttribute("id");
		
		// hash password here
		String passwordHash = hashPasword(password);
		
		String status = userDbUtil.updatePassword(userId, passwordHash);
		
		response.sendRedirect(baseURL + "dashboard/settings?status=" + status);
	}



	private void deactivateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
		
		// Access session
		HttpSession session = request.getSession(true);
		
		//get user id
		int userId = (int) session.getAttribute("id");
		
		boolean status = userDbUtil.deactivateAccount(userId);
		
		if(status) {
			// return to settings page and throw an error
			response.sendRedirect(baseURL);
			return;
		}
		
		response.sendRedirect(baseURL + "dashboard/settings?status=deactivate_error");
	}



	private void updateSettings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
	
		// Access session
		HttpSession session = request.getSession(true);
				
		
		// read first name here
		String firstName = request.getParameter("first_name").trim();
		
		// read last name here
		String lastName = request.getParameter("last_name").trim();
		
		// read email here
		String email = request.getParameter("email").trim();
		
		//get user id
		int userId = (int) session.getAttribute("id");
		
		if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
			
			// return to settings page and throw an error
			response.sendRedirect(baseURL + "dashboard/settings?status=settings_error");
			return;
		}
		
		User updatedUser = new User (userId, firstName, lastName, email);
		
		// update data in the db util
		String status;
		try {
			
			status = userDbUtil.updateSettings(updatedUser);
			
			response.sendRedirect(baseURL + "dashboard/settings?status=" + status);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}


	private void redirectToHomepage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		//get base url
		String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
		
		response.sendRedirect(baseURL);
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read user info from form data
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email").toLowerCase();
		String password = request.getParameter("password");
		
		// pass this data to the frontend with object for easier reading
		List<String> userData = new ArrayList<String>();
		userData.add(firstname);
		userData.add(lastname);
		userData.add(email);
		
		// validate if none of the form data is empty here, else if any is empty return message back to user that form can't be empty.
		if(firstname.trim().isEmpty() || lastname.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
			
			//get the object and pass it here instead of using arraylist
			
			// return back to form page with other values
			sendMessage(request, response, "/auth/register.jsp", userData, "error");
			return;
		}
		
		// hash password here
		String passwordHash = hashPasword(password);
		
		// create a new user object
		User newUser = new User(firstname, lastname, email, passwordHash);
		
		// add the user to the database
		String status = userDbUtil.registerUser(newUser);
		
		// send them success message and direct them to login
		sendMessage(request, response, "/auth/register.jsp", null, status);
		
	}


	private String hashPasword(String password) {
		// TODO Auto-generated method stub
		 
		try {
			String SALT = password;
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			md.update(SALT.getBytes());
			
			byte[] digestedMd = md.digest();
			  
			return bytesToString(digestedMd);  
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private String bytesToString(byte[] digestedMd) {
		
		StringBuilder stringBuilder = new StringBuilder();
		for(byte b : digestedMd) {	
			stringBuilder.append(String.format("%02x", b));
		}
		
		return stringBuilder.toString();
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

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		// read user info from form data
		String email = request.getParameter("email").toLowerCase();
		String password = request.getParameter("password");
		
		// validate form data
		if(email.trim().isEmpty() || password.trim().isEmpty()) {
			
			//get the object and pass it here instead of using arraylist
			
			// return back to form page with other values
			sendMessage(request, response, "/auth/login.jsp", null, "error");
			return;
		}

		// hash password here
		String passwordHash = hashPasword(password);
				
		// create a new user object
		User user = new User(email, passwordHash);
		
		// add the user to the database
		User userData = userDbUtil.loginUser(user);
		
		// if true successful store details in session and redirect to dashboard else send error to login page
		if(userData.isStatus()) {
			
			//get base url
			String url = request.getRequestURL().toString();
			String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
			
			// store data in session
			session.setAttribute("id", userData.getId());
			session.setAttribute("firstname", userData.getFirstname());
			session.setAttribute("lastname", userData.getLastname());
			session.setAttribute("email", userData.getEmail());
			session.setAttribute("role", userData.getRole());
			session.setAttribute("memory", userData.getMemoryUsage());
			session.setAttribute("image", userData.getProfilePicture());
			
			response.sendRedirect(baseURL +"dashboard/home");  
		}else {
			
			sendMessage(request, response, "/auth/login.jsp", null, "error");
			
		}
		
	}

}
