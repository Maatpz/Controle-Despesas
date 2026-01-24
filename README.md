# Controle de Despesas

## Sobre o Projeto
Sistema de controle financeiro pessoal que permite aos usuários gerenciar suas receitas e despesas. O projeto conta com uma API REST no backend e uma interface frontend responsiva.

## Tecnologias Utilizadas

### Backend
*   **Java 21**
*   **Spring Boot 3.5.10**
*   **Spring Web** 
*   **Spring Data JPA** 
*   **Spring Security** 
*   **MySQL Driver** 
*   **Bean Validation**
*   **Lombok** 
*   **SpringDoc OpenAPI**

### Frontend
*   **HTML5**
*   **CSS3**
*   **Bootstrap 5.3.2** 
*   **JavaScript**

## Funcionalidades
*   **Autenticação**: Cadastro (Criação de conta seguida de login manual) e Login de usuários.
*   **Dashboard**: Visão geral das finanças com totais de Receitas, Despesas e Saldo atual.
*   **Gerenciamento de Transações**:
    *   Cadastrar nova receita ou despesa.
    *   Listar transações.
    *   Filtrar por tipo (Receita/Despesa).
    *   Filtrar por período (Data Início/Fim).
    *   Editar e excluir transações.

## Endpoints da API

### Usuários (`/api/usuarios`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/api/usuarios` | Lista todos os usuários |
| `GET` | `/api/usuarios/{id}` | Busca usuário por ID |
| `POST` | `/api/usuarios` | Cadastra novo usuário |
| `PUT` | `/api/usuarios/{id}` | Atualiza dados do usuário |
| `DELETE` | `/api/usuarios/{id}` | Remove usuário |

### Transações (`/api/transacoes`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/transacoes/usuario/{usuarioId}` | Cria nova transação para o usuário |
| `GET` | `/api/transacoes/{id}` | Busca transação por ID |
| `GET` | `/api/transacoes/usuario/{usuarioId}` | Lista todas transações do usuário |
| `GET` | `/api/transacoes/usuario/{usuarioId}/tipo/{tipo}` | Filtra transações por tipo (RECEITA/DESPESA) |
| `GET` | `/api/transacoes/usuario/{usuarioId}/periodo` | Filtra transações por período (dataInicio, dataFim) |
| `PUT` | `/api/transacoes/{id}` | Atualiza uma transação existente |
| `DELETE` | `/api/transacoes/{id}` | Remove uma transação |
| `GET` | `/api/transacoes/usuario/{usuarioId}/resumo` | Obtém resumo financeiro total |
| `GET` | `/api/transacoes/usuario/{usuarioId}/resumo/periodo` | Obtém resumo financeiro por período |

## Estrutura do Frontend

| Arquivo | Descrição |
| :--- | :--- |
| `index.html` | Tela de Login e Cadastro (Landing page) |
| `dashboard.html` | Painel principal com resumo e listagem de transações |

## Como Executar

1.  Clone o repositório.
2.  Importe o projeto em sua IDE como projeto Maven.
3.  Execute a classe principal do Spring Boot (`DespesasApplication`).
4.  Acesse `http://localhost:8080/index.html` no navegador.
