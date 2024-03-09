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
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ChartDataController {

    private final DeviceService deviceService;
    private final ChartDataService chartDataService;
    @GetMapping("/device/status")
    public List<RangeData> getStatues(
            @RequestParam(name = "deviceId") String deviceId,
            @RequestParam(name = "unixStartDateTime") String unixStartDateTime,
            @RequestParam(name = "unixEndDateTime") String unixEndDateTime
    ) {
        return chartDataService.getRangeDate(deviceId, unixStartDateTime , unixEndDateTime);
    }

    @GetMapping("/devices")
    public List<Device> getAll() {
        return deviceService.getAll();
    }

    @GetMapping("/device/status/count")
    public Map<String, Integer> getStatuesCount(
            @RequestParam(name = "deviceId") String deviceId,
            @RequestParam(name = "unixStartDateTime") String unixStartDateTime,
            @RequestParam(name = "unixEndDateTime") String unixEndDateTime
    ) {
        return chartDataService.getStatusCount(deviceId, unixStartDateTime , unixEndDateTime);
    }
}
