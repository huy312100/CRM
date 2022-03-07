package cybersoft.javabackend.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import cybersoft.javabackend.crm.dto.JobCreateDto;
import cybersoft.javabackend.crm.model.Job;
import cybersoft.javabackend.crm.model.Role;
import cybersoft.javabackend.crm.repository.MySQLConnection;

public class JobDao {
	public List<Job> findAll() throws SQLException{
		List<Job> jobs = new LinkedList<>();
		Connection connection = MySQLConnection.getConnection();
		
		String query = "SELECT id , job_name, start_date, end_date FROM jobs";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Job job = new Job();
				
				job.setId(resultSet.getInt("id"));
				job.setName(resultSet.getNString("job_name"));
				job.setStartDate(resultSet.getTimestamp("start_date"));
				job.setEndDate(resultSet.getTimestamp("end_date"));
				
				jobs.add(job);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return jobs;
	}
	
	public Job findJobById(int id) throws SQLException {
		Connection connection = MySQLConnection.getConnection();
		Job job = null;
		
		String query = "SELECT id, job_name, start_date, end_date FROM jobs WHERE id = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				job = new Job();
				job.setId(resultSet.getInt("id"));
				job.setName(resultSet.getNString("job_name"));
				job.setStartDate(resultSet.getTimestamp("start_date"));
				job.setEndDate(resultSet.getTimestamp("end_date"));
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return job;
	}
	
	public void addNewJob(JobCreateDto dto) throws SQLException {
		Connection connection = MySQLConnection.getConnection();
		String query = "INSERT INTO jobs(job_name, start_date, end_date) VALUES (?,?,?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setNString(1, dto.getName());
			statement.setNString(2, dto.getStartDate());
			statement.setNString(3, dto.getEndDate());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public void updateJob(JobCreateDto dto, int idUpdate) throws SQLException {
		Connection connection = MySQLConnection.getConnection();
		
		String query = "UPDATE jobs SET job_name = ?, start_date = ?, end_date = ? WHERE id = ?";
		int result = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, dto.getName());
			statement.setString(2, dto.getStartDate());
			statement.setString(3, dto.getEndDate());
			statement.setInt(4, idUpdate);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public void deleteJobById(int id) throws SQLException {
		Connection connection = MySQLConnection.getConnection();
		
		String query = "DELETE FROM jobs WHERE id = ?";
		
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
}
