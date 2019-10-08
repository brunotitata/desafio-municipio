# municipio
**Instalação**
<br />
Para executar o projeto, entre na pasta e execute os seguintes comandos:
```
$ mvn clean install
$ docker build -t senior:latest .
$ docker run -p 8080:8080 senior:latest
```
**API's**
<br />
Iten 1
<br />
`POST http://localhost:8080/api/upload`
<br />
Iten 2
<br />
`GET http://localhost:8080/api/cidades/capitais"`
<br />
Iten 3
<br />
`GET http://localhost:8080/api/estado/maior-e-menor`
<br />
Iten 4
<br />
`GET http://localhost:8080/api/quantidade-estado`
<br />
Iten 5
<br />
`GET http://localhost:8080/api/info/cidade?identificador={codigoIbge}`
<br />
Iten 6
<br />
`GET http://localhost:8080/api/cidades/estado?uf={UF}`
<br />
Iten 7 
<br />
`POST http://localhost:8080/api/cidade`
<br />
Iten 8
<br />
`DELETE http://localhost:8080/api/excluir/cidade/{municipioUUID}"`
<br />
Iten 9 - **COLUNAS**: codigo_ibge, codigo_uf, latitude, longitude, nome_municipio, capital
<br />
`GET http://localhost:8080/api/filtrar?coluna=nome_municipio&filtrar=Itu`
<br />
Iten 11
<br />
`GET http://localhost:8080/api/total-registros`
<br />
Iten 12
<br />
`GET http://localhost:8080/api/distancia`
