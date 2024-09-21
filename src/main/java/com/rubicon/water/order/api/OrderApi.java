package com.rubicon.water.order.api;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;

@Api(value="OrderApi",tags ="water OrderApi")
@RequestMapping("/")
public interface OrderApi {

	@PostMapping("/createOrder")
	public ResponseEntity<?> createOrder(@RequestParam(value ="farmId" ,required =true) int farmId,
			@RequestParam(value ="StartDateTime",required =true ) Date StartDateTime, @RequestParam(value ="duration",required =true) int duration);
	
	@PutMapping("/updateOrder")
	public ResponseEntity<?> updateOrder(@RequestParam(value ="farmId",required =true) int farmId,
			@RequestParam(value ="orderId",required =true) int orderId);

	@GetMapping("/getOrderDetails/{farmId}")
	public ResponseEntity<?> getOrderDetails(@PathVariable(name="farmId",required =true) int farmId) ;
}
