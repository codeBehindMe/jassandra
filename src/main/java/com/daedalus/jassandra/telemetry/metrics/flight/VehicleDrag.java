package com.daedalus.jassandra.telemetry.metrics.flight;

import com.daedalus.jassandra.telemetry.metrics.IHashable;
import com.daedalus.jassandra.telemetry.metrics.IMetric;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import org.javatuples.Triplet;

import java.util.HashMap;

public class VehicleDrag implements IMetric, IHashable {
    @Override
    public Triplet<Double, Double, Double> valueNow(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame) throws RPCException {
        return vessel.flight(referenceFrame).getDrag();
    }

    @Override
    public HashMap getHashMap(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame) throws RPCException {
        HashMap<String, Triplet<Double, Double, Double>> vehicleDrag =
                new HashMap<String, Triplet<Double, Double, Double>>();
        vehicleDrag.put(this.getClass().getName(), this.valueNow(vessel,referenceFrame));
        return vehicleDrag;
    }
}
