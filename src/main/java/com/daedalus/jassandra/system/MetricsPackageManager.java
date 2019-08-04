package com.daedalus.jassandra.system;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.UUID;

/**
 * Manages the metrics package for streaming.
 */
public class MetricsPackageManager {
    final String sessionID = UUID.randomUUID().toString().replace("-", "");
    private final String vehicleName;
    private final String buildName;
    private HashMap metricsPackage = new HashMap();
    private final Gson gson = new Gson();

    public MetricsPackageManager(String vehicleName, String buildName) {
        this.vehicleName = vehicleName;
        this.buildName = buildName;

        // FIXME: String elements in code.
        // FIXME: Construction of elements in metrics package could be done elsewhere?
        metricsPackage.put("vehicleID", this.vehicleName);
        metricsPackage.put("buildName", this.buildName);
        metricsPackage.put("sessionID", this.sessionID);
    }

    public void addMetricToPackage(HashMap values) {
        this.metricsPackage.putAll(values);
    }

    public HashMap getPackage() {
        return this.metricsPackage;
    }

    /**
     * Puts a timestamp on the package.
     */
    private void packageTimeStamp() {

        this.metricsPackage.put("packageTime", System.currentTimeMillis());
    }

    public String jsonSerialisePackage() {
        this.packageTimeStamp();
        return this.gson.toJson(this.metricsPackage);
    }

}
