package com.rubicon.water.order.model;

import java.util.Date;

public class Order {

	private int farmId ;
	private Date startDateTime;
	private int duration;
	private String orderStatus;
	private String orderDiscription;

	public int getFarmId() {
		return farmId;
	}
	public void setFarmId(int farmId) {
		this.farmId = farmId;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderDiscription() {
		return orderDiscription;
	}
	public void setOrderDiscription(String orderDiscription) {
		this.orderDiscription = orderDiscription;
	}
	
	
	
}
