package org.guidewheel.services;

import lombok.RequiredArgsConstructor;
import org.guidewheel.pojos.RangeData;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ChartDataService {

    private final CsvService csvService;
    public List<RangeData> getRangeDate(String deviceId, String unixStartDateTime, String unixEndDateTime) throws Exception {
        return getRangeData(deviceId);
    }

    public Map<String, Integer> getStatusCount(String deviceId, String unixStartDateTime, String unixEndDateTime) throws Exception {

        List<RangeData> rangeData = getRangeData(deviceId);
        Map<String, Integer> statusCounts = new HashMap<>();
        Arrays.stream(MachineStatus.values()).forEach(machineStatus -> statusCounts.put(machineStatus.name(), 0));
        rangeData.forEach(data -> {

            Instant startInstant = data.start();
            Instant endInstant = data.end();
            statusCounts.put(data.state(), statusCounts.get(data.state()) + (int)Duration.between(startInstant, endInstant).getSeconds());
        });

        return statusCounts;
    }

    private List<RangeData> getRangeData(String deviceId) throws Exception {
        List<RangeData> rangeDataList = csvService.read("data.csv");
        return rangeDataList.stream().filter(r -> r.deviceId().equals(deviceId)).toList();
    }
}
