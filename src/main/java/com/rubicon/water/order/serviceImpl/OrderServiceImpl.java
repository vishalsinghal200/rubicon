package com.rubicon.water.order.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubicon.water.order.entity.OrderEntity;
import com.rubicon.water.order.model.Order;
import com.rubicon.water.order.repository.OrderRepository;
import com.rubicon.water.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired 
	OrderRepository OrderRepository;


	@Override
	public int createOrder(int farmId, Date dateTime, int duration) {
		int id=0;
		Date startDateTime = getDateHourandMins(dateTime);
		Date  stopDateTime = getDateHourandMins(getStopDateTime(duration,startDateTime)); 
		System.out.println("startDateTime-"+startDateTime + "::stopDateTime -"+stopDateTime+ ":: Creent DateTime-"+new Date());
		List<OrderEntity> orderEntityList = OrderRepository.findOrderEntitiesByFarmId(farmId);
		boolean isRecord = false;
		for(OrderEntity orderEntity:orderEntityList) {
			if(isWithinRange(startDateTime,getDateHourandMins(orderEntity.getStartDateTime()),getDateHourandMins(orderEntity.getStopDateTime()))) {
				isRecord=true;
				id=	orderEntity.getId();
				System.out.println("Already have order for same time Range::"+id);
			}
		}
		if(!isRecord) {
			OrderEntity orderEntity = new OrderEntity();
			orderEntity.setFarmId(farmId);
			orderEntity.setStartDateTime(startDateTime);
			orderEntity.setStopDateTime(stopDateTime);
			orderEntity.setDuration(duration);
			orderEntity.setOrderStatus("Requested");
			orderEntity.setCreateDateTime(new Date());
			OrderRepository.save(orderEntity);
			id= orderEntity.getId();
		}
		return id;
	}

	private boolean isWithinRange(Date startDateTime, Date startDate,Date endDate) {
		boolean isrange = false;
		if(startDateTime.getTime() >= startDate.getTime() &&
				startDateTime.getTime() <= endDate.getTime()) {
			isrange =true;
		}
		
		return isrange;
	}
	@Override
	public String updateOrder(int farmId, int orderId) {

		String status = null;
		Optional<OrderEntity> optOrderEntity = OrderRepository.findById(orderId);
		if(optOrderEntity.isPresent()) {
			OrderEntity orderEntity = optOrderEntity.get();    
			orderEntity.setOrderStatus("CANCEL");
			OrderRepository.save(orderEntity);
			status = "UPDATED";
		}
		return status;
	}

	@Override
	public List<Order> getOrderDetails(int farmId) {
		List<Order>  orderList= new ArrayList<>();
		List<OrderEntity> orderEntityList = OrderRepository.findOrderEntitiesByFarmId(farmId);
		for(OrderEntity orderEntity:orderEntityList) {
			Order order = new Order();
			order.setOrderStatus(orderEntity.getOrderStatus());
			order.setFarmId(orderEntity.getFarmId());
			order.setStartDateTime(orderEntity.getStartDateTime());
			order.setDuration(orderEntity.getDuration());
			order.setOrderDiscription(getStatus(orderEntity.getOrderStatus()));
			orderList.add(order);
		}
		return orderList;
	}

	private Date getDateHourandMins(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    date  = cal.getTime();
		return date;
	}
	private Date getStopDateTime(int duration,Date dNow) {
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(dNow);
		  cal.add(Calendar.MINUTE, duration);
		  dNow = cal.getTime();
		  return dNow;
	}
	private String getStatus(String status) {
		HashMap<String, String> statusHM = new HashMap<>();
		statusHM.put("Requested", "Order has been placed but not yet delivered.");
		statusHM.put("InProgress", "Order is being delivered right now.");
		statusHM.put("Delivered", "Order has been delivered.");
		statusHM.put("Cancelled", "Order was cancelled before delivery.");
		return statusHM.get(status);
	}
}
