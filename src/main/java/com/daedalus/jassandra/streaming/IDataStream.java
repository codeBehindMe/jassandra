package com.daedalus.jassandra.streaming;

import krpc.client.RPCException;

public interface IDataStream<T> {
    public T getValue() throws RPCException;
}
