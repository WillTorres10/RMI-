package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Functions extends Remote {
	
	double inserir(String nome, Double imc) throws RemoteException;
	
}
