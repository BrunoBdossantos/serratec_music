# 🎵 Serratec Music API

API RESTful desenvolvida como projeto final da disciplina de Back-end do Serratec. A API gerencia usuários, artistas, músicas e playlists, aplicando os conceitos de Spring Boot, JPA/Hibernate e relacionamentos.

---

## 🧑‍💻 Autor

* **Nome:** Bruno Barbosa dos Santos

---

## 🛠️ Tecnologias Utilizadas

O projeto foi construído com as seguintes tecnologias:

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.3.0
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL
* **Gerenciador de Dependências:** Maven
* **Validação:** Spring Validation (Bean Validation)
* **Documentação:** Springdoc OpenAPI (Swagger)
* **Ambiente de Desenvolvimento (IDE):** Eclipse

---

## ⚙️ Pré-requisitos

Antes de rodar a aplicação, certifique-se de que você tem os seguintes softwares instalados:

* [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior
* [Apache Maven](https://maven.apache.org/download.cgi)
* [PostgreSQL](https://www.postgresql.org/download/) (servidor de banco de dados)
* Uma IDE Java (ex: Eclipse, IntelliJ) ou um editor de código (ex: VS Code)
* Uma ferramenta de banco de dados (ex: DBeaver ou pgAdmin)

---

## 🏁 Como Rodar o Projeto

Siga os passos abaixo para executar a aplicação localmente:

### 1. Clonar o Repositório
(Se o projeto estivesse no Git, você o clonaria. Caso contrário, apenas abra o projeto no Eclipse).

### 2. Configurar o Banco de Dados

1.  Abra seu DBeaver (ou pgAdmin).
2.  Crie um novo banco de dados (database) chamado exatamente:
    ```sql
    CREATE DATABASE serratec_music;
    ```

### 3. Configurar a Aplicação

1.  Navegue até o arquivo `src/main/resources/application.properties`.
2.  **IMPORTANTE:** Altere as seguintes linhas com o seu usuário e senha do PostgreSQL:
    ```properties
    spring.datasource.username=seu_usuario_postgres
    spring.datasource.password=sua_senha_postgres
    ```

### 4. Executar a Aplicação

1.  No Eclipse, encontre a classe `MusicApplication.java`.
2.  Clique com o botão direito nela.
3.  Vá em **Run As** -> **Java Application**.
4.  O console deverá exibir a mensagem "Tomcat started on port(s): 8080".

---

## 📚 Como Usar a API

Toda a documentação da API, incluindo todos os endpoints, modelos de dados e descrições, foi gerada automaticamente com o Swagger (Springdoc).

Após iniciar a aplicação, acesse o seguinte link no seu navegador:

**[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

Nesta interface, você pode testar todos os endpoints (POST, GET, PUT, DELETE) diretamente.

### Principais Endpoints

* `POST /usuarios` - Cria um novo usuário e seu perfil aninhado.
* `GET /usuarios/{id}` - Busca um usuário.
* `POST /artistas` - Cria um novo artista.
* `POST /musicas` - Cria uma nova música (associando artistas existentes).
* `POST /playlists` - Cria uma nova playlist (associando um `donoId`).
* `PUT /playlists/{id}/musicas` - Atualiza a lista de músicas de uma playlist.

---

## 🗄️ Modelagem de Dados (Relacionamentos)

* **Usuario <-> Perfil** (OneToOne)
* **Usuario <-> Playlist** (OneToMany)
* **Musica <-> Artista** (ManyToMany)
* **Playlist <-> Musica** (ManyToMany)