package server;

import java.io.Serializable;
import java.rmi.RemoteException;

import shared.Functions;
import server.banco;

public abstract class FunctionsImplements implements Functions, Serializable {
	//Salva o Banco
	private banco bd = null;
	//Contrutor
	public FunctionsImplements(banco BD){
		this.bd = BD;
	}
	//Função para inserir os dados
	@Override
	public double inserir(String nome, Double imc) throws RemoteException{
		listener("inserir");
		boolean verifica = this.bd.verificaUsuario(nome);
		if(verifica) {
			return bd.atualizarUsuario(nome, imc);
		}else {
			bd.inserirNovoUser(nome, imc);
			return 0;
		}
	}
	
	public abstract void listener(String method);
}
