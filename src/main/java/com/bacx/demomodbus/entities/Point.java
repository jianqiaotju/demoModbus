package com.bacx.demomodbus.entities;

import lombok.Data;

@Data
public class Point {

    private String deviceName;
    private String name;
    private String description;
    private String pointType;
    private String regType;
    private String regOffset;
    private String regSubIndex;
    private String unit;
    private String rwType;



}
