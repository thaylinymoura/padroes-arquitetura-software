package aplicacao;


import negocio.modelo.EntidadeFactory;
import persistencia.RepositorioGenerico;

import java.util.Map;

public class ServicoCadastroGenerico {

    private final RepositorioGenerico repositorio = new RepositorioGenerico();

    public Object cadastrar(String tipoEntidade, Map<String, Object> dados) {
        System.out.println("[APLICACAO] Recebendo requisição genérica para: " + tipoEntidade);

        // 1. Chama a fábrica na camada de negócio para criar o objeto
        Object novaEntidade = EntidadeFactory.criar(tipoEntidade, dados);

        // 2. Chama o repositório para persistir o objeto
        repositorio.salvar(novaEntidade);

        return novaEntidade;
    }
}
