package com.bacx.demomodbus.service;

import com.bacx.demomodbus.entities.Device;
import com.bacx.demomodbus.entities.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointServiceImpl implements PointService {
    @Override
    public List<Device> getPoints() {
        Device device=new Device();
        device.setName("RWG");
        System.out.println(device);
        List<Device> devices= new ArrayList<Device>();

        devices.add(device);

        return devices;
    }
}
