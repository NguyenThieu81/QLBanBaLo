package com.example.QLBanBalo.services;

import com.example.QLBanBalo.entity.Shipment;
import com.example.QLBanBalo.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentServices {
    @Autowired
    private ShipmentRepository shipmentRepository;

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Shipment getShipmentById(Long id) {
        return shipmentRepository.findById(id).orElse(null);
    }

    public void addShipment(Shipment shipment) {
        shipmentRepository.save(shipment);
    }

    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }

    public void updateShipment(Shipment shipment) {
        shipmentRepository.save(shipment);
    }
}
