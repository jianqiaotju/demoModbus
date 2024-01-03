package com.bacx.demomodbus.entities;

import lombok.Data;

@Data
public class Connection {

    private String name;
    private String description;
    private String connectIp;
    private String port;


}
