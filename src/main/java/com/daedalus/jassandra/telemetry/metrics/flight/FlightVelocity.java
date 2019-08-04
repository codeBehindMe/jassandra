package com.daedalus.jassandra.telemetry.metrics.flight;

import com.daedalus.jassandra.telemetry.metrics.CoordinateSystem;
import com.daedalus.jassandra.telemetry.metrics.IHashable;
import com.daedalus.jassandra.telemetry.metrics.IMetric;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import org.javatuples.Triplet;

import java.util.HashMap;

public class FlightVelocity implements IMetric, IHashable {
    @Override
    public Triplet<Double, Double, Double> valueNow(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame) throws RPCException {
        return vessel.flight(referenceFrame).getVelocity();
    }

    @Override
    public HashMap getHashMap(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame) throws RPCException {

        Triplet<Double, Double, Double> flightVelocities = this.valueNow(vessel, referenceFrame);
        HashMap<String, HashMap<String, Double>> resultMap = new HashMap<String, HashMap<String, Double>>();
        HashMap<String, Double> valueMap = new HashMap<String, Double>();

        valueMap.put(CoordinateSystem.X.toString(), flightVelocities.getValue0());
        valueMap.put(CoordinateSystem.Y.toString(), flightVelocities.getValue1());
        valueMap.put(CoordinateSystem.Z.toString(), flightVelocities.getValue2());

        resultMap.put(this.getClass().getName(), valueMap);

        return resultMap;
    }
}
