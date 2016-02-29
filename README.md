# digger

Antes de executar o setup tenha certeza que tem o Docker instalado

`https://docs.docker.com/engine/installation/` 

Para compilar

`./setup.sh`

Para executar o digger-api server 

`./start-api.sh`

Para executar o digger-web server

`./start-web.sh`

# WEB

Via Browser `http://localhost:9000`

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
```json
  [
    {
    "nome": "account-plugin-google"
  },
  {
    "nome": "account-plugin-jabber"
  },
  {
    "nome": "account-plugin-salut"
  },
  {
    "nome": "account-plugin-yahoo"
  },
  {
    "nome": "accountsservice"
  },
  {
    "nome": "acl"
  },
  {
    "nome": "acpi-support"
  },
  {
    "nome": "acpid"
  }]
```
