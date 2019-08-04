package com.daedalus.jassandra;

import com.daedalus.jassandra.system.ActiveVehicleDataStreamManager;
import com.daedalus.jassandra.system.ConnectionManager;
import com.daedalus.jassandra.system.VehicleManager;
import com.daedalus.jassandra.telemetry.metrics.flight.*;

import java.util.concurrent.TimeUnit;

public class Jassandra {
    public static void main(String[] args) throws Exception {

        final String buildName = "pre-release-build";
        final int pollRateInMilliseconds = 250;
        ConnectionManager c = ConnectionManager.getInstance();
        VehicleManager vehicleManager = new VehicleManager(c);

        ActiveVehicleDataStreamManager vdm =
                new ActiveVehicleDataStreamManager(vehicleManager, buildName);

        vdm.addMetricToDataStream(new FlightVelocity());
        vdm.addMetricToDataStream(new SurfaceAltitude());
        vdm.addMetricToDataStream(new VehicleAngleOfAttack());
        vdm.addMetricToDataStream(new VehicleDrag());
        vdm.addMetricToDataStream(new VerticalSpeed());

        while (true) {
            System.out.println(vdm.getSeraliasablePackage());
            TimeUnit.MILLISECONDS.sleep(pollRateInMilliseconds);
        }
    }
}
