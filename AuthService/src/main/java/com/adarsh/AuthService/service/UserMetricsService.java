package com.adarsh.AuthService.service; // Note: Class path needs to be updated to 'metrics'

import com.adarsh.AuthService.enums.Interest;
import com.adarsh.AuthService.enums.PreferenceType;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

// Assuming the class is moved to com.adarsh.AuthService.metrics
@Service
public class UserMetricsService {

    private final MeterRegistry meterRegistry;

    public UserMetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    /**
     * Increments the counter for a specific Interest.
     * Micrometer manages the cache: it finds the existing counter or creates/caches a new one.
     * @param interest The selected Interest
     */
    public void incrementInterest(Interest interest) {
        // Use the MeterRegistry's factory method to GET or CREATE the counter, ensuring consistent reuse.
        meterRegistry.counter(
                "user_selection_count",
                "selection_type", "interest",
                "value", interest.getValue() // NOTE: Still needs the ampersand fix for AI & ML
        ).increment();
    }

    /**
     * Increments the counter for a specific Preference Type.
     * @param preference The selected PreferenceType
     */
    public void incrementPreference(PreferenceType preference) {
        // Use the MeterRegistry's factory method to GET or CREATE the counter, ensuring consistent reuse.
        meterRegistry.counter(
                "user_selection_count",
                "selection_type", "preference",
                "value", preference.name() // Use name() for clean enum strings
        ).increment();
    }
}