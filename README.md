# Spring Boot GraphQL CRUD Example

Complete working example of **Create, Read, Update, Delete** operations using **Spring Boot + Spring for GraphQL**.

## Tech Stack

- Java 17
- Spring Boot 3.3.4
- Spring for GraphQL
- Spring Data JPA
- H2 In-Memory Database
- Lombok

## How to Run

### Prerequisites
- JDK 17+
- Maven 3.8+

### Steps

```bash
# 1. Go to project folder
cd spring-boot-graphql-crud

# 2. Build & Run
mvn spring-boot:run
```

Application will start on: **http://localhost:8080**

### GraphiQL UI (Recommended)
Open in browser: **http://localhost:8080/graphiql**

### H2 Console (optional)
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:productdb`
- Username: `sa`
- Password: (leave empty)

---

## GraphQL Operations

### 1. CREATE Product
```graphql
mutation {
  createProduct(input: {
    name: "iPhone 16"
    description: "Latest Apple smartphone"
    price: 999.99
    quantity: 50
  }) {
    id
    name
    description
    price
    quantity
  }
}
```

### 2. GET All Products
```graphql
query {
  getAllProducts {
    id
    name
    description
    price
    quantity
  }
}
```

### 3. GET Product by ID
```graphql
query {
  getProductById(id: 1) {
    id
    name
    description
    price
    quantity
  }
}
```

### 4. UPDATE Product
```graphql
mutation {
  updateProduct(id: 1, input: {
    price: 899.99
    quantity: 40
  }) {
    id
    name
    price
    quantity
  }
}
```

### 5. DELETE Product
```graphql
mutation {
  deleteProduct(id: 1)
}
```

---
A. Using curl (Command Line) GraphQL always uses POST and sends a JSON body.
use git bash prompt

1. Create Product 
```Bash$:
curl -X POST http://localhost:8080/graphql \
-H "Content-Type: application/json" \
-d '{
"query": "mutation { createProduct(input: { name: \"iPhone 16\", description: \"Apple phone\", price: 999.99, quantity: 50 }) { id name price quantity } }"
}'

```

2. Get All Products
```Bash$:
curl -X POST http://localhost:8080/graphql \
-H "Content-Type: application/json" \
-d '{
"query": "query { getAllProducts { id name price quantity } }"
}'

```
3. Get Product by ID
```Bash$:
 curl -X POST http://localhost:8080/graphql \
   -H "Content-Type: application/json" \
   -d '{
   "query": "query { getProductById(id: 1) { id name description price quantity } }"
   }'
```
4. Update Product
```Bash$:
   curl -X POST http://localhost:8080/graphql \
   -H "Content-Type: application/json" \
   -d '{
   "query": "mutation { updateProduct(id: 1, input: { price: 899.99, quantity: 40 }) { id name price quantity } }"
   }'
```
5.  Delete Product
```Bash$
    curl -X POST http://localhost:8080/graphql \
    -H "Content-Type: application/json" \
    -d '{
    "query": "mutation { deleteProduct(id: 1) }"
    }'
```

## Project Structure

```
src/main/java/com/example/graphqlcrud/
├── GraphqlCrudApplication.java
├── entity/
│   └── Product.java
├── repository/
│   └── ProductRepository.java
├── dto/
│   ├── ProductInput.java
│   └── ProductUpdateInput.java
└── controller/
    └── ProductController.java

src/main/resources/
├── application.properties
└── graphql/
    └── schema.graphqls
```

## Notes

- All data is stored in **H2 in-memory** database (data is lost on restart).
- GraphQL endpoint: `POST http://localhost:8080/graphql`
- GraphiQL playground: `http://localhost:8080/graphiql`
