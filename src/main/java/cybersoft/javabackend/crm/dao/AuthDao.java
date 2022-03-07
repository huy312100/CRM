package cybersoft.javabackend.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cybersoft.javabackend.crm.dto.UserLoginDto;
import cybersoft.javabackend.crm.repository.MySQLConnection;

public class AuthDao {

	public int login(String email, String password) throws SQLException {
		
		Connection connection = MySQLConnection.getConnection();
		String query = "SELECT count(id) AS result FROM users WHERE email = ? AND user_password = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(!resultSet.next()) {
				return 0;
			}
			return resultSet.getInt(1);
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return 0;
	}

	public UserLoginDto findUserLogin(String email) throws SQLException {
		Connection connection = MySQLConnection.getConnection();
		String query = "SELECT email, user_password FROM users WHERE email = ?";
		UserLoginDto dto = null;
		
		try {
			PreparedStatement statment = connection.prepareStatement(query);
			statment.setString(1, email);
			
			ResultSet resultSet = statment.executeQuery();
			
			if(resultSet.next())
			{
				dto = new UserLoginDto();
				dto.setEmail(resultSet.getString("email"));
				dto.setPassword(resultSet.getString("user_password"));
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	
		return dto;
	}
	
	
}
