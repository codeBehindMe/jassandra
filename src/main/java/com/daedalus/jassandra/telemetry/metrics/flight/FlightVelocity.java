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
    public Triplet<Double, Double, Double> get(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame) throws RPCException {
        return vessel.flight(referenceFrame).getVelocity();
    }

    @Override
    public HashMap<String, Double> getHashMap(SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame) throws RPCException {
        String metricName = this.getClass().getName();

        String FlightVelocityX = metricName + CoordinateSystem.X;
        String FlightVelocityY = metricName + CoordinateSystem.Y;
        String FlightVelocityZ = metricName + CoordinateSystem.Z;

        Triplet<Double, Double, Double> flightVelocities = this.get(vessel, referenceFrame);
        HashMap<String, Double> resultMap = new HashMap<String, Double>();

        resultMap.put(FlightVelocityX, flightVelocities.getValue0());
        resultMap.put(FlightVelocityY, flightVelocities.getValue1());
        resultMap.put(FlightVelocityZ, flightVelocities.getValue2());

        return resultMap;
    }
}
