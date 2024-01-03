package com.bacx.demomodbus.entities;

import lombok.Data;

@Data
public class Device {
    private Connection connection;
    private String slaveId;
    private String name;
    private String description;

}
