package com.daedalus.jassandra.telemetry.metrics;

import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;

import java.util.HashMap;

public interface IHashable<T> {
    public HashMap<String, T> getHashMap
            (SpaceCenter.Vessel vessel, SpaceCenter.ReferenceFrame referenceFrame)
            throws RPCException;
}
