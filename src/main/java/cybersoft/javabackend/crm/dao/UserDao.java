package cybersoft.javabackend.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.javabackend.crm.dto.UserCreateDto;
import cybersoft.javabackend.crm.model.Role;
import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.repository.MySQLConnection;

public class UserDao {
	public List<User> findAll() throws SQLException{
		List<User> users = new LinkedList<User>();
		List<Role> roles = new ArrayList<>();
		
		Connection connection = MySQLConnection.getConnection();
		String query = "SELECT u.id as id, u.fullname as fullname, u.email as email, "
				+ "u.avatar as avatar, r.id as roleId, r.role_name as role_name, r.createDate as createDate "
				+ "FROM users u, roles r WHERE u.roleId = r.id";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				User user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setFullname(resultSet.getString("fullname"));
				user.setEmail(resultSet.getString("email"));
				user.setAvatar(resultSet.getString("avatar"));
				
				int roleId = resultSet.getInt("roleId");
				Role role = getRoleFromList(roles, roleId);
				
				if(role == null) {
					role = new Role();
					role.setId(resultSet.getInt("roleId"));
					role.setName(resultSet.getString("role_name"));
					role.setCreateDate(resultSet.getTimestamp("createDate"));
					roles.add(role);
				}
				
				user.setRole(role);
				
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return users;
	}
	
	public User findUserById(int id) throws SQLException {
		List<Role> roles = new ArrayList<>();
		User user = null;
		Connection connection = MySQLConnection.getConnection();
		String query = "SELECT u.id as id, u.fullname as fullname, u.email as email, u.user_password as user_password,"
				+ "u.avatar as avatar, r.id as roleId, r.role_name as role_name, r.createDate as createDate "
				+ "FROM users u, roles r WHERE u.roleId = r.id " + "AND u.id = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setFullname(resultSet.getString("fullname"));
				user.setPassword(resultSet.getString("user_password"));
				user.setEmail(resultSet.getString("email"));
				user.setAvatar(resultSet.getString("avatar"));
				
				int roleId = resultSet.getInt("roleId");
				Role role = getRoleFromList(roles, roleId);
				
				if(role == null) {
					role = new Role();
					role.setId(resultSet.getInt("roleId"));
					role.setName(resultSet.getString("role_name"));
					role.setCreateDate(resultSet.getTimestamp("createDate"));
					roles.add(role);
				}
				
				user.setRole(role);
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		}	finally {
			connection.close();
		}
		return user;
	}

	private Role getRoleFromList(List<Role> roles, int roleId) {
			for(Role role : roles) {
				if(role.getId() == roleId) {
					return role;
				}
			}
		return null;
	}

	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM users WHERE id = ?";
		Connection connection = MySQLConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
		}catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public void add(UserCreateDto dto) throws SQLException {
		String query = "INSERT INTO users(fullname, email, user_password, avatar, roleId)"
						+ "VALUES(?,?,?,?,?)";
		Connection connection = MySQLConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, dto.getFullname());
			statement.setNString(2, dto.getEmail());
			statement.setNString(3, dto.getPassword());
			statement.setNString(4, dto.getAvatar());
			statement.setInt(5, dto.getRoleId());
			
			statement.executeUpdate();
		}catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public void update(UserCreateDto dto, int idUpdate) throws SQLException {
		Connection connection = MySQLConnection.getConnection();
		String query = "UPDATE users SET fullname = ?, email = ?, user_password = ?, avatar = ?, roleId = ? WHERE id = ?";
		int result = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, dto.getFullname());
			statement.setNString(2, dto.getEmail());
			statement.setNString(3, dto.getPassword());
			statement.setNString(4, dto.getAvatar());
			statement.setInt(5, dto.getRoleId());
			statement.setInt(6, idUpdate);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		}finally {
			connection.close();
		}
	}
	
	
}
