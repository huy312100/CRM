package cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.crm.dto.TaskCreateDto;
import cybersoft.javabackend.crm.model.Job;
import cybersoft.javabackend.crm.model.Status;
import cybersoft.javabackend.crm.model.Task;
import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.service.JobService;
import cybersoft.javabackend.crm.service.TaskService;
import cybersoft.javabackend.crm.service.UserService;
import cybersoft.javabackend.crm.util.JspConst;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "taskServlet", urlPatterns = {
			UrlConst.TASK_DASHBOARD,
			UrlConst.TASK_ADD,
			UrlConst.TASK_UPDATE,
			UrlConst.TASK_DELETE
})
public class TaskServlet extends HttpServlet {
	
	private TaskService taskService;
	private UserService userService;
	private JobService jobService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		taskService = new TaskService();
		userService = new UserService();
		jobService = new JobService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.TASK_DASHBOARD:
			getTaskDashboard(req,resp);
			break;
		case UrlConst.TASK_ADD:
			getAddTask(req,resp);
			break;
		case UrlConst.TASK_UPDATE:
			getUpdateTask(req,resp);
			break;
		case UrlConst.TASK_DELETE:
			getDeleteTask(req,resp);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.TASK_DASHBOARD:
			
			break;
		case UrlConst.TASK_ADD:
			postAddTask(req,resp);
			break;
		case UrlConst.TASK_UPDATE:
			postUpdateTask(req,resp);
			break;
		case UrlConst.TASK_DELETE:
			
			break;
		default:
			break;
		}
	}

	private void getTaskDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Task> tasks = taskService.findAllTask();
		
		if(tasks != null && ! tasks.isEmpty()) {
			req.setAttribute("tasks", tasks);
		}
		req.getRequestDispatcher(JspConst.TASK_DASHBOARD).forward(req, resp);
		
	}
	
	private void getAddTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> users = userService.findAll();
		if(users != null || !users.isEmpty()) {
			req.setAttribute("users", users);
		}
		List<Job> jobs = jobService.findAll();
		if(jobs != null || !jobs.isEmpty()) {
			req.setAttribute("jobs", jobs);
		}
		
		List<Status> statuss = taskService.findAllStatus();
		if(statuss != null || !statuss.isEmpty()) {
			req.setAttribute("statuss", statuss);
		}
		req.getRequestDispatcher(JspConst.TASK_ADD).forward(req, resp);
	}
	
	private void getUpdateTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> users = userService.findAll();
		if(users != null || !users.isEmpty()) {
			req.setAttribute("users", users);
		}
		List<Job> jobs = jobService.findAll();
		if(jobs != null || !jobs.isEmpty()) {
			req.setAttribute("jobs", jobs);
		}
		List<Status> statuss = taskService.findAllStatus();
		if(statuss != null || !statuss.isEmpty()) {
			req.setAttribute("statuss", statuss);
		}
		
		int idUpdate = Integer.parseInt(req.getParameter("id"));
		Task taskUpdate = taskService.findTaskById(idUpdate);
		req.setAttribute("task", taskUpdate);
		req.getRequestDispatcher(JspConst.TASK_UPDATE).forward(req, resp);
	}
	
	private void getDeleteTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int idDelete = Integer.parseInt(req.getParameter("id"));
		
		taskService.deleteTask(idDelete);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD);
	}
	
	private void postAddTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		TaskCreateDto dto = extractDtoFromReq(req);
		taskService.addNewTask(dto);
		resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD);
	}
	
	private void postUpdateTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int idUpdate = Integer.parseInt(req.getParameter("id"));
		TaskCreateDto dto = extractDtoFromReq(req);
		
		taskService.updateTask(dto, idUpdate);
		resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD);
	}
	
	private TaskCreateDto extractDtoFromReq(HttpServletRequest req) {
		String name = req.getParameter("name");
		String startDate = req.getParameter("start_date");
		String endDate = req.getParameter("end_date");
		int userId = Integer.parseInt(req.getParameter("user"));
		int statusId = Integer.parseInt(req.getParameter("status"));
		int jobId = Integer.parseInt(req.getParameter("job"));
		
		return new TaskCreateDto(name, startDate, endDate, userId, statusId, jobId);
	}
}
