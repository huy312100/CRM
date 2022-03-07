package cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.crm.dto.UserCreateDto;
import cybersoft.javabackend.crm.model.Role;
import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.service.RoleService;
import cybersoft.javabackend.crm.service.UserService;
import cybersoft.javabackend.crm.util.JspConst;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "userServlet", urlPatterns = {
		UrlConst.USER_DASHBOARD,
		UrlConst.USER_ADD,
		UrlConst.USER_UPDATE,
		UrlConst.USER_DELETE
})
public class UserServlet extends HttpServlet {
	private UserService service;
	private RoleService roleService;
	@Override
	public void init() throws ServletException {
		
		super.init();
		service = new UserService();
		roleService = new RoleService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
			case UrlConst.USER_DASHBOARD:
				getUserDashboard(req,resp);
				break;
			case UrlConst.USER_ADD:
				getUserAdd(req,resp);
				break;
			case UrlConst.USER_UPDATE:
				getUserUpdate(req,resp);
				break;
			case UrlConst.USER_DELETE:
				getUserDelete(req,resp);
				break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlConst.USER_DASHBOARD:
			
			break;
		case UrlConst.USER_ADD:
			postUserAdd(req,resp);
			break;
		case UrlConst.USER_UPDATE:
			postUserUpdate(req,resp);
			break;
		case UrlConst.USER_DELETE:
			
			break;	
		}
	}

	private void getUserDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		service.deleteById(id);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
		
	}

	private void getUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Role> roles = roleService.findAllRole();
		if(roles != null || !roles.isEmpty()) {
			req.setAttribute("roles", roles);
		}
		int idUpdate = Integer.parseInt(req.getParameter("id"));
		User userUpdate = service.findUserById(idUpdate);
		
		req.setAttribute("user", userUpdate);
		req.getRequestDispatcher(JspConst.USER_UPDATE).forward(req, resp);
	}

	private void getUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Role> roles = roleService.findAllRole();
		if(roles != null || !roles.isEmpty()) {
			req.setAttribute("roles", roles);
		}
		req.getRequestDispatcher(JspConst.USER_ADD)
			.forward(req, resp);
	}

	private void getUserProfile(HttpServletRequest req, HttpServletResponse resp) {
		
		
	}

	private void getUserDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> users = service.findAll();
		
		if(users != null && !users.isEmpty()) {
			req.setAttribute("users", users);
		}
		
		req.getRequestDispatcher(JspConst.USER_DASHBOARD)
			.forward(req, resp);		
	}
	
	private void postUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserCreateDto dto = extractDtoFromReq(req);
		
		service.add(dto);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
	}
	
	private void postUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int idUpdate = Integer.parseInt(req.getParameter("id"));
		UserCreateDto dto = extractDtoFromReq(req);
		
		service.update(dto, idUpdate);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
	}
	
	private UserCreateDto extractDtoFromReq(HttpServletRequest req) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String avatar = req.getParameter("avatar");
		int roleId = Integer.parseInt(req.getParameter("role"));
		return new UserCreateDto(email, password, name, avatar, roleId);
	}
}
