package org.guidewheel.services;

import org.guidewheel.pojos.RangeData;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ChartDataService {
    public List<RangeData> getRangeDate(String deviceId, String unixStartDateTime, String unixEndDateTime) {
        return getRangeData(deviceId);
    }

    public Map<String, Integer> getStatusCount(String deviceId, String unixStartDateTime, String unixEndDateTime) {

        List<RangeData> rangeData = getRangeData(deviceId);
        Map<String, Integer> statusCounts = new HashMap<>();
        Arrays.stream(MachineStatus.values()).forEach(machineStatus -> statusCounts.put(machineStatus.name(), 0));
        rangeData.forEach(data -> statusCounts.put(data.state(), statusCounts.get(data.state()) + 1));

        return statusCounts;
    }

    private static List<RangeData> getRangeData(String deviceId) {
        List<RangeData> rangeDataList = new ArrayList<>();

        Instant now = Instant.now();

        RangeData rangeData1 = RangeData.builder().deviceId(deviceId).start(now).power("100").state("IDLE").end(now.plus(4, ChronoUnit.DAYS)).build();
        RangeData rangeData2 = RangeData.builder().deviceId(deviceId).start(now.plus(2, ChronoUnit.DAYS)).power("20").state("IDLE").end(now.plus(5, ChronoUnit.DAYS)).build();
        RangeData rangeData3 = RangeData.builder().deviceId(deviceId).start(now.plus(6, ChronoUnit.DAYS)).power("48").state("UNLOADED").end(now.plus(5, ChronoUnit.DAYS)).build();
        RangeData rangeData4 = RangeData.builder().deviceId(deviceId).start(now.plus(4, ChronoUnit.DAYS)).power("78").state("OFF").end(now.plus(5, ChronoUnit.DAYS)).build();
        RangeData rangeData5 = RangeData.builder().deviceId(deviceId).start(now.plus(2, ChronoUnit.DAYS)).power("45").state("LOADED").end(now.plus(5, ChronoUnit.DAYS)).build();
        RangeData rangeData6 = RangeData.builder().deviceId(deviceId).start(now.plus(7, ChronoUnit.DAYS)).power("89").state("UNLOADED").end(now.plus(5, ChronoUnit.DAYS)).build();
        RangeData rangeData7 = RangeData.builder().deviceId(deviceId).start(now.plus(8, ChronoUnit.DAYS)).power("23").state("IDLE").end(now.plus(5, ChronoUnit.DAYS)).build();

        rangeDataList.add(rangeData1);
        rangeDataList.add(rangeData2);
        rangeDataList.add(rangeData3);
        rangeDataList.add(rangeData4);
        rangeDataList.add(rangeData5);
        rangeDataList.add(rangeData6);
        rangeDataList.add(rangeData7);

        return rangeDataList;
    }
}
