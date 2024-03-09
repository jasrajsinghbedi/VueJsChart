package org.guidewheel.services;

import org.guidewheel.pojos.Device;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {
    public List<Device> getAll() {
        List<Device> devices = new ArrayList<>();
        Device device1 = Device.builder().id("1").name("device1").build();
        Device device2 = Device.builder().id("2").name("device2").build();
        devices.add(device1);
        devices.add(device2);
        return devices;
    }
}
