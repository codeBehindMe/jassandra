package com.daedalus.jassandra.telemetry.metrics.flight;

import com.daedalus.jassandra.telemetry.metrics.IHashable;
import com.daedalus.jassandra.telemetry.metrics.IMetric;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;

import java.util.HashMap;

public class VerticalSpeed implements IMetric, IHashable {
    @Override
    public Double valueNow(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame)
            throws RPCException {
        return vessel.flight(referenceFrame).getVerticalSpeed();
    }

    @Override
    public HashMap getHashMap(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame) throws RPCException {
        HashMap<String, Double> verticalSpeed = new HashMap<String, Double>();
        verticalSpeed.put(this.getClass().getName(), this.valueNow(vessel, referenceFrame));
        return verticalSpeed;
    }
}
