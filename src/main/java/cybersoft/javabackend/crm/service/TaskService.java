package cybersoft.javabackend.crm.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.javabackend.crm.dao.TaskDao;
import cybersoft.javabackend.crm.dto.TaskCreateDto;
import cybersoft.javabackend.crm.model.Status;
import cybersoft.javabackend.crm.model.Task;

public class TaskService {
	
	private TaskDao dao;
	
	public TaskService() {
		dao = new TaskDao();
	}
	
	public List<Task> findAllTask(){
		List<Task> tasks = null;
		
		try {
			tasks = dao.findAllTask();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tasks;
	}
	
	public Task findTaskById(int id) {
		Task task = null;
		
		try {
			task = dao.findTaskById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return task;
	}
	
	public void addNewTask(TaskCreateDto dto) {
		
		try {
			dao.addNewTask(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTask(TaskCreateDto dto, int idUpdate) {
		try {
			dao.updateTask(dto, idUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTask(int id) {
		try {
			dao.deleteTask(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Status> findAllStatus() {
		List<Status> statuss = null;
		
		try {
			statuss = dao.findAllStatus();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return statuss;
	}
}
