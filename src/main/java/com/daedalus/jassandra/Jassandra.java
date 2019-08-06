package com.daedalus.jassandra;

import com.daedalus.jassandra.datastream.FileStream;
import com.daedalus.jassandra.system.ActiveVehicleDataStreamManager;
import com.daedalus.jassandra.system.ConnectionManager;
import com.daedalus.jassandra.system.VehicleManager;
import com.daedalus.jassandra.telemetry.metrics.flight.*;

public class Jassandra {
    public static void main(String[] args) throws Exception {

        final String buildName = "pre-release-build";
        final int pollRateInMilliseconds = 500;
        ConnectionManager c = ConnectionManager.getInstance();
        VehicleManager vehicleManager = new VehicleManager(c);

        ActiveVehicleDataStreamManager vdm =
                new ActiveVehicleDataStreamManager(vehicleManager, buildName);

        vdm.addMetricToDataStream(new FlightVelocity());
        vdm.addMetricToDataStream(new SurfaceAltitude());
        vdm.addMetricToDataStream(new VehicleAngleOfAttack());
        vdm.addMetricToDataStream(new VehicleDrag());
        vdm.addMetricToDataStream(new VerticalSpeed());

        FileStream fStream = new FileStream(vdm, pollRateInMilliseconds);
        fStream.setEcho(true);
        fStream.start();

    }
}
