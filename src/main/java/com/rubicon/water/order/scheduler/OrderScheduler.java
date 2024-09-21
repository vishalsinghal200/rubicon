package com.rubicon.water.order.scheduler;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rubicon.water.order.entity.OrderEntity;
import com.rubicon.water.order.repository.OrderRepository;

@Component
public class OrderScheduler {

	@Autowired 
	OrderRepository OrderRepository;

	@Scheduled(cron = "* * * * * *")
	public void processOrders() {
		
		String status[]= new String[] { "Requested", "InProgress" };
		List<OrderEntity>  orderEntityList  = OrderRepository.findOrderEntitiesByOrderStatusIn(Arrays.asList(status));
		for(OrderEntity orderEntity:orderEntityList) {
			if("Requested".equals(orderEntity.getOrderStatus())&& getCurrentDateHourandMins().equals(orderEntity.getStartDateTime().toString())) {
				orderEntity.setOrderStatus("InProgress");
				orderEntity.setUpdateDateTime(new Date());
				OrderRepository.save(orderEntity);
				System.out.println(" Marked InProgress Order ID::"+orderEntity.getId());
			}
			else if("InProgress".equals(orderEntity.getOrderStatus())&& getCurrentDateHourandMins().equals(orderEntity.getStopDateTime().toString())) {
				orderEntity.setOrderStatus("Delivered");
				orderEntity.setUpdateDateTime(new Date());
				OrderRepository.save(orderEntity);
				System.out.println("Marked Delivered Order ID::"+orderEntity.getId());
			}
		}
	
	}
	
 private String getCurrentDateHourandMins() {
    Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd H:mm:ss");
		String strDate = formatter.format(currentDateTime);
		return strDate+".0";
	}
	
}
