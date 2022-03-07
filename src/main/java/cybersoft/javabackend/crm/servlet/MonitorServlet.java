package cybersoft.javabackend.crm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.crm.repository.MySQLConnection;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "monitorServlet", urlPatterns = UrlConst.HEALTH)
public class MonitorServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch(path) {
			case UrlConst.HEALTH: {
				
				
				if(MySQLConnection.getConnection() != null) {
					resp.getWriter().append("Database connection has been established successfully.");
				}else {
					resp.getWriter().append("Database connection could not be established.");
				}
				
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}
}
