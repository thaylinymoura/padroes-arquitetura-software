package persistencia;

import negocio.modelo.Cliente;

import java.util.HashMap;
import java.util.Map;

import static persistencia.RepositorioConta.contas;

public class RepositorioCliente {

    private static final Map<String, Cliente> cliente  = new HashMap<>();

    public void salvarConta(Cliente  Cliente) {
        System.out.println("[PERSISTENCIA] Salvando conta: " + cliente.getNome());
        cliente.put(cliente.geNome(), cliente);
    }

    public  void salvarCliente()
    {

    }




}
