package org.guidewheel.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.guidewheel.pojos.RangeData;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CsvService {
    public List<RangeData> read(String path) throws Exception {
        List<RangeData> rangeDataList = new ArrayList<>();
        Random random = new Random();
        try (InputStream inputStream = CSVReader.class.getClassLoader().getResourceAsStream(path);
            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
            csvReader.skip(1);

            int counter = 0;
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                String fromTs = record[1];
                String toTs = record[2];
                String metrics = record[3];
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(metrics);
                JsonNode psumNode = root.path("Psum");

                RangeData rangeData = RangeData.builder()
                        .power(psumNode.path("avgvalue").asText())
                        .start(Instant.ofEpochMilli(Long.parseLong(fromTs)))
                        .end(Instant.ofEpochMilli(Long.parseLong(toTs)))
                        .deviceId(counter %2 ==0 ? "1" : "2")
                        .state(MachineStatus.values()[random.nextInt(MachineStatus.values().length)].name())
                        .build();
                rangeDataList.add(rangeData);
                counter++;
            }
        }
        return rangeDataList;
    }
}
