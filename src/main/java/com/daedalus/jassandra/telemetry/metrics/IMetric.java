package com.daedalus.jassandra.telemetry.metrics;

import krpc.client.RPCException;
import krpc.client.services.SpaceCenter.ReferenceFrame;
import krpc.client.services.SpaceCenter.Vessel;

import java.util.HashMap;

public interface IMetric<T> {
    public T get(Vessel vessel, ReferenceFrame referenceFrame) throws RPCException;
}
