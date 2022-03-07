package cybersoft.javabackend.crm.dto;

public class RoleCreateDto {
	private String name;
	private String createDate;
		
	public RoleCreateDto(String name, String createDate) {
		super();
		this.name = name;
		this.createDate = createDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
}
