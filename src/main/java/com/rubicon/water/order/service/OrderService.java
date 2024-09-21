package com.rubicon.water.order.service;

import java.util.Date;
import java.util.List;
import com.rubicon.water.order.model.Order;


public interface OrderService {
	
	public int createOrder(int farmId, Date StartDateTime, int duration);
	public String updateOrder(int farmId, int orderId);
	public List<Order> getOrderDetails(int farmId);

}
