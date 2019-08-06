package com.daedalus.jassandra.telemetry.metrics;

import krpc.client.RPCException;
import krpc.client.services.SpaceCenter.ReferenceFrame;
import krpc.client.services.SpaceCenter.Vessel;

public interface IMetric<T> {
    public T valueNow(Vessel vessel, ReferenceFrame referenceFrame) throws RPCException;
}
