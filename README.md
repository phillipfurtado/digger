# digger

Para compilar

`./setup.sh`

Para executar o digger-api server 

`./start.sh`


# API

- Acesso API http://localhost:8080/digger/api

- Salvar Servidor

  POST /servidores
  
```json
  {
  "nome": "vagrant",
  "enderecoIP": "10.11.12.42",
  "user": "vagrant",
  "senha": "vagrant",
  "tipoOS": 2
 }
```

- Obter Servidor

  GET /servidores/{id}

- Listar Servidores

  GET /servidores
  
- Deletar Servidor

  DELETE /servidores/{id}
  
- Atualizar Servidor

  PUT /servidores/{id}

```json
  {
  "nome": "vagrant",
  "enderecoIP": "10.11.12.42",
  "user": "vagrant",
  "senha": "vagrant",
  "tipoOS": 2
 }
```

- Listar Aplicacoes de Servidor

  GET /servidores/{id}/aplicacoes
