package cybersoft.javabackend.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.javabackend.crm.dto.TaskCreateDto;
import cybersoft.javabackend.crm.model.Job;
import cybersoft.javabackend.crm.model.Status;
import cybersoft.javabackend.crm.model.Task;
import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.repository.MySQLConnection;

public class TaskDao {
	
	public List<Task> findAllTask() throws SQLException{
		List<Task> tasks = new LinkedList<>();
		List<User> users = new ArrayList<>();
		List<Job> jobs = new ArrayList<>();
		List<Status> statuss = new ArrayList<>();
		Connection connection = MySQLConnection.getConnection();
		
		String query = "SELECT t.id as id, t.task_name as task_name, t.start_date as start_date, t.end_date as end_date, "
					+ "u.id as userid, u.fullname as fullname, "
					+ "s.id as status_id, s.status_name as status_name, s.createDate as createDate, "
					+ "j.id as job_id,  j.job_name as job_name "
					+ "FROM tasks t, users u, status s, jobs j " + "WHERE t.userid = u.id " + "AND t.job_id = j.id "
					+ "AND t.status_id = s.id "
					+ "ORDER BY t.id";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Task task =  new Task();
				
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getNString("task_name"));
				task.setStartDate(resultSet.getTimestamp("start_date"));
				task.setEndDate(resultSet.getTimestamp("end_date"));
				
				int userId = resultSet.getInt("userid");
				User user = getUserFromList(users, userId);
				if(user == null) {
					user = new User();
					user.setId(resultSet.getInt("userid"));
					user.setFullname(resultSet.getNString("fullname"));
					
					users.add(user);
				}
				task.setUser(user);
				
				int statusId = resultSet.getInt("status_id");
				Status status = getStatusFromList(statuss, statusId);
				if(status == null) {
					status = new Status();
					status.setId(resultSet.getInt("status_id"));
					status.setName(resultSet.getNString("status_name"));
					status.setCreateDate(resultSet.getTimestamp("createDate"));
					
					statuss.add(status);
				}
				task.setStatus(status);
				
				int jobId = resultSet.getInt("job_id");
				Job job = getJobFromList(jobs, jobId);
				
				if(job == null) {
					job = new Job();
					job.setId(resultSet.getInt("job_id"));
					job.setName(resultSet.getNString("job_name"));
					
					jobs.add(job);
				}
				task.setJob(job);
				
				tasks.add(task);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return tasks;
	}
	
	public Task findTaskById(int id) throws SQLException {
		List<User> users = new ArrayList<>();
		List<Job> jobs = new ArrayList<>();
		List<Status> statuss = new ArrayList<>();
		Task task = null;
		Connection connection = MySQLConnection.getConnection();
		
		String query = "SELECT t.id as id, t.task_name as task_name, t.start_date as start_date, t.end_date as end_date, "
				+ "u.id as userid, u.fullname as fullname, "
				+ "s.id as status_id, s.status_name as status_name, s.createDate as createDate, "
				+ "j.id as job_id,  j.job_name as job_name "
				+ "FROM tasks t, users u, status s, jobs j " + "WHERE t.userid = u.id " + "AND t.job_id = j.id "
				+ "AND t.status_id = s.id "
				+ "AND t.id = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				task = new Task();
				
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getNString("task_name"));
				task.setStartDate(resultSet.getTimestamp("start_date"));
				task.setEndDate(resultSet.getTimestamp("end_date"));
				
				int userId = resultSet.getInt("userid");
				User user = getUserFromList(users, userId);
				if(user == null) {
					user = new User();
					user.setId(resultSet.getInt("userid"));
					user.setFullname(resultSet.getNString("fullname"));
					
					users.add(user);
				}
				task.setUser(user);
				
				int statusId = resultSet.getInt("status_id");
				Status status = getStatusFromList(statuss, statusId);
				if(status == null) {
					status = new Status();
					status.setId(resultSet.getInt("status_id"));
					status.setName(resultSet.getNString("status_name"));
					status.setCreateDate(resultSet.getTimestamp("createDate"));
					
					statuss.add(status);
				}
				task.setStatus(status);
				
				int jobId = resultSet.getInt("job_id");
				Job job = getJobFromList(jobs, jobId);
				
				if(job == null) {
					job = new Job();
					job.setId(resultSet.getInt("job_id"));
					job.setName(resultSet.getNString("job_name"));
					
					jobs.add(job);
				}
				task.setJob(job);
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return task;
	}
	
	
	private Status getStatusFromList(List<Status> statuss, int statusId) {
		for(Status status : statuss) {
			if(status.getId() == statusId) {
				return status;
			}
		}
		return null;
	}
	
	private Job getJobFromList(List<Job> jobs, int jobId) {
		for(Job job : jobs) {
			if(job.getId() == jobId) {
				return job;
			}
		}
		return null;
	}
	
	private User getUserFromList(List<User> users, int userId) {
		for(User user : users) {
			if(user.getId() == userId) {
				return user;
			}
		}
		return null;
	}
	
	public void addNewTask(TaskCreateDto dto) throws SQLException {
		Connection connection = MySQLConnection.getConnection();
		
		String query = "INSERT INTO tasks(task_name, start_date, end_date, userid, status_id, job_id )"
						+ "VALUES(?,?,?,?,?,?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setNString(1, dto.getName());
			statement.setNString(2, dto.getStartDate());
			statement.setNString(3, dto.getEndDate());
			statement.setInt(4, dto.getUserId());
			statement.setInt(5, dto.getStatusId());
			statement.setInt(6, dto.getJobId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
				
	}
	
	public void updateTask(TaskCreateDto dto, int idUpdate) throws SQLException {
		Connection connection = MySQLConnection.getConnection();
		String query = "UPDATE tasks SET task_name = ?, start_date = ?, end_date = ?, userid = ?, status_id = ?, job_id = ? WHERE id = ?";
		int result = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, dto.getName());
			statement.setNString(2, dto.getStartDate());
			statement.setNString(3, dto.getEndDate());
			statement.setInt(4, dto.getUserId());
			statement.setInt(5, dto.getStatusId());
			statement.setInt(6, dto.getJobId());
			statement.setInt(7, idUpdate);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public void deleteTask(int id) throws SQLException {
		Connection connection = MySQLConnection.getConnection();
		String query = "DELETE FROM tasks WHERE id = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public List<Status> findAllStatus() throws SQLException{
		List<Status> statuss = new LinkedList<Status>();
		
		Connection connection = MySQLConnection.getConnection();
		String query = "SELECT id , status_name, createDate FROM status";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Status status = new Status();
				status.setId(resultSet.getInt("id"));
				status.setName(resultSet.getNString("status_name"));
				status.setCreateDate(resultSet.getTimestamp("createDate"));
				
				statuss.add(status);
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return statuss;
	}
}
