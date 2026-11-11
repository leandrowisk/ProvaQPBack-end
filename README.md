# ProvaQP — Backend

API REST desenvolvida em Java com Spring Boot para gerenciamento de pessoas, autenticação de usuários e persistência de dados em banco H2.

O projeto utiliza Spring Security e JWT para proteger os endpoints.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (JJWT 0.12.6)
- H2 Database
- Maven
- Lombok

## Funcionalidades

- Autenticação com e-mail e senha
- Geração e validação de tokens JWT
- Proteção de endpoints com Spring Security
- Cadastro de pessoas
- Listagem de pessoas
- Persistência de dados com JPA
- Banco H2 em memória
- Criação automática de usuário administrador na inicialização

## Pré-requisitos

Para executar a aplicação, é necessário:

- JDK compatível com a versão configurada no `pom.xml`
- Maven ou Maven Wrapper
- IntelliJ IDEA ou outra IDE Java

## Como executar

Execução rápida

O projeto está configurado para execução local, sem necessidade de instalar ou configurar um banco de dados.
1. Clone o repositório.
2. Abra o projeto no IntelliJ IDEA.
3. Aguarde o Maven baixar as dependências.
4. Execute a classe principal do Spring Boot.
5. A API estará disponível em http://localhost:8090.
6. Usuário fixo para login
   E-mail: admin@admin.com
   Senha: 12345
   O token expirará a cada 3 minutos, funcionalidade de logout implementada.

Acesso ao console do Banco H2:
Acesse http://localhost:8090/h2-console.
JDBC URL: jdbc:h2:mem:qpdb
Usuário: sa
Senha: password ou 12345
