package org.guidewheel.services;

import org.guidewheel.pojos.RangeData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChartDataService {
    public List<RangeData> getRangeDate() {
        List<RangeData> rangeDataList = new ArrayList<>();
        RangeData rangeData1 = RangeData.builder().build();
        rangeDataList.add(rangeData1);
        return rangeDataList;
    }
}
