package negocio.modelo;
import java.util.Map;

public class EntidadeFactory {
    // A factory cria o objeto de negócio a partir de dados genéricos
    public static Object criar(String tipoEntidade, Map<String, Object> dados) {
        System.out.println("[NEGOCIO-FACTORY] Criando entidade do tipo: " + tipoEntidade);
        switch (tipoEntidade.toLowerCase()) {
            case "cliente":
                return new Cliente((String) dados.get("cpf"), (String) dados.get("nome"));
            case "agencia":
                return new Agencia((Integer) dados.get("numero"), (String) dados.get("nome"));
            case "conta":
                return new Conta(
                        (String) dados.get("numeroConta"),
                        (Cliente) dados.get("titular"),
                        (Agencia) dados.get("agencia"),
                        0.0 // Regra de negócio: saldo inicial zero
                );
            default:
                throw new IllegalArgumentException("Tipo de entidade desconhecido: " + tipoEntidade);
        }
    }
}