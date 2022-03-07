package cybersoft.javabackend.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import cybersoft.javabackend.crm.dto.RoleCreateDto;
import cybersoft.javabackend.crm.model.Role;
import cybersoft.javabackend.crm.repository.MySQLConnection;

public class RoleDao {
	
	public List<Role> findAllRole() throws SQLException{
		List<Role> roles = new LinkedList<>();
		
		Connection connection = MySQLConnection.getConnection();
		
		String query = "SELECT id, role_name, createDate FROM roles";
		
		try {
			PreparedStatement statement =  connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Role role = new Role();
				
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("role_name"));
				role.setCreateDate(resultSet.getTimestamp("createDate"));
				
				roles.add(role);
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		
		return roles;
	}
	
	public Role findRoleById(int id) throws SQLException {
		Role role = null;
		
		Connection connection = MySQLConnection.getConnection();
		
		String query = "SELECT id, role_name, createDate FROM roles WHERE id = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getNString("role_name"));
				role.setCreateDate(resultSet.getTimestamp("createDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			connection.close();
		}
		
		return role;
	}
	
	public void addNewRole(RoleCreateDto dto) throws SQLException {
		Connection connection = MySQLConnection.getConnection();
		
		String query = "INSERT INTO roles(role_name, createDate) VALUES(?,?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setNString(1, dto.getName());
			statement.setNString(2, dto.getCreateDate());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public void updateRole(RoleCreateDto dto, int idUpdate) throws SQLException {
		Connection connection = MySQLConnection.getConnection();
		String query = "UPDATE roles SET role_name = ?, createDate = ? WHERE id = ?";
		int result = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, dto.getName());
			statement.setString(2, dto.getCreateDate());
			statement.setInt(3, idUpdate);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		}finally {
			connection.close();
		}	
	}
	public void deleteRoleById(int id) throws SQLException {
		Connection connection = MySQLConnection.getConnection();
		String query = "DELETE FROM roles WHERE id = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
	}
}
