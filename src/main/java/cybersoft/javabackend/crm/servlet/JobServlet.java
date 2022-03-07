package cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Url;

import cybersoft.javabackend.crm.dto.JobCreateDto;
import cybersoft.javabackend.crm.model.Job;
import cybersoft.javabackend.crm.service.JobService;
import cybersoft.javabackend.crm.util.JspConst;
import cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "jobServlet", urlPatterns = {
				UrlConst.JOB_DASHBOARD,UrlConst.JOB_ADD,UrlConst.JOB_UPDATE,
				UrlConst.JOB_DELETE
})
public class JobServlet extends HttpServlet{
	private JobService service;
	
	@Override
	public void init() throws ServletException {
		super.init();
		service = new JobService();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.JOB_DASHBOARD:
			getJobDashboard(req,resp);
			break;
		case UrlConst.JOB_ADD:
			getJobAdd(req,resp);
			break;
		case UrlConst.JOB_UPDATE:
			getJobUpdate(req,resp);
			break;
		case UrlConst.JOB_DELETE:
			getJobDelete(req, resp);
			break;
		default:
			break;
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.JOB_DASHBOARD:
			
			break;
		case UrlConst.JOB_ADD:
			postJobAdd(req,resp);
			break;
		case UrlConst.JOB_UPDATE:
			postJobUpdate(req,resp);
			break;
		case UrlConst.JOB_DELETE:
			getJobDelete(req,resp);
			break;
		default:
			break;
		}
	}

	private void getJobDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Job> jobs = service.findAll();
		
		if(jobs != null && !jobs.isEmpty()) {
			req.setAttribute("jobs", jobs);
		}
		
		req.getRequestDispatcher(JspConst.JOB_DASHBOARD).forward(req, resp);
	}
	
	private void getJobAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher(JspConst.JOB_ADD).forward(req, resp);
	}
	
	private void getJobUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idUpdate = Integer.parseInt(req.getParameter("id"));
		Job jobUpdate = service.findJobById(idUpdate);
		
		req.setAttribute("job", jobUpdate);
		req.getRequestDispatcher(JspConst.JOB_UPDATE).forward(req, resp);
	}
	
	private void postJobAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JobCreateDto dto = extractDtoFromReq(req);
		
		service.addNewJob(dto);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.JOB_DASHBOARD);
	}
	
	private void postJobUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int idUpdate = Integer.parseInt(req.getParameter("id"));
		JobCreateDto dto = extractDtoFromReq(req);
		
		service.updateJob(dto, idUpdate);
		resp.sendRedirect(req.getContextPath() +  UrlConst.JOB_DASHBOARD);
	}
	
	private void getJobDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		service.deleteJob(id);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.JOB_DASHBOARD);
	}
	
	private JobCreateDto extractDtoFromReq(HttpServletRequest req) {
		String name = req.getParameter("name");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		
		return new JobCreateDto(name, startDate, endDate);
	}
}
