package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Calculadora extends Remote {

    int somar(int n1, int n2) throws RemoteException;

    int substrair(int n1, int n2) throws RemoteException;

    int multiplicar(int n1, int n2) throws RemoteException;

    float dividir(int n1, int n2) throws RemoteException;

}
