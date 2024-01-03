package com.bacx.demomodbus.controller;

import com.bacx.demomodbus.service.PointService;
import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.base.ReadFunctionGroup;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.msg.ReadCoilsRequest;
import com.serotonin.modbus4j.msg.ReadCoilsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class CommController {

    @Autowired
    ModbusFactory modbusFactory;

    @Autowired
    PointService pointService;

    @Autowired
    IpParameters ipParameters;

    @GetMapping("/Hello")
    public String Hello(){
        return "Hello Modbus";
    }


    @GetMapping("/modbus")
    public String ModbusTest() throws Exception {

        int slaveId = 1;

//        ipParameters = new IpParameters();
        ipParameters.setHost("localhost");
        ModbusMaster master = modbusFactory.createTcpMaster(ipParameters, false);
        BatchRead<String> batchRead = new BatchRead<String>();


        batchRead.addLocator("hr1",
                BaseLocator.holdingRegister(slaveId, 1, DataType.TWO_BYTE_INT_SIGNED));
        batchRead.addLocator("hr2",
                BaseLocator.holdingRegister(slaveId, 2, DataType.TWO_BYTE_INT_SIGNED));
        batchRead.addLocator("hr3",
                BaseLocator.holdingRegister(slaveId, 3, DataType.TWO_BYTE_INT_SIGNED));
        batchRead.addLocator("hr6",
                BaseLocator.holdingRegister(slaveId, 6, DataType.TWO_BYTE_INT_SIGNED));
        batchRead.addLocator("hr7",
                BaseLocator.holdingRegister(slaveId, 7, DataType.TWO_BYTE_INT_SIGNED));

        batchRead.setContiguousRequests(true);

        try{
            master.init();
            readCoilTest(master, slaveId, 0, 8);
            BatchResults<String> results = master.send(batchRead);
            System.out.println(results);


        } catch (ModbusTransportException e) {
            System.out.println("通讯故障！");
            e.printStackTrace();
        }         finally {
            master.destroy();
        }

        return "Succuss!";
    }


    @GetMapping("/things")
    public String getThings(){

        return "Thing List Here:";
    }


    @GetMapping("/devices")
    public String getDevices(){
       return pointService.getPoints().toString();
    }


    public static void readCoilTest(ModbusMaster master, int slaveId, int start, int len) {
        try {
            ReadCoilsRequest request = new ReadCoilsRequest(slaveId, start, len);
            ReadCoilsResponse response = (ReadCoilsResponse) master.send(request);

            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println(Arrays.toString(response.getBooleanData()));
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }




}
