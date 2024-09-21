package com.rubicon.water.order.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.rubicon.water.order.api.OrderApi;
import com.rubicon.water.order.model.Order;
import com.rubicon.water.order.service.OrderService;

@Controller
public class OrderController implements OrderApi{

	@Autowired
	OrderService  orderService; 
	
	@Override
	public ResponseEntity<?> createOrder(int farmId, Date startDateTime, int duration) {
		try {
			int orderID = orderService.createOrder(farmId, startDateTime, duration);
			return new ResponseEntity<>(orderID, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> updateOrder(int farmId, int orderId) {
		try {
			String orderStatus = orderService.updateOrder(farmId, orderId);
			if(orderStatus!= null) {
			return new ResponseEntity<>(orderStatus, HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getOrderDetails(int farmId) {
		try {
			List<Order> orderStatusList = orderService.getOrderDetails(farmId);
			return new ResponseEntity<>(orderStatusList, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
