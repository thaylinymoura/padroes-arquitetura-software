package persistencia;

import negocio.modelo.Agencia;
import negocio.modelo.Cliente;
import negocio.modelo.Conta;

import java.util.HashMap;
import java.util.Map;

public class RepositorioGenerico {
    private static final Map<String, Object> db = new HashMap<>();

    public void salvar(Object entidade) {
        if (entidade instanceof Cliente) {
            Cliente c = (Cliente) entidade;
            System.out.println("[PERSISTENCIA] Salvando Cliente: " + c.getNome());
            db.put("cliente_" + c.getCpf(), c);
        } else if (entidade instanceof Agencia) {
            Agencia a = (Agencia) entidade;
            System.out.println("[PERSISTENCIA] Salvando Agencia: " + a.getNome());
            db.put("agencia_" + a.getNumero(), a);
        } else if (entidade instanceof Conta) {
            Conta c = (Conta) entidade;
            System.out.println("[PERSISTENCIA] Salvando Conta: " + c.getNumeroConta());
            db.put("conta_" + c.getNumeroConta(), c);
        }
    }
}
