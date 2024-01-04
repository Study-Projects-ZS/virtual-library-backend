# Sistema de Gerenciamento de Livros

Este é um projeto de sistema de gerenciamento de livros desenvolvido utilizando Spring Boot com Spring Data JPA para persistência e Spring Feign para integração com uma API externa de informações sobre livros (Google Books).

## Funcionalidades

- Cadastro de livros com os campos: título, autor e ISBN.
- CRUD completo para gerenciar livros (Create, Read, Update e Delete).
- Integração com API externa para buscar detalhes adicionais dos livros cadastrados, como descrição, capa do livro, avaliações e ano de publicação.
- Classe Usuário com métodos de login e autenticação através do Spring Security

## Tecnologias Utilizadas

- Spring Boot 3.2.0
- Spring Data JPA 
- Spring Feign
- Java 17
- Lombok
- Spring Security

## Requisitos

- Java 17 ou superior
- Maven 4.0.0 ou superior
- Lombok 1.18.30 ou superior
- Acesso à internet para integração com a API externa de livros

## Como Usar

1. Clone o repositório do projeto.
2. Abra o projeto em sua IDE.
3. Configure as informações de conexão com o banco de dados no arquivo `application.properties`.
4. Execute a aplicação Spring Boot.
5. Utilize ferramentas como Postman, cURL ou a interface web disponibilizada para realizar operações CRUD, consultas e listagem de livros.

## Como Contribuir

Se deseja contribuir com o projeto, siga estas etapas:

1. Faça um fork do repositório.
2. Clone o fork para o seu ambiente local.
3. Crie uma branch para suas alterações (`git checkout -b feature/nova-feature`).
4. Faça as alterações desejadas e commit (`git commit -am 'Adicione uma nova feature'`).
5. Push para a branch (`git push origin feature/nova-feature`).
6. Crie um novo Pull Request explicando suas alterações.

## Boas Práticas e Estruturação

- Siga as melhores práticas de desenvolvimento, separação de responsabilidades, tratamento de exceções, validações, uso de DTOs, etc.
- Utilize anotações do Spring Data JPA para mapeamento de entidades, consultas personalizadas, etc.
- Garanta que as alterações na aplicação estejam bem estruturada e de fácil compreensão, seguindo os padrões de codificação e organização de pacotes.

## Contato

Para dúvidas ou sugestões, entre em contato pelos emails: 

Samara Almeida via samaraalmeida379@gmail.com <br>
Wallysson Araujo via wallysson.christian@outlook.com
