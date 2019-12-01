# Crime-Analysis

Sistema de classificação do usuário em situação segura, pouco segura ou insegura, em relação a sofrer um furto, com base no histórico de BOs registrados no estado de São Paulo. 
Os datasets utilizados para a análise estão disponíveis em: https://www.kaggle.com/inquisitivecrow/crime-data-in-brazil/
Além disso, é preciso configurar um banco de dados Cassandra e um servidor Kafka para executar o projeto.

## Para executar

    sbt
    eclipse
    run

O acesso fica disponível em localhost:9000 em seu navegador, e as rotas disponíveis são `/loadData` e `/classifier/*cor/*turno/*sexo`.
