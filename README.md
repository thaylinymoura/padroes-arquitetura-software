# Atividade Prática: Padrão de Arquitetura em Camadas

Este projeto foi desenvolvido para a disciplina de Padrões de Arquitetura de Software e tem como objetivo implementar e analisar duas variações do Padrão de Arquitetura em Camadas. A aplicação simula um sistema simples de abertura de contas bancárias.

* Linguagem: Java
* Padrão Principal: Arquitetura em Camadas (Layered Architecture)


## Decisões de Projeto Gerais

As seguintes decisões foram tomadas e aplicam-se a ambas as variantes implementadas:

1. Domínio do Problema: Foi escolhido um sistema bancário simplificado com três entidades principais: Cliente, Agencia e Conta. Este domínio é suficientemente simples para focar nos aspectos arquiteturais, mas complexo o bastante para demonstrar as diferenças entre as abordagens.
2. Estratégia de Persistência: Optou-se por uma persistência em memória utilizando HashMap. A justificativa para essa decisão é manter o foco exclusivamente no padrão de arquitetura em camadas, evitando a complexidade adicional de configuração de um banco de dados relacional (JDBC, JPA, etc.). Isso cumpre o requisito de usar "outro meio" de persistência.
3. Definição das Camadas Lógicas: A arquitetura foi dividida em quatro camadas lógicas, conforme os conceitos apresentados em aula. A comunicação entre elas segue uma regra estrita onde uma camada superior só pode invocar serviços da camada imediatamente inferior.

* Apresentação: Responsável pela interação com o usuário. Neste projeto, é simulada por uma classe Main.
* Aplicação: Camada intermediária que orquestra o fluxo de trabalho. Ela não contém regras de negócio, mas coordena as chamadas entre a apresentação e as camadas inferiores.
* Negócio: Contém as entidades e as regras de negócio do sistema (ex: uma conta nova sempre começa com saldo zero). É o núcleo da aplicação.
* Persistência: Abstrai o acesso aos dados, sendo responsável por salvar e recuperar as entidades de negócio.

## Análise Comparativa das Variantes

 ### Variante 1 : Um Método por Entidade
Descrição
Nesta abordagem, as classes das camadas de Aplicação и Persistência possuem métodos específicos para cada entidade. Exemplos: cadastrarCliente(...), salvarAgencia(...), abrirConta(...).

Decisão de Projeto
A decisão foi criar uma interface de serviço explícita e fortemente tipada, onde cada operação de negócio corresponde a um método nomeado de forma clara e direta.

Justificativa e Pontos Fortes
Clareza e Simplicidade: O código é extremamente legível e fácil de entender. É óbvio qual a responsabilidade de cada método.

Baixa Complexidade Inicial: Para sistemas com um número pequeno e fixo de entidades, esta abordagem é mais rápida de implementar e não exige mecanismos complexos como reflection ou factories.

Pontos Fracos e Trade-offs

Baixa Coesão e Alto Acoplamento: Conforme criticado no material da "Aula 02", esta abordagem pode ferir a coesão, pois a camada de Aplicação acaba conhecendo detalhes de múltiplas entidades, e qualquer nova entidade exige alterações em sua interface.

Dificuldade de Manutenção e Extensão: Esta é a principal desvantagem. Adicionar uma nova entidade ao sistema (ex: 

CartaoDeCredito) exigiria a criação de novos métodos em todas as camadas (solicitarCartao, salvarCartao, etc.), tornando o sistema rígido e trabalhoso para evoluir.

### Variante 2: Meta-Parâmetro
Descrição
Esta variante utiliza métodos genéricos, como cadastrar(tipoEntidade, dados), onde um "meta-parâmetro" (tipoEntidade) informa a operação a ser realizada. Os dados são passados em uma estrutura genérica (Map<String, Object>). A criação dos objetos de negócio é delegada a uma 

Factory, centralizando essa responsabilidade.

Decisão de Projeto
A decisão foi centralizar as operações de cadastro em um único método genérico na camada de Aplicação e utilizar o padrão de projeto Factory para desacoplar a camada de Aplicação da criação concreta dos objetos de negócio.

Justificativa e Pontos Fortes
* Flexibilidade e Escalabilidade: O sistema torna-se muito mais fácil de estender. Para adicionar uma nova entidade, não é necessário alterar a assinatura do serviço na camada de Aplicação, apenas estender a EntidadeFactory e o repositório genérico.
* Baixo Acoplamento: A camada de Aplicação não está mais acoplada às classes concretas do domínio, apenas à EntidadeFactory e ao repositório genérico.

Aderência ao Princípio Aberto/Fechado (OCP): A camada de Aplicação está "fechada para modificação" e "aberta para extensão". Novas funcionalidades (novas entidades) podem ser adicionadas com o mínimo de alteração no código existente.

Pontos Fracos e Trade-offs

* Maior Complexidade Inicial: A implementação exige a criação de estruturas adicionais, como a EntidadeFactory, o que pode ser excessivo para sistemas muito simples.
* Perda de Segurança de Tipo (Type Safety): O uso de String para identificar entidades e Map para passar dados transfere a verificação de tipos do tempo de compilação para o tempo de execução. Erros de digitação no nome da entidade ou nas chaves do mapa só serão descobertos durante a execução, o que exige testes mais rigorosos.

## Conclusão
A implementação das duas variantes demonstra na prática um dos trade-offs mais importantes em arquitetura de software: simplicidade vs. flexibilidade.
A Variante 1 é mais simples e direta, ideal para projetos pequenos e com escopo bem definido. A Variante 2 é mais robusta, flexível e escalável, sendo a escolha preferível para sistemas maiores e que têm a expectativa de evoluir ao longo do tempo, mesmo que exija um esforço inicial maior.
