package cybersoft.javabackend.crm.dto;

public class TaskCreateDto {
	private String name;
	private String startDate;
	private String endDate;
	private int userId;
	private int statusId;
	private int jobId;
		
	public TaskCreateDto() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	public TaskCreateDto(String name, String startDate, String endDate, int userId, int statusId, int jobId) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.statusId = statusId;
		this.jobId = jobId;
	}
	
	
}
