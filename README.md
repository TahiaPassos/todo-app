# TO-DO LIST - API DE GERENCIAMENTO DE TAREFAS

API REST desenvolvida em Java com Spring Boot para gerenciamento de tarefas, permitindo operações como criar, listar, alterar e excluir.

#### TECNOLOGIAS UTILIZADAS
- Java 21
- Spring Boot 4.0.0
- Spring Data JPA
- Maven 3.9.11
- SQL Server Express

#### FUNCIONALIDADES
- Criação de tarefas

- Listagem de todas as tarefas ordenadas por data de criação

- Busca de tarefa por id

- Busca de tarefas por título

- Atualização de tarefas

- Exclusão de tarefas

#### PRÉ-REQUISITOS
- Java 17+
- Maven 3.x
- Git
- Configurar seu application.properties


#### Clonar o repositório

  ``` bash
   git clone https://github.com/TahiaPassos/todo-app.git
   ```
  
 #### Navegar até a pasta do repositório
  ``` bash
   cd todo-app
   ```

#### Fazer o build usando o Maven (certifique-se de que ele está nas variáveis de ambiente do sistema)
  ``` bash
   mvn clean install
   ```

#### Execute a aplicação

   ``` bash
  mvn spring-boot:run
   ```

Para acessar a API localmente, utilize uma ferramenta como Postman ou Insomnia, ou utilize um comando curl:
 
 ``` bash
  curl http://localhost:8080/api/tarefas
   ```

Lembre-se que para refletir as alterações das funcionalidades é necessário configurar seu arquivo de application.properties. Aqui está um exemplo:

 ``` properties
  spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=todo_db;encrypt=false
  spring.datasource.username=sa
  spring.datasource.password=sua_senha_aqui
  spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
   ```

### Endpoints disponíveis
 ``` bash
  POST /api/tarefas
  GET /api/tarefas
  GET /api/tarefas/{id}
  GET /api/tarefas/buscar?titulo=estudar
  PUT /api/tarefas/{id}
  DELETE /api/tarefas/{id}
   ```

### Script utilizado para a criação do banco de dados

``` sql
CREATE TABLE tb_tarefas (
    id INT IDENTITY(1,1) PRIMARY KEY,
    titulo NVARCHAR(100) NOT NULL,
    descricao NVARCHAR(500),
    data_criacao DATETIME2 DEFAULT SYSDATETIME(),
    status NVARCHAR(20) DEFAULT 'PENDENTE'
```
);

