package com.daedalus.jassandra.datastream;

import com.daedalus.jassandra.system.IDataStreamManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Writes the data to a file
 */
public class FileStream implements IDataStream {
    private final String sessionID;
    private final String buildName;
    private final String vehicleName;
    private final int pollingDelayMilliseconds;
    private IDataStreamManager dataStreamManager;
    private FileWriter file = null;
    private boolean echoPackage = false;

    public FileStream(IDataStreamManager dataStreamManager, int pollingDelayMilliseconds) throws Exception {
        this.sessionID = dataStreamManager.getSessionID();
        this.buildName = dataStreamManager.getBuildName();
        this.vehicleName = dataStreamManager.getVesselName();

        this.dataStreamManager = dataStreamManager;
        this.pollingDelayMilliseconds = pollingDelayMilliseconds;
    }

    @Override
    public void setEcho(boolean status) {
        this.echoPackage = status;
    }

    private void echo(String s) {
        if (echoPackage) {
            System.out.println(s);
        }
    }

    // FIXME: This method is doing too much work.
    @Override
    public void start() throws Exception {
        try {
            try {
                this.file = new FileWriter(this.sessionID);
                while (true) {
                    String dPackage = this.dataStreamManager.getSerialisablePackage();
                    this.file.write(dPackage + "\n");
                    this.echo(dPackage);
                    TimeUnit.MILLISECONDS.sleep(this.pollingDelayMilliseconds);
                }
            } finally {
                this.file.close();
            }
        } catch (IOException e) {
            // FIXME: Logging message handled by print statement.
            System.out.println("Exception occurred when instantiating file");
            throw e;
        }
    }

    @Override
    public void stop() {

    }
}
