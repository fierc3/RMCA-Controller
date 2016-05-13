package ch.robot.vo;

public class RobotVO {

	
	public RobotVO(String name, String ip){
		this.ip = ip;
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public String getIp(){
		return ip;
	}
	String name;
	String ip;
}
