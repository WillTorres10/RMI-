package server;

import java.io.Serializable;
import java.rmi.RemoteException;
import server.banco;

import shared.Calculadora;

public abstract class CalculadoraHP implements Calculadora, Serializable {

    private static final long serialVersionUID = 1L;
    
    private banco bd;
    
    public CalculadoraHP(banco bd) {
		this.bd = bd;
	}
    
    @Override
    public int somar(int n1, int n2) throws RemoteException {
        listener("somar");
        return (n1 + n2);
    }

    @Override
    public int substrair(int n1, int n2) throws RemoteException {
        listener("substrair");
        return (n1 - n2);
    }

    @Override
    public int multiplicar(int n1, int n2) throws RemoteException {
        listener("multiplicar");
        return (n1 * n2);
    }

    @Override
    public float dividir(int n1, int n2) throws RemoteException {
        listener("dividir");
        return (n1 / n2);
    }
    
    public abstract void listener(String method);

}
