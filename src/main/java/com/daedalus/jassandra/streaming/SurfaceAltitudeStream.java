package com.daedalus.jassandra.streaming;

import krpc.client.RPCException;
import krpc.client.services.SpaceCenter.Vessel;
import krpc.client.services.SpaceCenter.ReferenceFrame;


public class SurfaceAltitudeStream implements IDataStream<Double> {

    private ReferenceFrame referenceFrame;
    private Vessel vessel;
    public SurfaceAltitudeStream(Vessel v) throws RPCException {
        this.vessel = v;
        this.referenceFrame = this.vessel.getOrbit().getBody().getReferenceFrame();
    }

    public Double getValue() throws RPCException {
        return this.vessel.flight(this.referenceFrame).getSurfaceAltitude();
    }
}
