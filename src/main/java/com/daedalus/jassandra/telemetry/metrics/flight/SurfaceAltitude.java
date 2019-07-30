package com.daedalus.jassandra.telemetry.metrics.flight;

import com.daedalus.jassandra.telemetry.metrics.IMetric;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;

public class SurfaceAltitude implements IMetric {
    @Override
    public Double get(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame)
            throws RPCException {
        return vessel.flight(referenceFrame).getSurfaceAltitude();
    }
}
