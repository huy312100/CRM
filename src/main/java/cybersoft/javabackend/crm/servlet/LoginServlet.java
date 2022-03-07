package cybersoft.javabackend.crm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.javabackend.crm.service.AuthService;
import cybersoft.javabackend.crm.util.JspConst;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name= "loginServlet", urlPatterns = {
		UrlConst.AUTH_LOGIN,
		UrlConst.AUTH_SIGNUP,
		UrlConst.AUTH_LOGOUT
})
public class LoginServlet extends HttpServlet{
	private AuthService service;
	
	@Override
	public void init() throws ServletException {
		service = new AuthService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.AUTH_LOGIN: 
			
			// kiem tra cookie - email
			Cookie[] cookies = req.getCookies();
			int cookiesCount = cookies == null ? 0 : cookies.length;
			for(int i = 0; i < cookiesCount; i++)
				if(cookies[i].getName().equals("email"))
					req.setAttribute("email", cookies[i].getValue());
			
			String status = String.valueOf(req.getSession().getAttribute("status"));
			if(!status.equals("null"))
				resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
			else
				req.getRequestDispatcher(JspConst.AUTH_LOGIN)
					.forward(req, resp);
			break;
		case UrlConst.AUTH_SIGNUP:
			req.getRequestDispatcher(JspConst.AUTH_SIGNUP)
				.forward(req, resp);
			break;
		case UrlConst.AUTH_LOGOUT: 
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
			break;	
		default:
			throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
			case UrlConst.AUTH_LOGIN:
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String remember = req.getParameter("rememberUsername");
			boolean isLogin = true;
			
			HttpSession currentSession = req.getSession();
			
			if(email == null || password == null)
			{
				isLogin = false;
			}else if(!service.login(email, password)) {
				isLogin = false;
			}
			
			if(isLogin) {
				currentSession.setAttribute("status", "Logged is Successfully.");
				resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
			}else {
				resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
			}
				break;
			case UrlConst.AUTH_SIGNUP:
				req.getRequestDispatcher(JspConst.AUTH_SIGNUP)
				.forward(req, resp);
				break;
			case UrlConst.AUTH_LOGOUT:
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
		}
	}
}
