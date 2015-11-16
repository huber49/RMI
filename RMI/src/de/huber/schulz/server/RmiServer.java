package de.huber.schulz.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import de.huber.schulz.server.RmiInterface;

public class RmiServer implements RmiInterface {

	public RmiServer() {
		super();
	}

	@Override
	public String buildHelloString(String inputString) throws RemoteException {
		return "Hello" + inputString;
	}

	public static void main(String... args) {
		
		System.setProperty("java.security.policy","file:///home/benutzer/java/jdk-8u60-linux-i586/jdk1.8.0_60/lib/security/java.policy");
		
		// Kontrolliert Zugriff auf System ressources from untrusted downloaded
		// code running in the JVM
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		// making the remote object available to clients
		// 0 as tcp-port
		String name = "HelloServer";
		RmiInterface server = new RmiServer();
		try {
			RmiInterface stub = (RmiInterface) UnicastRemoteObject
					.exportObject(server, 0);
			// Server in die lokale Registry eintragen
			//Achtung: Default ist Localjost und Port 1099! 
			//andernfalls muss man weitere Argumente �bergeben
			Registry  registry = LocateRegistry.createRegistry(1099);
			registry.rebind(name, stub);
			System.out.println("Server bound! :P");
			
		} catch (RemoteException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}
}
