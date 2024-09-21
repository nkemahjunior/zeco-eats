package com.deliveries.kafkaListerners;


import com.deliveries.scheduleDeliveries.ScheduleOrderDelivery;
import com.zeco.shared.NewOrderShared;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReceiveOrders {

    @Autowired
    private ScheduleOrderDelivery scheduleOrderDelivery;


    //check retry logic in topicsConfig
    @KafkaListener(topics ="${topics.new-order}", groupId = "delivery-consumer-group")
    public void receiveOrders(NewOrderShared order){
        scheduleOrderDelivery.scheduleDelivery(order);
    }
}