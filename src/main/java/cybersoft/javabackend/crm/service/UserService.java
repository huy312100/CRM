package cybersoft.javabackend.crm.service;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import cybersoft.javabackend.crm.dao.UserDao;
import cybersoft.javabackend.crm.dto.UserCreateDto;
import cybersoft.javabackend.crm.model.User;
import cybersoft.javabackend.crm.util.PwdUtils;

public class UserService {
	private UserDao dao;
	
	
	public UserService() {
		dao = new UserDao();
	}
	
	public List<User> findAll(){
		List<User> users = null;		
		try {
			users = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public User findUserById(int id){
		User user = null;
		try {
			user = dao.findUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void deleteById(int id) {
		try {
			dao.deleteById(id);			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void add(UserCreateDto dto) {
		String hasedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hasedPwd);
		try {
			dao.add(dto);			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(UserCreateDto dto, int idUpdate) {
		String hasedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hasedPwd);
		
		try {
			dao.update(dto, idUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
