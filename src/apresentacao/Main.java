package apresentacao;

import aplicacao.ServicoAberturaConta;
import aplicacao.ServicoCadastroGenerico;
import negocio.modelo.Agencia;
import negocio.modelo.Cliente;
import negocio.modelo.Conta;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Variante 1: Um Método por Entidade ---");
        ServicoAberturaConta servico = new ServicoAberturaConta();

        // Simula a entrada de dados do usuário
        System.out.println("\n[APRESENTACAO] Usuário solicita cadastro de agência.");
        Agencia agencia = servico.cadastrarAgencia(101, "Agência Central");

        System.out.println("\n[APRESENTACAO] Usuário solicita cadastro de cliente.");
        Cliente cliente = servico.cadastrarCliente("123.456.789-00", "Elias Ferreira");

        System.out.println("\n[APRESENTACAO] Usuário solicita abertura de conta.");
        Conta conta = servico.abrirConta("9876-5", cliente, agencia);

        System.out.println("\nProcesso finalizado! Conta " + conta.getNumeroConta() + " criada.");

        System.out.println("\n--- Variante 2: Meta-Parâmetro ---");
        ServicoCadastroGenerico servico = new ServicoCadastroGenerico();

        // Simula a entrada de dados do usuário para a Agência
        System.out.println("\n[APRESENTACAO] Usuário solicita cadastro de agência.");
        Map<String, Object> dadosAgencia = new HashMap<>();
        dadosAgencia.put("numero", 102);
        dadosAgencia.put("nome", "Agência Digital");
        Agencia agencia = (Agencia) servico.cadastrar("agencia", dadosAgencia);

        // Simula a entrada de dados do usuário para o Cliente
        System.out.println("\n[APRESENTACAO] Usuário solicita cadastro de cliente.");
        Map<String, Object> dadosCliente = new HashMap<>();
        dadosCliente.put("cpf", "987.654.321-00");
        dadosCliente.put("nome", "Valdemar Neto");
        Cliente cliente = (Cliente) servico.cadastrar("cliente", dadosCliente);

        // Simula a entrada de dados do usuário para a Conta
        System.out.println("\n[APRESENTACAO] Usuário solicita abertura de conta.");
        Map<String, Object> dadosConta = new HashMap<>();
        dadosConta.put("numeroConta", "1234-X");
        dadosConta.put("titular", cliente); // Passando objetos já criados
        dadosConta.put("agencia", agencia);
        Conta conta = (Conta) servico.cadastrar("conta", dadosConta);

        System.out.println("\nProcesso finalizado! Conta " + conta.getNumeroConta() + " criada.");
    }

}
