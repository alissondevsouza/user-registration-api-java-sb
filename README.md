# API para Gerenciamento de Usuários

Este projeto é uma API para Gerenciamento de Usuários construída em Java 17. Esta API viabiliza consultas, cadastros, 
atualizações e exclusões de usuários. Alêm disso, ela conta com com autenticaçã de usuário e tambem com testes unitários.

## Indice
- [Tecnologias Utilizadas](#Tecnologias Utilizadas)
- [Funcionalidades Principais](#Funcionalidades Principais)
- [Instalação](#Instalação)
- [Execução](#Execução)
- [Endpoints da API](#Endpoints da API)
- [Como Contribuir para o Projeto](#Como Contribuir para o Projeto)

## Tecnologias Utilizadas
- <strong>Spring Boot:</strong> Framework que facilita a criação de aplicativos Java baseados em Spring.
- <strong>Spring Security:</strong> Fornece autenticação e autorização robustas para aplicativos Spring.
- <strong>Java JWT:</strong> Biblioteca para a criação e validação de JSON Web Tokens (JWT) em Java.
- <strong>Spring Data JPA:</strong> Facilita a implementação de camadas de acesso a dados baseadas em JPA.
- <strong>PostgreSQL:</strong> Banco de dados relacional utilizado para armazenar dados de usuários.
- <strong>H2 Database:</strong> Banco de dados em memória utilizado para testes.
- <strong>JUnit:</strong> Framework para escrever testes automatizados em Java.
- <strong>Mockito:</strong> Biblioteca de mocking popular para testes unitários em Java.

## Funcionalidades Principais
- <strong>Cadastro de Usuários:</strong> A API permite o cadastro de novos usuários.
- <strong>Autenticação:</strong> Implementa um sistema de autenticação seguro utilizando JWT.
- <strong>Autorização:</strong> Garante que apenas usuários autorizados tenham acesso a recursos específicos.
- <strong>Gerenciamento de Perfis de Usuários:</strong> Oferece funcionalidades para atribuir e modificar perfis de usuário.
- <strong>Testes Automatizados:</strong> Conta com testes unitários e de integração para garantir a robustez da aplicação.

## Instalação
1. Clone o repositório:
```bash
git clone git@github.com:alissondevsouza/user-registration-api-java-sb.git
```
2. Instale as dependências com Maven:
```bash
mvn install
```
## Execução
1. Navegue até a pasta que contem o docker-compose da base de dados:
```bash
cd .\Postgres-Docker\
```
2. Execute o docker-compose para subir o banco de dados da aplicação:
```bash
docker-compose up -d
```
3. Execute a aplicação:
4. Acesse a aplicação em http://localhost:9080

## Endpoints da API
A API oferece os seguintes endpoints:

**LOGIN**
```markdown
POST authentication/login - Autentica um usuário e gera um token JWT.
```
*Request Body:*
```json
{
  "userLogin": "admin",
  "userPassword": "1234"
}
```
*Response:*
```json
{
  "status": "OK",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1c2VyLXJlZ2lzdHJhdGlvbiIsInN1YiI6ImFkbWluIiwiZXhwIjoxNzA4ODA0OTg4fQ.KwJ-5xuHnQ7AlP7u8JdrwyvjKC4N98rHbk7jT6vZvns"
}
```

<br/>

**GET USERS**
```markdown
GET /users - Recupera uma lista de todos os usuários.
```
*Response:*
```json
{
  "status": "OK",
  "data": {
    "users": [
      {
        "id": 1,
        "userName": "Admin",
        "userEmail": "admin@teste.com",
        "userLogin": "admin",
        "role": "ADMIN"
      },
      {
        "id": 2,
        "userName": "Usuario 1",
        "userEmail": "usuario1@teste.com",
        "userLogin": "usuario1",
        "role": "USER"
      },
      {
        "id": 3,
        "userName": "Usuario 2",
        "userEmail": "usuario2@teste.com",
        "userLogin": "usuario2",
        "role": "USER"
      }
    ]
  }
}
```

<br/>

**GET USER POR ID**
```markdown
GET /user/1 - Recupera um usuário por ID.
```
*Response:*
```json
{
    "status": "OK",
    "data": {
        "id": 1,
        "userName": "Admin",
        "userEmail": "admin@teste.com",
        "userLogin": "admin",
        "role": "ADMIN"
    }
}
```

<br/>

**CREATE USER**
```markdown
POST /users - Cria um novo usuário.
```
*Request Body:*
```json
{
    "userName": "Usuario 2",
    "userEmail": "usuario2@teste.com",
    "userLogin": "usuario2",
    "userPassword": "1234",
    "role": "USER"
}
```
*Response:*
```json
{
    "status": "CREATED",
    "data": {
        "id": 3,
        "userName": "Usuario 2",
        "userEmail": "usuario2@teste.com",
        "userLogin": "usuario2",
        "role": "USER"
    }
}
```

<br/>

**UPDATE USER**
```markdown
PUT /users/update/3 - Atualiza um usuário.
```
*Request Body:*
```json
{
  "userName": "Usuario Update"
}
```
*Response:*
```json
{
  "status": "OK",
  "data": {
    "id": 3,
    "userName": "Usuario Update",
    "userEmail": "usuario2@teste.com",
    "userLogin": "usuario2",
    "role": "USER"
  }
}
```

<br/>

**DELETE USER**
```markdown
DELETE /users/delete/3 - Deleta um usuário.
```
*Response:*
```json
{
  "status": "OK",
  "data": "User with ID 3 has been successfully deleted!"
}
```
 ## Como Contribuir para o Projeto 

<div>
    <ol>
        <li>Faça um fork do projeto.</li>
        <li>Crie uma nova branch com as suas alterações:
        <code>git checkout -b my-feature</code></li>
        <li>Salve as alterações e crie uma mensagem de commit contando o que você fez:
        <code>git commit -m "feature: My new feature"</code></li>
        <li>Envie as suas alterações:
        <code>git push origin my-feature</code></li>
        <li>Abra um Pull Request para a branch develop</li>
    </ol>
</div>
