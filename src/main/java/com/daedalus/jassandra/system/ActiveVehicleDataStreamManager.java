package com.daedalus.jassandra.system;

import com.daedalus.jassandra.telemetry.metrics.IHashable;
import com.daedalus.jassandra.telemetry.metrics.IMetric;
import com.google.gson.Gson;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter.ReferenceFrame;
import krpc.client.services.SpaceCenter.Vessel;

import java.util.ArrayList;

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
public class ActiveVehicleDataStreamManager implements IDataStreamManager {


    private final String vesselName;
    private final MetricsPackageManager metricsPackageManager;
    private final String buildName;
    private final String sessionID;
    private VehicleManager vehicleManager;
    private Vessel activeVessel;
    private ReferenceFrame referenceFrame;
    private ArrayList<IMetric> metricList = new ArrayList<IMetric>();

    public ActiveVehicleDataStreamManager(VehicleManager vehicleManager, String buildName) throws RPCException {
        this.vehicleManager = vehicleManager;
        this.activeVessel = vehicleManager.getActiveVessel();
        this.referenceFrame = this.activeVessel.getOrbit().getBody().getReferenceFrame();

        /*
            We only set the name once, since initially it'll have the name that we give it and once
            the stage separation happens, these names get appended with things like debris.
         */
        this.vesselName = this.activeVessel.getName();
        metricsPackageManager = new MetricsPackageManager(this.vesselName, buildName);
        this.buildName = buildName;
        this.sessionID = metricsPackageManager.sessionID;

    }

    private void resetDataStream() throws RPCException {
        this.activeVessel = this.vehicleManager.getActiveVessel();
        this.referenceFrame = this.activeVessel.getOrbit().getBody().getReferenceFrame();
    }

    @Override
    public void addMetricToDataStream(IMetric metric) {
        this.metricList.add(metric);
    }

    @Override
    public void showMetrics() throws RPCException {
        this.resetDataStream();
        for (int i = 0; i < this.metricList.size(); i++) {
            System.out.println(this.metricList.get(i).valueNow(this.activeVessel, this.referenceFrame));
        }
    }

    @Override
    public void showMetricsAsJson() throws RPCException {

        Gson gson = new Gson();
        this.resetDataStream();
        for (int i = 0; i < this.metricList.size(); i++) {
            IHashable obj = (IHashable) this.metricList.get(i);
            System.out.println(gson.toJson(obj.getHashMap(this.activeVessel, this.referenceFrame)).toString());
        }
    }


    @Override
    public String getSerialisablePackage() throws RPCException {
        this.resetDataStream();

        for (int i = 0; i < this.metricList.size(); i++) {
            IHashable hMetric = (IHashable) this.metricList.get(i);
            this.metricsPackageManager.addMetricToPackage(hMetric.getHashMap(this.activeVessel, this.referenceFrame));
        }
        return this.metricsPackageManager.jsonSerialisePackage();
    }

    @Override
    public String getVesselName() {
        return this.vesselName;
    }

    @Override
    public String getBuildName() {
        return this.buildName;
    }

    @Override
    public String getSessionID() {
        return this.sessionID;
    }
}
