package com.daedalus.jassandra.telemetry.metrics;

import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;

public class VerticalVelocity implements IMetric {
    @Override
    public Object get(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame) throws RPCException {
        return null;
    }
}
