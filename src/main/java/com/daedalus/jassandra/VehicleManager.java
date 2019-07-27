package com.daedalus.jassandra;

import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import krpc.client.services.SpaceCenter.Vessel;

import java.util.List;

/**
 * Handles the active vehicles inside the session.
 */
public class VehicleManager {

    private SpaceCenter spaceCenter;
    private Vessel activeVessel;
    private List<Vessel> availableVessels;

    public VehicleManager(ConnectionManager c) throws RPCException {
        this.spaceCenter = c.getSpaceCenter();
        this.availableVessels = spaceCenter.getVessels();
        this.activeVessel = this.spaceCenter.getActiveVessel();
    }

    public void resetActiveVessel() throws RPCException {
        this.activeVessel = this.spaceCenter.getActiveVessel();
    }

    public Vessel getActiveVessel() {
        return this.activeVessel;
    }

    /**
     * @return The final stage of the vessels available.
     */
    public Vessel getFinalStageVessel() {
        return this.availableVessels.get(availableVessels.size() - 1);
    }
}
