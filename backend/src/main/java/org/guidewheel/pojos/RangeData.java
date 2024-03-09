package org.guidewheel.pojos;

import lombok.Builder;

import java.time.Instant;

@Builder
public record RangeData(String deviceId, Instant start, Instant end, String power, String state) { }
