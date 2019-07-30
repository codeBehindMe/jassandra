package com.daedalus.jassandra.telemetry.metrics.flight;

import com.daedalus.jassandra.telemetry.metrics.IMetric;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;

public class VerticalVelocity implements IMetric {
    @Override
    public Object get(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame) throws
            RPCException {
        return null;
    }
}
