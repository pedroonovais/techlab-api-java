# TechLab API Java

**TechLab API Java** é uma API desenvolvida em **Java com Spring Boot** para o sistema de **gerenciamento de pátios**, com foco no controle de localização de motos utilizando sensores e tecnologia RFID. A solução é modularizada em camadas, utiliza banco de dados relacional e fornece documentação via Swagger.

---

## 📌 Funcionalidades

- Gerenciamento de **usuários**
- Registro e controle de **motos** associadas a usuários
- Cadastro e monitoramento de **sensores** posicionados no pátio
- Registro de **leituras RFID** para rastrear a movimentação das motos
- Administração de **pátios**, com possibilidade de configurar capacidade e localização
- API RESTful com respostas em JSON
- Documentação interativa com Swagger

---

# 👩‍💻 Participantes

- Pedro Henrique Mendonça de Novais - RM555276
- Davi Alves de Lima - RM556008
- Rodrigo Alcides Bohac Ríos - RM554826

---

## 🏗 Estrutura do Projeto

- **controller**: Camada de apresentação (REST endpoints)
- **service**: Camada de regras de negócio
- **model**: Entidades e DTOs da aplicação
- **repository**: Interface com o banco de dados (JPA Repositories)
- **config**: Configurações gerais (Swagger, CORS etc.)

---

## 💻 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Oracle Database (ou outro banco relacional)
- Swagger (Springdoc OpenAPI)
- Maven

---

## 🚀 Como Executar o Projeto

Clone o repositório:

```bash
git clone https://github.com/seu-usuario/techlab-api-java
cd techlab-api-java
```

Configure o `application.properties` com as credenciais do banco de dados.

Execute a aplicação:

```bash
./mvnw spring-boot:run
```

Acesse a documentação Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📬 Endpoints da API

### 🔹 Usuários (`/usuario`)

| Método | Rota               | Descrição                   |
|--------|--------------------|-----------------------------|
| GET    | `/usuario`         | Lista todos os usuários     |
| GET    | `/usuario/{id}`    | Retorna um usuário por ID   |
| POST   | `/usuario`         | Cria um novo usuário        |
| PUT    | `/usuario/{id}`    | Atualiza um usuário         |
| DELETE | `/usuario/{id}`    | Remove um usuário           |

**Exemplo de corpo (POST/PUT):**
```json
{
  "nome": "João Silva",
  "email": "joao.silva@example.com",
  "senha": "senhaSegura123!",
  "cpf": "43264531844",
  "status": "ATIVO",
  "perfil": "ADMIN"
}
```

---

### 🔹 Motos (`/moto`)

| Método | Rota            | Descrição                    |
|--------|-----------------|------------------------------|
| GET    | `/moto`         | Lista todas as motos         |
| GET    | `/moto/{id}`    | Retorna uma moto por ID      |
| POST   | `/moto`         | Cadastra uma nova moto       |
| PUT    | `/moto/{id}`    | Atualiza os dados da moto    |
| DELETE | `/moto/{id}`    | Remove uma moto              |

**Exemplo de corpo (POST):**
```json
{
  "marca": "Honda",
  "modelo": "CG 160",
  "placa": "HSI2003",
  "chassi": "9C2KC1670HR123456",
  "motor": "KC16E1234567",
  "imeiIot": "359881080123456"
}
```

**Exemplo de corpo (PUT):**
```json
{
  "marca": "Yamaha",
  "modelo": "Factor",
  "placa": "HSI2003",
  "chassi": "9C2KC1670HR123456",
  "motor": "KC16E7654321",
  "imeiIot": "359881080123456",
  "rfId": 2
}
```

---

### 🔹 Sensores (`/sensor`)

| Método | Rota              | Descrição                      |
|--------|-------------------|--------------------------------|
| GET    | `/sensor`         | Lista todos os sensores        |
| GET    | `/sensor/{id}`    | Retorna um sensor por ID       |
| POST   | `/sensor`         | Cadastra um novo sensor        |
| PUT    | `/sensor/{id}`    | Atualiza um sensor existente   |
| DELETE | `/sensor/{id}`    | Remove um sensor               |

**Exemplo de corpo (POST/PUT):**
```json
{
  "codigoIdentificacao": "SENSOR-001",
  "tipo": "TEMPERATURA",
  "localizacaoFisica": "Bloco A",
  "status": "ATIVO"
}
```

---

### 🔹 RFID (`/rfid`)

| Método | Rota           | Descrição                         |
|--------|----------------|-----------------------------------|
| GET    | `/rfid`        | Lista todos os registros RFID     |
| GET    | `/rfid/{id}`   | Retorna um RFID por ID            |
| POST   | `/rfid`        | Registra um novo RFID             |
| PUT    | `/rfid/{id}`   | Atualiza um registro RFID         |
| DELETE | `/rfid/{id}`   | Remove um registro RFID           |

**Exemplo de corpo (POST/PUT):**
```json
{
  "tipo": "ENTRADA",
  "localizacaoFisica": "Portão 3",
  "status": "ATIVO"
}
```

---

### 🔹 Pátios (`/patio`)

| Método | Rota              | Descrição                        |
|--------|-------------------|----------------------------------|
| GET    | `/patio`          | Lista todos os pátios            |
| GET    | `/patio/{id}`     | Retorna um pátio por ID          |
| POST   | `/patio`          | Cadastra um novo pátio           |
| PUT    | `/patio/{id}`     | Atualiza os dados de um pátio    |
| DELETE | `/patio/{id}`     | Remove um pátio                  |

**Exemplo de corpo (POST/PUT):**
```json
{
  "nome": "Pátio Central",
  "localizacao": "Av. Industrial, 123",
  "capacidadeTotal": 100,
  "vagasDisponiveis": 70,
  "descricao": "Pátio coberto e monitorado"
}
```
