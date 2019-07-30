package com.daedalus.jassandra.system;

import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import krpc.client.services.SpaceCenter.Vessel;

import java.io.IOException;

/**
 * This class handles the connections to the krpc server.
 */
public class ConnectionManager {

    private static ConnectionManager connectionManager = null;
    private Connection kspConnection;
    private SpaceCenter spaceCenter;
    private Vessel activeVessel;

    private ConnectionManager() throws RPCException, IOException {
        this.kspConnection = Connection.newInstance();
        this.spaceCenter = SpaceCenter.newInstance(this.kspConnection);
        this.activeVessel = this.spaceCenter.getActiveVessel();
    }

    public static ConnectionManager getInstance() throws IOException, RPCException {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }


    public SpaceCenter getSpaceCenter() {
        return this.spaceCenter;
    }

    public Connection getConnection() {
        return kspConnection;
    }

    public Vessel getActiveVessel() {
        return activeVessel;
    }
}
