package org.guidewheel.services;

import org.guidewheel.pojos.RangeData;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Service
public class ChartDataService {
    public List<RangeData> getRangeDate(String deviceId, String unixStartDateTime, String unixEndDateTime) {
        return getRangeData(deviceId);
    }

    public Map<String, Integer> getStatusCount(String deviceId, String unixStartDateTime, String unixEndDateTime) {

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

    private List<RangeData> getRangeData(String deviceId) {
        List<RangeData> rangeDataList = new ArrayList<>();

        Instant now = Instant.now();

        RangeData rangeData1 = RangeData.builder().deviceId(deviceId).start(now).power("100").state("IDLE").end(now.plus(4, ChronoUnit.DAYS)).build();
        RangeData rangeData2 = RangeData.builder().deviceId(deviceId).start(now.minus(2, ChronoUnit.DAYS)).power("20").state("IDLE").end(now.plus(5, ChronoUnit.DAYS)).build();
        RangeData rangeData3 = RangeData.builder().deviceId(deviceId).start(now.minus(6, ChronoUnit.DAYS)).power("48").state("UNLOADED").end(now.plus(5, ChronoUnit.DAYS)).build();
        RangeData rangeData4 = RangeData.builder().deviceId(deviceId).start(now.minus(4, ChronoUnit.DAYS)).power("78").state("OFF").end(now.plus(5, ChronoUnit.DAYS)).build();
        RangeData rangeData5 = RangeData.builder().deviceId(deviceId).start(now.minus(2, ChronoUnit.DAYS)).power("45").state("LOADED").end(now.plus(5, ChronoUnit.DAYS)).build();
        RangeData rangeData6 = RangeData.builder().deviceId(deviceId).start(now.minus(7, ChronoUnit.DAYS)).power("89").state("UNLOADED").end(now.plus(5, ChronoUnit.DAYS)).build();
        RangeData rangeData7 = RangeData.builder().deviceId(deviceId).start(now.minus(8, ChronoUnit.DAYS)).power("23").state("IDLE").end(now.plus(5, ChronoUnit.DAYS)).build();

        RangeData rangeData8 = RangeData.builder().deviceId(deviceId).start(now).power("2").state("OFF").end(now.plus(4, ChronoUnit.DAYS)).build();
        RangeData rangeData9 = RangeData.builder().deviceId(deviceId).start(now.minus(1, ChronoUnit.DAYS)).power("10").state("IDLE").end(now.plus(8, ChronoUnit.DAYS)).build();
        RangeData rangeData10 = RangeData.builder().deviceId(deviceId).start(now.minus(9, ChronoUnit.DAYS)).power("67").state("UNLOADED").end(now.plus(9, ChronoUnit.DAYS)).build();
        RangeData rangeData11 = RangeData.builder().deviceId(deviceId).start(now.minus(7, ChronoUnit.DAYS)).power("67").state("LOADED").end(now.plus(10, ChronoUnit.DAYS)).build();
        RangeData rangeData12 = RangeData.builder().deviceId(deviceId).start(now.minus(90, ChronoUnit.DAYS)).power("10").state("UNLOADED").end(now.plus(40, ChronoUnit.DAYS)).build();
        RangeData rangeData13 = RangeData.builder().deviceId(deviceId).start(now.minus(5, ChronoUnit.DAYS)).power("30").state("UNLOADED").end(now.plus(60, ChronoUnit.DAYS)).build();
        RangeData rangeData14 = RangeData.builder().deviceId(deviceId).start(now.minus(3, ChronoUnit.DAYS)).power("235").state("IDLE").end(now.plus(3, ChronoUnit.DAYS)).build();

        if(deviceId.equals("1")) {
            rangeDataList.add(rangeData1);
            rangeDataList.add(rangeData2);
            rangeDataList.add(rangeData3);
            rangeDataList.add(rangeData4);
            rangeDataList.add(rangeData5);
            rangeDataList.add(rangeData6);
            rangeDataList.add(rangeData7);
        } else {
            rangeDataList.add(rangeData8);
            rangeDataList.add(rangeData9);
            rangeDataList.add(rangeData10);
            rangeDataList.add(rangeData11);
            rangeDataList.add(rangeData12);
            rangeDataList.add(rangeData13);
            rangeDataList.add(rangeData14);
        }

        return rangeDataList;
    }
}
