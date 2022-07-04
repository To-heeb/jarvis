<%

	String url = request.getRequestURL().toString();
	String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";

	session.invalidate();
	response.sendRedirect(baseURL);

%>