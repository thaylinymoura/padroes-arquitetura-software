package persistencia;
import negocio.modelo.Conta;

import java.util.HashMap;
import java.util.Map;

public class RepositorioConta {
    private static final Map<String, Conta> contas = new HashMap<>();

    public void salvarConta(Conta conta) {
        System.out.println("[PERSISTENCIA] Salvando conta: " + conta.getNumeroConta());
        contas.put(conta.getNumeroConta(), conta);
    }
}