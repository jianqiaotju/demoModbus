package com.bacx.demomodbus.service;

import com.bacx.demomodbus.entities.Device;
import com.bacx.demomodbus.entities.Point;

import java.util.HashSet;
import java.util.List;

public interface PointService {

    public List<Device> getPoints();

}

