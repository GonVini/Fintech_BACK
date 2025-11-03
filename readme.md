# Fintech Backend

Backend desenvolvido com Spring Boot para o projeto de Fintech da faculdade. A aplicação expõe APIs REST para gerenciar
usuários, notificações, investimentos, moedas, histórico de cotação e validações de conta integradas ao banco Oracle da FIAP.

## Requisitos
- Java 17+
- Maven 3.9+
- Banco Oracle (instância FIAP)

## Configuração
1. Atualize o arquivo `src/main/resources/application.properties` com `HOST`, `PORT`, `SERVICE`, usuário e senha da instância Oracle FIAP.
2. Garanta que as tabelas `T_FINT_USUARIO`, `T_FINT_NOTIFICACAO`, `T_FINT_INVESTIMENTO`, `T_FINT_MOEDA`, `T_FINT_HISTORICOMOEDA` e `T_FINT_VALIDACAO`
   existam com as constraints de chave estrangeira conforme o modelo do projeto. Os nomes das sequências esperadas são:
   - `SEQ_FINT_USUARIO`
   - `SEQ_FINT_NOTIFICACAO`
   - `SEQ_FINT_INVESTIMENTO`
   - `SEQ_FINT_MOEDA`
   - `SEQ_FINT_HISTORICOMOEDA`
   - `SEQ_FINT_VALIDACAO`

   Ajuste os nomes no código caso utilize sequências diferentes.
3. Opcionalmente ajuste `spring.jpa.hibernate.ddl-auto` para `validate` em produção para garantir compatibilidade com o schema existente.

## Execução
```bash
mvn spring-boot:run
```

As APIs ficam disponíveis em `http://localhost:8080`.

## Endpoints principais
- `GET /api/usuarios` – lista usuários cadastrados.
- `GET /api/notificacoes?usuarioId={id}` – filtra notificações por usuário.
- `GET /api/investimentos?usuarioId={id}` – consulta investimentos de um usuário.
- `GET /api/moedas` – consulta moedas registradas.
- `GET /api/historico-moedas?moedaId={id}` – retorna histórico de cotação de uma moeda.
- `GET /api/validacoes/usuario/{id}` – verifica o status de validação de um usuário.

Todos os recursos possuem endpoints `POST`, `PUT` e `DELETE` para criar, atualizar e remover registros respeitando os códigos de status HTTP.
