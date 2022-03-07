package cybersoft.javabackend.crm.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.javabackend.crm.dao.JobDao;
import cybersoft.javabackend.crm.dto.JobCreateDto;
import cybersoft.javabackend.crm.model.Job;

public class JobService {
	private JobDao dao;
	
	public JobService() {
		dao = new JobDao();
	}
	
	public List<Job> findAll(){
		List<Job> jobs = null;
		
		try {
			jobs = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jobs;
	}
	
	public Job findJobById(int id) {
		Job job = null;
		try {
			job = dao.findJobById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return job;
	}
	
	public void addNewJob(JobCreateDto dto) {
		try {
			dao.addNewJob(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateJob(JobCreateDto dto, int idUpdate) {
		try {
			dao.updateJob(dto, idUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteJob(int id) {
		try {
			dao.deleteJobById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
