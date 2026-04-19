# 💰 Serviço de Preços (Microsserviço B)

Este repositório contém o microsserviço de **Preços**, que atua como um provedor de dados de custo para o ecossistema de microserviços. Ele é responsável por gerir e servir os valores monetários associados aos produtos do catálogo.

## 🏗️ Arquitetura do Projeto

O serviço foi desenvolvido seguindo o princípio de responsabilidade única, focado em performance e simplicidade de integração.

* **Papel no Sistema:** Atua como um *Backend Provider* (Serviço B), fornecendo dados de preços para o Microsserviço de Catálogo (Serviço A).
* **Persistência:** Utiliza um repositório em memória otimizado (`Map`), simulando uma base de dados de alta velocidade para consulta de preços.
* **Modelo de Dados:** Focado no desacoplamento, relacionando apenas o `produtoId` ao seu respectivo `valor`.

## 🛠️ Tecnologias Utilizadas

* **Java 26** & **Spring Boot 4.0.5**
* **Spring Web** para criação de APIs RESTful
* **SpringDoc OpenAPI (Swagger)** para documentação e testes de contrato
* **Lombok** para redução de código boilerplate

## 🌟 Diferenciais e Boas Práticas

### 1. Endpoint de Consulta em Lote (Batch API)
Para suportar arquiteturas de alta performance, o serviço disponibiliza o endpoint `/preco/lote`. Ele permite que outros serviços consultem múltiplos preços numa única chamada HTTP POST, reduzindo drasticamente o *overhead* de rede e a latência de integração.

### 2. Desacoplamento de Domínio
O serviço utiliza `PrecoResponseDTO` (Java Record) para expor apenas os dados necessários, garantindo que alterações internas no modelo de domínio não quebrem os contratos com os serviços consumidores.

### 3. Prontidão para Cloud (AWS Ready)
* **Configuração:** O serviço está configurado para rodar na porta padrão `8081`, facilitando o mapeamento de portas em instâncias EC2 ou containers.
* **Visibilidade:** A documentação Swagger integrada permite que equipes de infraestrutura e outros desenvolvedores validem o funcionamento do serviço imediatamente após o deploy.

## 🚀 Como Executar

### 1. Pré-requisitos
* **Java JDK 17** (ou superior) instalado.
* **Maven 3.8+** instalado.

### 2. Passo a Passo
1.  **Clone o repositório:**
    ```bash
    git clone <url-do-teu-repositorio-preco>
    cd sd-preco
    ```
2.  **Compile o projeto:**
    ```bash
    mvn clean install
    ```
3.  **Inicie a aplicação:**
    ```bash
    mvn spring-boot:run
    ```
4.  **Acesse o serviço:**
    A aplicação estará disponível em: `http://localhost:8081`

## 📖 Documentação da API

A documentação interativa (Swagger) pode ser acedida em:
`http://localhost:8081/swagger-ui.html`

### Endpoints Principais:
* `GET /preco/{id}`: Busca o preço individual de um produto pelo seu ID.
* `POST /preco/lote`: Recebe uma lista de IDs e devolve um mapa de preços (Lote).

---
**Desenvolvido para fins acadêmicos - IFSP.**