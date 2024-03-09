package org.guidewheel.controllers;

import lombok.RequiredArgsConstructor;
import org.guidewheel.pojos.Device;
import org.guidewheel.pojos.RangeData;
import org.guidewheel.services.ChartDataService;
import org.guidewheel.services.DeviceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChartDataController {

    private final DeviceService deviceService;
    private final ChartDataService chartDataService;
    @GetMapping("/status")
    public List<RangeData> getStatues(@RequestParam(value = "name", defaultValue = "World") String name) {
        return chartDataService.getRangeDate();
    }

    @GetMapping("/devices")
    public List<Device> getAll() {
        return deviceService.getAll();
    }
}
