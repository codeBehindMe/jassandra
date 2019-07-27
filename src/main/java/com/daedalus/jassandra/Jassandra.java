package com.daedalus.jassandra;

import com.daedalus.jassandra.streaming.SurfaceAltitudeStream;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter.Vessel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Jassandra {
    public static void main(String[] args) throws IOException, RPCException, InterruptedException {
        ConnectionManager c = ConnectionManager.getInstance();
        VehicleManager vehicleManager = new VehicleManager(c);

        try {

            Vessel activeVessel = vehicleManager.getActiveVessel();


            SurfaceAltitudeStream activeVesselSurfAltStream =
                    new SurfaceAltitudeStream(activeVessel);

            while (true) {
                System.out.println(activeVesselSurfAltStream.getValue());
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (IllegalArgumentException e) {
            vehicleManager.resetActiveVessel();
            Vessel v = vehicleManager.getActiveVessel();
            System.out.println(v.getName());
            SurfaceAltitudeStream surfaceAltitudeStream = new SurfaceAltitudeStream(v);
            while (true) {
                System.out.println(surfaceAltitudeStream.getValue());
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
