package com.daedalus.jassandra.telemetry.metrics.flight;

import com.daedalus.jassandra.telemetry.metrics.IHashable;
import com.daedalus.jassandra.telemetry.metrics.IMetric;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;

import java.util.HashMap;

public class VehicleAngleOfAttack implements IMetric, IHashable {
    @Override
    public Float valueNow(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame) throws RPCException {
        return vessel.flight(referenceFrame).getAngleOfAttack();
    }

    @Override
    public HashMap getHashMap(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame) throws RPCException {
        HashMap<String, Float> vehicleAngleOfAttack = new HashMap<String, Float>();
        vehicleAngleOfAttack.put(this.getClass().getName(), this.valueNow(vessel, referenceFrame));
        return vehicleAngleOfAttack;
    }
}
