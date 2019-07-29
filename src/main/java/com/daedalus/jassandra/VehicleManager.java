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
    }

    private void getAvailableVessels() throws RPCException {
        this.availableVessels = spaceCenter.getVessels();
    }

    public void resetActiveVessel() throws RPCException, NullPointerException {
        this.getAvailableVessels();

        if (this.availableVessels.size() == 1) {
            this.activeVessel = this.spaceCenter.getActiveVessel();
        } else {
            for (int i = 0; i < this.availableVessels.size(); i++) {
                String vesselName = this.availableVessels.get(i).getName();
                // FIXME: String literal in code.
                if (vesselName.contains("Probe")) {
                    this.activeVessel = this.availableVessels.get(i);
                    return;
                }
            }
            throw new NullPointerException("Could not find a vessel");
        }
    }

    public void showActiveVessels() throws RPCException {
        this.getAvailableVessels();
        for (int i = 0; i < this.availableVessels.size(); i++) {
            System.out.println(this.availableVessels.get(i).getName());
        }
    }

    public Vessel getActiveVessel() throws RPCException {
        this.resetActiveVessel();
        return activeVessel;
    }
}
