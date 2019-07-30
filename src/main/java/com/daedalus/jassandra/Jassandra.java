package com.daedalus.jassandra;

import com.daedalus.jassandra.system.ActiveVehicleDataStreamManager;
import com.daedalus.jassandra.system.ConnectionManager;
import com.daedalus.jassandra.system.VehicleManager;
import com.daedalus.jassandra.telemetry.metrics.FlightVelocity;
import com.daedalus.jassandra.telemetry.metrics.SurfaceAltitude;
import krpc.client.RPCException;
import krpc.client.StreamException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Jassandra {
    public static void main(String[] args) throws IOException, RPCException, InterruptedException, StreamException {
        ConnectionManager c = ConnectionManager.getInstance();
        VehicleManager vehicleManager = new VehicleManager(c);

        ActiveVehicleDataStreamManager vdm = new ActiveVehicleDataStreamManager(vehicleManager);

        vdm.addMetricToDataStream(new SurfaceAltitude());
        vdm.addMetricToDataStream(new FlightVelocity());

        while (true) {
            vdm.showMetrics();
//            System.out.println(vdm.getVehicleSurfaceAltitude());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
