package com.corona.orderdelivery.delivery;

import com.corona.orderdelivery.delivery.exceptions.DeliveryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/corona")
public class DeliveryController {
    private final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    private DeliveryService deliveryService;
    private DeliveryRepository deliveryRepository;

    DeliveryController(DeliveryService deliveryService, DeliveryRepository deliveryRepository) {
        this.deliveryService = deliveryService;
        this.deliveryRepository = deliveryRepository;
    }
    @GetMapping("/deliveries/{deliveryId}/routes")
    ResponseEntity<Route> getDeliveryRoute(@PathVariable Integer deliveryId) throws DeliveryNotFoundException {
            Route route = deliveryService.getDeliveryRoute(deliveryId);
            return ResponseEntity.ok(route);
    }

    @PostMapping("/deliveries")
    ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery){
        deliveryRepository.save(delivery);
        return new ResponseEntity<>(delivery, HttpStatus.CREATED);
    }
}
