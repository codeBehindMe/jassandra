package com.daedalus.jassandra.datastream;

public interface IDataStream {
    void setEcho(boolean status);

    void start() throws Exception;

    void stop() throws Exception;
}
