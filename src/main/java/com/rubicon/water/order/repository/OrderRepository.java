package com.rubicon.water.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rubicon.water.order.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{
	
	public List<OrderEntity> findOrderEntitiesByFarmId(int farmId);
	public List<OrderEntity> findOrderEntitiesByOrderStatusIn(List<String> orderStatus);

}
