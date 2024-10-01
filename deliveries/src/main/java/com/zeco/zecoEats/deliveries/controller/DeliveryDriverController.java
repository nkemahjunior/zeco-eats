package com.deliveries.controller;


import com.deliveries.dtos.*;
import com.deliveries.service.DeliveryDriversService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("api/deliveries")
public class DeliveryDriverController {


    @Autowired
    private DeliveryDriversService deliveryDriversService;

    @CircuitBreaker(name = "user-service", fallbackMethod = "errorGettingUser")
    @PostMapping("/newDriver")
    public ResponseEntity<CreateDeliveryDriverDto> createNewDriverEndpoint(@RequestBody CreateDeliveryDriverDto createDeliveryDriverDto){
        return ResponseEntity.ok(deliveryDriversService.saveNewDeliveryDriver(createDeliveryDriverDto));
    }

    public ResponseEntity<CreateDeliveryDriverDto> errorGettingUser(Exception ex) throws Exception {
       if(ex instanceof NoSuchElementException)
            throw new NoSuchElementException(ex.getMessage());
       else
           throw new Exception();
    }

    @PostMapping("/driver/available")
    public ResponseEntity<Void> addDriverAvailableForWorkEndpoint(@RequestBody AddAvailableDriverDTO addAvailableDriverDTO){
        deliveryDriversService.addDriverAvailableForWork(addAvailableDriverDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/driver/updateLocation")
    public ResponseEntity<Void> updateDriversLocationEndpoint(@RequestBody UpdateDriverLocationDTO updateDriverLocationDTO){
        deliveryDriversService.updateDriverLocationAndHeartBeat(updateDriverLocationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/driver/acceptDelivery")
    public ResponseEntity<Void> acceptToDeliverOrderEndpoint(@RequestBody AcceptOrDeclineDeliveryDTO acceptDeliveryDTO){
        deliveryDriversService.acceptToDeliverOrder(acceptDeliveryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/driver/declineDelivery")
    public ResponseEntity<Void> declineToDeliverOrderEndpoint(@RequestBody AcceptOrDeclineDeliveryDTO denyDeliveryDTO){
        deliveryDriversService.declineToDeliverOrder(denyDeliveryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}