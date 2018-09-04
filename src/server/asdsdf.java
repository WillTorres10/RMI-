package server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.*;
import server.banco;

import shared.Functions;
import server.FunctionsImplements;


public class servidor {

    @SuppressWarnings("unused")
    public static void main(String[] args) {  	
    	Registry registry = null;
        try {
        	try {
        		registry=LocateRegistry.createRegistry(1099);
        	}catch (Exception e) {
        		registry=LocateRegistry.getRegistry(1099);
			}
            // Conectanto ao Banco de Dados
            banco bd = new banco();
            bd.connectar();
            FunctionsImplements func = new FunctionsImplements(bd) {
				@Override
				public void listener(String method) {
					System.out.println("Executando " + method + " no lado do servidor");
				}
			};
			Functions send = (Functions) UnicastRemoteObject.exportObject(func, 0);
			
			registry.rebind("functions", func);
			
            System.out.println("Servidor de Banco está pronto para ser utilizado.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
