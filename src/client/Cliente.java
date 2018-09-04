package client;
import java.rmi.Naming;

import shared.Functions;

public class Cliente {
	static String endpoint_addr = "127.0.0.1";
	
	public static void main(String[] args) {

        try {
        	Functions func = (Functions) Naming.lookup("//" + endpoint_addr + "/functions");
            System.out.println("> Enviando execução do método Inserir");
            double diferencaIMC = func.inserir("sdfsdf", 0.2);
            System.out.println("< Resposta do método Inserir: " + diferencaIMC);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	
}
