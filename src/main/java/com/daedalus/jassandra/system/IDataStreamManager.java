package com.daedalus.jassandra.system;

import com.daedalus.jassandra.telemetry.metrics.IMetric;

public interface IDataStreamManager {
    void addMetricToDataStream(IMetric metric) throws Exception;

    void showMetrics() throws Exception;

    void showMetricsAsJson() throws Exception;

    String getSerialisablePackage() throws Exception;

    String getVesselName() throws Exception;

    String getBuildName() throws Exception;

    String getSessionID() throws Exception;
}
