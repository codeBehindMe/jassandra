package com.daedalus.jassandra;

import krpc.client.RPCException;
import krpc.client.Stream;
import krpc.client.StreamException;
import krpc.client.services.SpaceCenter;
import krpc.client.services.SpaceCenter.Vessel;
import org.javatuples.Triplet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Jassandra {
    public static void main(String[] args) throws IOException, RPCException, InterruptedException, StreamException {
        ConnectionManager c = ConnectionManager.getInstance();
//        VehicleManager vehicleManager = new VehicleManager(c);
//
//
//        Vessel activeVessel = vehicleManager.getActiveVessel();
//        Stream<Triplet<Double, Double, Double>> dataStream =
//                c.getConnection().addStream(activeVessel,
//                        "velocity",
//                        activeVessel.getOrbit().getBody().getReferenceFrame());

        ActiveVehicleDataStreamManager ads = new ActiveVehicleDataStreamManager(c);
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(ads.getSurfaceAltitude());
        }
    }
}
