# 🎮 CIRI Games - API

<br />

<div align="left">
	<img src="https://ik.imagekit.io/0wyyadtva/Copilot_20251019_211526.png?updatedAt=1760919559940" title="source: imgur.com" width="35%"/>
</div>

<br />

## 🚀 Descrição do Projeto

Essa Loja de Games é um **backend em Spring Boot**, com **Java 17** e **JPA/Hibernate**, que permite gerenciar produtos e categorias de uma loja de jogos.
 O projeto expõe **endpoints RESTful** para operações **CRUD** em **Categorias** e **Produtos**, com validações e filtros de preço.

------

## 🛠 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.x
- Spring Data JPA
- H2 / MySQL (configurável)
- REST API
- Jakarta Validation

------

## ⚙️ Configuração do Projeto

### 1. Clonar o repositório

```
git clone git@github.com:cdouradom/Loja-de-Games.git
cd lojadegames
```

### 2. Build e Run

```
./mvnw clean install
./mvnw spring-boot:run
```

### 3. Configuração do banco

No arquivo `application.properties` configure:

```
spring.datasource.url=jdbc:mysql://localhost:3306/lojadegames
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
```

> Para teste rápido, você pode usar o **H2 em memória**, sem MySQL.

------

## 📦 Estrutura da API

### Endpoints de Categorias

Base URL: `/categorias`

| Método | Endpoint       | Descrição                          | Status de Retorno    |
| ------ | -------------- | ---------------------------------- | -------------------- |
| GET    | `/`            | Retorna todas as categorias        | 200 OK               |
| GET    | `/{id}`        | Retorna categoria por ID           | 200 OK / 404         |
| GET    | `/nome/{nome}` | Retorna categorias contendo o nome | 200 OK               |
| POST   | `/`            | Cria nova categoria                | 201 Created          |
| PUT    | `/`            | Atualiza categoria existente       | 200 OK / 404         |
| DELETE | `/{id}`        | Deleta categoria por ID            | 204 No Content / 404 |

**Exemplo de POST Categoria**

```
POST http://localhost:8080/categorias
Content-Type: application/json

{
  "nome": "Aventura"
}
```

------

### Endpoints de Produtos

Base URL: `/produtos`

| Método | Endpoint                | Descrição                                                 | Status de Retorno    |
| ------ | ----------------------- | --------------------------------------------------------- | -------------------- |
| GET    | `/`                     | Retorna todos os produtos                                 | 200 OK               |
| GET    | `/{id}`                 | Retorna produto por ID                                    | 200 OK / 404         |
| GET    | `/titulo/{titulo}`      | Retorna produtos que contenham o título                   | 200 OK               |
| GET    | `/maior/{preco}`        | Retorna produtos com preço maior que o valor (ASC)        | 200 OK               |
| GET    | `/maiorouigual/{preco}` | Retorna produtos com preço maior ou igual (ASC)           | 200 OK               |
| GET    | `/menor/{preco}`        | Retorna produtos com preço menor que o valor (DESC)       | 200 OK               |
| GET    | `/menorouigual/{preco}` | Retorna produtos com preço menor ou igual (DESC)          | 200 OK               |
| POST   | `/`                     | Cria novo produto (verifica categoria existente)          | 201 Created / 400    |
| PUT    | `/`                     | Atualiza produto existente (verifica categoria existente) | 200 OK / 404 / 400   |
| DELETE | `/{id}`                 | Deleta produto por ID                                     | 204 No Content / 404 |

**Exemplo de POST Produto**

```
POST http://localhost:8080/produtos
Content-Type: application/json

{
  "titulo": "The Legend of Zelda: Tears of the Kingdom",
  "descricao": "Aventura no reino de Hyrule",
  "foto": "https://ik.imagekit.io/andremds/nba%202k%2025.avif",
  "preco": 299.90,
  "quantidade": 15,
  "categoria": {
    "id": 1
  }
}
```

**Exemplo de GET Produtos acima de 400 (ordem crescente)**

```
GET http://localhost:8080/produtos/maior/400
```

------

## 📊 DER (Diagrama Entidade-Relacionamento)

<div align="left">   <img src="https://github.com/cdouradom/Loja-de-Games/blob/main/imagens/DER.png?raw=true" width="50%" title="DER Loja de Games"/> </div>

------

## 🔗 Observações

- Todos os endpoints suportam **CORS** (`origins="*"`), permitindo consumo de qualquer front-end.
- Validações via `@Valid` garantem que os dados enviados estejam corretos.
- Atualizações (`PUT`) e exclusões (`DELETE`) retornam **404 Not Found** se o recurso não existir.
- Produtos só podem ser criados/atualizados se a **categoria existir**; caso contrário, retorna **400 Bad Request**.
- **Variável base para front-end**:

```
BASE_URL=http://localhost:8080
```

------

## 🧪 Testes no Insomnia / Postman

Você pode importar os endpoints no Insomnia ou Postman criando requests do tipo **GET, POST, PUT e DELETE**.

Exemplo de GET de produtos acima de 400:

```
GET http://localhost:8080/produtos/maior/400
Accept: application/json
```

Exemplo de POST de produto:

```
POST http://localhost:8080/produtos
Content-Type: application/json

{
  "titulo": "God of War Ragnarok",
  "descricao": "Aventura em Midgard",
  "foto": "https://linkdafoto.com/gow.png",
  "preco": 499.90,
  "quantidade": 20,
  "categoria": { "id": 1 }
}
```

> Dica: No Insomnia, vá em **Import > Raw Text** ou **Clipboard** e cole o JSON das requests.

------

## Contribuições

Este repositório é parte de um projeto educacional, mas contribuições são bem-vindas!

- Criar uma **issue**
- Enviar um **pull request**
- Compartilhar com colegas que estejam aprendendo Java

------

## Contato

Desenvolvido por [**Cintia Dourado**](https://github.com/cdouradom)
Para dúvidas, sugestões ou colaborações, entre em contato via GitHub ou abra uma issue!

