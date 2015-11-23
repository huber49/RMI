package de.huber.schulz.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import de.huber.schulz.server.RmiInterface;

public class TestClient {

	public static void main(String[] args) {
		
		System.setProperty("java.security.policy","file:///home/benutzer/java/jdk-8u60-linux-i586/jdk1.8.0_60/lib/security/java.policy");
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "HelloServer"; // hier soll der Name des Interfaces
											// stehen?
			Registry registry;
			registry = LocateRegistry.getRegistry(1099); // hier ggf. Hostnamen statt #
			System.out.println("Registry = " + registry);
			RmiInterface serv = (RmiInterface) registry.lookup(name);
			String result = serv.buildHelloString("Hans");
			System.out.println(result);
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
