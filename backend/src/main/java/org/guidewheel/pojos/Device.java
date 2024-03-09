package org.guidewheel.pojos;

import lombok.Builder;

@Builder
public record Device(String id, String name) { }
