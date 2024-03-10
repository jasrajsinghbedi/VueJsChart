package org.guidewheel.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "ChartData", description = "Chart data APIs")
@RequiredArgsConstructor
@RestController
public class ChartDataController {

    private final DeviceService deviceService;
    private final ChartDataService chartDataService;

    @Operation(
            description = "fetch operating loads within the timeframe provide for a device",
            summary = "fetch operating loads"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/device/load")
    public List<RangeData> getStatues(
            @RequestParam(name = "deviceId") String deviceId,
            @RequestParam(name = "unixStartDateTime") String unixStartDateTime,
            @RequestParam(name = "unixEndDateTime") String unixEndDateTime
    ) throws Exception {
        return chartDataService.getRangeDate(deviceId, unixStartDateTime , unixEndDateTime);
    }

    @Operation(
            description = "fetch all devices where guidewheel is operating",
            summary = "fetch all devices"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/devices")
    public List<Device> getAll() {
        return deviceService.getAll();
    }

    @Operation(
            description = "fetch all states with counts for each second",
            summary = "fetch all states"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/device/status/count")
    public Map<String, Integer> getStatuesCount(
            @RequestParam(name = "deviceId") String deviceId,
            @RequestParam(name = "unixStartDateTime") String unixStartDateTime,
            @RequestParam(name = "unixEndDateTime") String unixEndDateTime
    ) throws Exception {
        return chartDataService.getStatusCount(deviceId, unixStartDateTime , unixEndDateTime);
    }
}
