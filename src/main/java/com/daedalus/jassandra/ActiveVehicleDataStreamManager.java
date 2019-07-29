package com.daedalus.jassandra;

import krpc.client.RPCException;
import krpc.client.services.SpaceCenter.ReferenceFrame;
import krpc.client.services.SpaceCenter.Vessel;

/**
 * Manages the data streams from the vehicle.
 * <p>
 * The management of data streams for multi stage * vehicles is particularly difficult. This is
 * because, data streams are generated off the vehicle * objects. In the initial phase of the game,
 * there is a flight vehicle in the game which references all the stages of the vehicle.
 * <p>
 * But when stage separation occurs , that first flight vehicle is removed from the game and new
 * vehicle objects are introduced. This then causes a null reference pointer exception to the data
 * streams that were initially pointing to the initial flight vehicle.
 */
public class ActiveVehicleDataStreamManager {

    private ConnectionManager connectionmanager;
    private Vessel activeVessel;
    private ReferenceFrame referenceFrame;

    public ActiveVehicleDataStreamManager(ConnectionManager c) {
        this.connectionmanager = c;
    }

    /**
     * Get's the sessions currently active vessel.
     */
    private void setActiveVessel() {
        this.activeVessel = this.connectionmanager.getActiveVessel();
    }


    /**
     * Set's the reference frame for the currently active vessel.
     */
    private void setReferenceFrame() throws RPCException {
        this.referenceFrame = this.activeVessel.getOrbit().getBody().getReferenceFrame();
    }

    /**
     * @return Returns the surface altitude.
     */
    public Double getSurfaceAltitude() throws RPCException {
        return this.activeVessel.flight(this.referenceFrame).getSurfaceAltitude();
    }
}
