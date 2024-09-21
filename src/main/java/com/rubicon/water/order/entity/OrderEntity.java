package com.rubicon.water.order.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//defining class name as Table name
@Table
public class OrderEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private int farmId ;
	@Column
	private Date startDateTime;
	@Column
	private Date stopDateTime;
	@Column
	private int duration;
	@Column
	private String orderStatus;
	@Column
	private Date CreateDateTime;
	@Column
	private Date updateDateTime;

	public Date getCreateDateTime() {
		return CreateDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		CreateDateTime = createDateTime;
	}
	public Date getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public int getId() {
		return id;
	}
	public int getFarmId() {
		return farmId;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public Date getStopDateTime() {
		return stopDateTime;
	}
	public void setStopDateTime(Date stopDateTime) {
		this.stopDateTime = stopDateTime;
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
	
}
