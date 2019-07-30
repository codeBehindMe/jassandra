package com.daedalus.jassandra.telemetry.metrics.flight;

import com.daedalus.jassandra.telemetry.metrics.IMetric;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import org.javatuples.Triplet;

public class VehicleDrag implements IMetric {
    @Override
    public Triplet<Double, Double, Double> get(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame) throws RPCException {
        return vessel.flight(referenceFrame).getDrag();
    }
}
