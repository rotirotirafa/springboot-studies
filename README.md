# API de Produtos com Spring Boot e PostgreSQL

Esta é uma API REST para gerenciar produtos, desenvolvida com Spring Boot e Spring Data JPA, conectada a um banco de dados PostgreSQL que roda em um container Docker.

## Funcionalidades

* Criar novos produtos
* Listar todos os produtos
* Buscar um produto específico por ID
* Atualizar um produto existente
* Deletar um produto

## Tecnologias Utilizadas

* Java 17 (ou superior)
* Spring Boot 3.x.x (ou a versão que você está usando)
* Spring Data JPA
* Maven
* PostgreSQL
* Docker e Docker Compose
* Lombok

## Pré-requisitos

Antes de começar, você precisa ter as seguintes ferramentas instaladas na sua máquina:

* [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior
* [Apache Maven 3.6](https://maven.apache.org/download.cgi) ou superior (ou pode usar o Maven Wrapper incluído no projeto)
* [Docker](https://www.docker.com/get-started)
* [Docker Compose](https://docs.docker.com/compose/install/) (geralmente já vem com o Docker Desktop)
* Um cliente SQL de sua preferência (opcional, para inspecionar o banco ou executar scripts manualmente, como DBeaver, pgAdmin, ou psql).

## Configuração do Ambiente e Execução

Para configurar e rodar o projeto localmente, siga estes passos:

### 1. Banco de Dados (PostgreSQL com Docker)

O projeto usa Docker Compose para configurar e rodar uma instância do PostgreSQL.

1.  Abra o terminal na pasta raiz do projeto (onde está o arquivo `docker-compose.yml`).
2.  Execute o comando abaixo para iniciar o container do PostgreSQL em segundo plano:

    ```bash
    docker-compose up -d
    ```

    Este comando vai:
    * Baixar a imagem do PostgreSQL (se você ainda não tiver).
    * Criar e iniciar um container chamado `lojadb_container`.
    * Configurar o banco com os seguintes dados:
        * **Usuário:** `admin`
        * **Senha:** `admin`
        * **Nome do Banco:** `lojadb`
        * **Porta:** `5432` (acessível em `localhost:5432`)
    * Criar um volume Docker chamado `postgres_data_loja` para que os dados do banco sejam persistidos.

### 2. Criação da Estrutura do Banco (Script SQL)

Com o container do PostgreSQL em execução, você precisa criar as tabelas. O script SQL para isso está na pasta `/sql/` do projeto.

1.  Conecte-se ao banco de dados `lojadb` no container PostgreSQL usando seu cliente SQL preferido e as credenciais mencionadas acima (`localhost`, porta `5432`, usuário `admin`, senha `admin`, banco `lojadb`).
2.  Localize e execute o script SQL que está em:

    ```
    /sql/01_create_tables.sql
    ```

    (Se o nome do arquivo for diferente, por favor, ajuste. Se houver vários scripts, execute-os na ordem correta.)

    *Exemplo do script `sql/01_create_tables.sql` (que criamos anteriormente):*
    ```sql
    CREATE TABLE IF NOT EXISTS produto (
        id SERIAL PRIMARY KEY,
        nome VARCHAR(255) NOT NULL,
        descricao TEXT,
        preco DECIMAL(10, 2) NOT NULL
    );
    ```

### 3. Executando a Aplicação Spring Boot

Com o banco de dados configurado e as tabelas criadas, você pode rodar a aplicação Spring Boot.

#### Usando o Maven Wrapper (Recomendado):

O Maven Wrapper (`mvnw`) permite executar o projeto com a versão correta do Maven sem precisar instalá-lo globalmente.

* No Linux ou macOS:
    ```bash
    ./mvnw spring-boot:run
    ```
* No Windows:
    ```bash
    mvnw.cmd spring-boot:run
    ```

#### Usando o Maven Instalado Globalmente:

Se você prefere usar sua instalação global do Maven:

```bash
mvn spring-boot:run