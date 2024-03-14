Para executar o projeto usando Spring Boot e Docker, siga estas etapas:

**Clonar o Repositório**
[Clone o repositório do projeto da sua plataforma de hospedagem, como GitHub.](https://github.com/L42Matheus/Desafio_Backend)


**Configuração do Banco de Dados MySQL**
   
![image](https://github.com/L42Matheus/Desafio_Backend/assets/77408554/f13bcd3c-ab07-492e-a857-85b1fa60c2d4)

**Certifique-se de ter o Docker instalado em sua máquina. Em seguida, crie um contêiner Docker para o banco de dados MySQL com as configurações necessárias:**

![image](https://github.com/L42Matheus/Desafio_Backend/assets/77408554/44cf2c64-f680-4e89-9881-cb1c145cc0ce)


basha >
docker-compose up -d

Isso iniciará um contêiner MySQL na porta 3308, com uma base de dados chamada "starwars" e senha "Admin@" para o usuário root.


**Compilar e Executar o Projeto**
Navegue até o diretório do projeto clonado e execute o seguinte comando para compilar e executar o projeto Spring Boot:

bash >
mvn spring-boot:run

ou

![image](https://github.com/L42Matheus/Desafio_Backend/assets/77408554/3b83e079-7342-4300-9b6f-e792478160e1)





**Endpoints da API:**

Agora que o projeto está em execução, você pode acessar os endpoints da API usando ferramentas como Postman ou cURL:

[Link para a coleção do Postman](https://github.com/L42Matheus/Desafio_Backend/blob/master/SWAPI-STAR-WARS.postman_collection.json)

- **Atualizar Descrição de um Filme:**
  - PUT - [http://localhost:8080/swapi/api/films/4/description](http://localhost:8080/swapi/api/films/4/description)

- **Obter Lista de Filmes:**
  - GET - [http://localhost:8080/swapi/api/films](http://localhost:8080/swapi/api/films)

- **Obter Detalhes de um Filme por ID:**
  - GET - [http://localhost:8080/swapi/api/films/4](http://localhost:8080/swapi/api/films/4)

- **Obter Filmes de uma Saga Específica:**
  - GET - [http://localhost:8080/swapi/api/films/saga/Revenge%20of%20the%20Sith](http://localhost:8080/swapi/api/films/saga/Revenge%20of%20the%20Sith)




**Testando a API**
Você pode testar os endpoints da API usando ferramentas de cliente HTTP,
como Postman ou Insomnia. Certifique-se de fornecer os parâmetros e payloads corretos para cada solicitação.


Algumas consultas SQL adicionais que você pode usar para explorar os dados da tabela "film":

```sql
Selecionar todos os filmes:
  SELECT * FROM film;

Selecionar filmes por título:
  SELECT * FROM film WHERE title = 'Nome do Filme';

Selecionar filmes lançados após uma determinada data:
  SELECT * FROM film WHERE release_date > '1999-01-01';

Selecionar filmes com uma descrição específica:
  SELECT * FROM film WHERE opening_crawl LIKE '%palavra_chave%';

Selecionar filmes por diretor:
  SELECT * FROM film WHERE director = 'Nome do Diretor';

Contar o número total de filmes:
  SELECT COUNT(*) FROM film;

Ordenar os filmes por data de lançamento:
  SELECT * FROM film ORDER BY release_date DESC;

Selecionar filmes lançados em um determinado ano:
  SELECT * FROM film WHERE YEAR(release_date) = 2005;
