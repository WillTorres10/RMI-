package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import server.banco;

import shared.Calculadora;

public class ServidorCalculo {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        try {
            // Criando serviço na porta 1099
            LocateRegistry.createRegistry(1099);
            System.setProperty("java.rmi.server.hostname","127.0.0.1");
            banco bd = new banco();
            bd.connectar();
            // Criando objeto a ser enviado
            Calculadora packet = new CalculadoraHP(bd){
                private static final long serialVersionUID = 1L;
                @Override
                public void listener(String method) {
                    System.out.println("Executando Calculadora#" + method + " no lado do servidor");
                }
            };

            // Exportando o objeto para enviar
            Calculadora stub = (Calculadora) UnicastRemoteObject.exportObject(packet, 0);

            // Declarando o método na /calculadora
            Naming.rebind("calculadora", packet);
            
            System.out.println("Servidor de Cálculo está pronto para ser utilizado.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
