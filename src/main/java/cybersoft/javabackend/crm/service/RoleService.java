package cybersoft.javabackend.crm.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.javabackend.crm.dao.RoleDao;
import cybersoft.javabackend.crm.dto.RoleCreateDto;
import cybersoft.javabackend.crm.model.Role;

public class RoleService {
	private RoleDao dao;
	
	public RoleService() {
		dao = new RoleDao();
	}
	
	public List<Role> findAllRole(){
		List<Role> roles = null;
		
		try {
			roles = dao.findAllRole();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return roles;
	}
	
	public Role findRoleById(int id) {
		Role role = null;
		
		try {
			role = dao.findRoleById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}
	
	public void allNewRole(RoleCreateDto dto) {
		
		try {
			dao.addNewRole(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateRole(RoleCreateDto dto, int idUpdate) {
		try {
			dao.updateRole(dto, idUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRoleById(int id) {
		try {
			dao.deleteRoleById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
