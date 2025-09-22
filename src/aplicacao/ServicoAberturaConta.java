package aplicacao;

import negocio.modelo.Agencia;
import negocio.modelo.Cliente;
import negocio.modelo.Conta;
import persistencia.RepositorioAgencia;
import persistencia.RepositorioCliente;
import persistencia.RepositorioConta;

public class ServicoAberturaConta {

    private final RepositorioCliente repoCliente = new RepositorioCliente();
    private final RepositorioAgencia repoAgencia = new RepositorioAgencia();
    private final RepositorioConta repoConta = new RepositorioConta();

    public Cliente cadastrarCliente(String cpf, String nome) {
        System.out.println("[APLICACAO] Recebendo dados para cadastrar cliente.");
        Cliente novoCliente = new Cliente(cpf, nome); // Regra de negócio de criação
        repoCliente.salvarCliente(novoCliente);
        return novoCliente;
    }

    public Agencia cadastrarAgencia(int numero, String nome) {
        System.out.println("[APLICACAO] Recebendo dados para cadastrar agência.");
        Agencia novaAgencia = new Agencia(numero, nome);
        repoAgencia.salvarAgencia(novaAgencia);
        return novaAgencia;
    }

    public Conta abrirConta(String numeroConta, Cliente titular, Agencia agencia) {
        System.out.println("[APLICACAO] Recebendo dados para abrir conta.");
        Conta novaConta = new Conta(numeroConta, titular, agencia, 0.0); // Regra de negócio: saldo inicial é zero
        repoConta.salvarConta(novaConta);
        return novaConta;
    }
}