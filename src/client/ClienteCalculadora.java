package client;

import java.rmi.Naming;

import shared.Calculadora;

/**
 * Classe que representa o cliente querendo usar um servidor de cálculo
 *
 */

public class ClienteCalculadora {

    // Endereço de IP do servidor remoto.
    static String endpoint_addr = "192.168.1.109";
    
    public static void main(String[] args) {

        try {
            // Obtendo referencia do objeto remoto
            Calculadora calculadora = (Calculadora) Naming.lookup("//" + endpoint_addr + "/calculadora");

            // Executando métodos do remoto
            System.out.println("> Enviando execução do método Calculadora#multiplicar");
            int resultadoMultiplicacao = calculadora.multiplicar(21, 2);
            System.out.println("< Resposta do método Calculadora#multiplicar: " + resultadoMultiplicacao);
            
            // Executando métodos do remoto
            System.out.println("> Enviando execução do método Calculadora#dividir");
            float resultadoDivisao = calculadora.dividir(29, 7);
            System.out.println("< Resposta do método Calculadora#dividir: " + resultadoDivisao);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
