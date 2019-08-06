package com.daedalus.jassandra.system;

import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import krpc.client.services.SpaceCenter.Vessel;

import java.util.List;

/**
 * Handles the active vehicles inside the session.
 * <p>
 * It turns out, this is a surprisingly hard thing to do. Especially when a launch vehicle  is a
 * multi-stage vehicle. Initially, the active vehicle will be a single object (With the name of the
 * vehicle as the active vehicle) however, when stage separation occurs, that object is "lost" and
 * multiple new objects are created.
 * <p>
 * The exact model how this happens isn't fully described yet, so I've made a very temporary fix to
 * find the active vessel before doing anything. Otherwise it'll cause a null pointer reference.
 * <p>
 * I also found usually the upper stage (the one we're usually interested) has the keyword "Probe"
 * in the name string. So I hacked that into identifying the active vehicle.
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
