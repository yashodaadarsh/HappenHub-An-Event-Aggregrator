package org.adarsh.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Interest {
    TECHNOLOGY("TECHNOLOGY"),
    SOFTWARE("SOFTWARE"),
    AI_ML("AI & ML"),
    WEB_DEV("WEB DEV"),
    DESIGN("DESIGN"),
    STARTUPS("STARTUPS"),
    MARKETING("MARKETING"),
    BUSINESS("BUSINESS"),
    GAMING("GAMING"),
    HEALTH("HEALTH"),
    FINANCE("FINANCE"),
    SCIENCE("SCIENCE");

    private final String value;

    Interest(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Interest fromValue(String value) {
        for (Interest i : Interest.values()) {
            if (i.value.equalsIgnoreCase(value)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Unknown Interest: " + value);
    }
}
