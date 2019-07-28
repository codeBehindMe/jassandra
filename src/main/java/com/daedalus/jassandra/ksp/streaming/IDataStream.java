package com.daedalus.jassandra.ksp.streaming;

import krpc.client.RPCException;

public interface IDataStream<T> {
    public T getValue() throws RPCException;
}
