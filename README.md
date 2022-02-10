# connections
Curso de verão do IME - Introdução a Microsserviços com SpringBoot

Sobre a tarefa

Você deve finalizar as operações de CRUD no microsserviço de Conexões, seguindo a modelagem adotada até agora. Além disso, você deverá finalizar de implementar os comportamentos reativos de eventos.

# docker-compose de connections
docker-compose up -d

## criar database
docker exec -it  <ID> psql -c "CREATE DATABASE connections"

## permissoes para root
docker exec -it  <ID> psql -c "GRANT ALL PRIVILEGES ON DATABASE accounts TO root"

# subir apenas o database do serviço de contas (account)
docker-compose up pg 

## criar database
docker exec -it  <ID> psql -c "CREATE DATABASE accounts"

## permissoes para root
docker exec -it  <ID> psql -c "GRANT ALL PRIVILEGES ON DATABASE accounts TO root"

## executar o serviço de accounts e connections

## enviar post para criar conta
http POST :8081/accounts < account.json
Get-Content account.json | http POST :8081/accounts

## fazer amizade
http POST :8082/friendships/1/2
http POST :8082/friendships/1/3

## listar friends id
http GET :8082/accounts/1/friends

## count friends
http GET :8082/accounts/1/friends?count=true

## desfazer amizade
http DELETE :8082/friendships/1/3

## pgadmin
http://localhost:9000/

## kafdrop
http://localhost:9000/