package de.huber.schulz.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiInterface extends Remote{

	String buildHelloString(String inputString) throws RemoteException;
}
