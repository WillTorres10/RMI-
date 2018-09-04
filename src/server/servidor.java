package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import server.banco;

import shared.Calculadora;
import shared.Functions;
import server.FunctionsImplements;

public class servidor {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        try {
            // Criando serviço na porta 1099
            LocateRegistry.createRegistry(1099);
            System.setProperty("java.rmi.server.hostname","10.42.0.1");
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
			Naming.rebind("functions", func);
            System.out.println("Servidor de Banco está pronto para ser utilizado.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
