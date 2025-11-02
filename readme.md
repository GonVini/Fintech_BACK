# Fintech Backend

Aplicação Spring Boot que expõe APIs REST para o projeto de Fintech. O backend inclui cadastro de usuários, contas e transações financeiras, utilizando JPA para persistência em banco Oracle.

## Requisitos
- Java 17+
- Maven 3.9+
- Banco Oracle (instância FIAP)

## Configuração
1. Atualize o arquivo `src/main/resources/application.properties` com as credenciais da instância Oracle FIAP.
2. Crie as tabelas executando a aplicação com `spring.jpa.hibernate.ddl-auto=update` ou via scripts SQL gerados a partir das entidades JPA.

## Execução
```
mvn spring-boot:run
```

As APIs estarão disponíveis em `http://localhost:8080`.

## Endpoints principais
- `GET /api/users` – consulta usuários cadastrados
- `GET /api/accounts` – consulta contas vinculadas aos usuários
- `GET /api/transactions` – consulta transações financeiras

Utilize os endpoints `POST`, `PUT` e `DELETE` equivalentes para criar, atualizar e remover recursos.
