# Projeto Certification NWL

## Descrição

Projeto feito no evento NLW Expert da Rocketseat.
No final do evento fiz algumas modificações no projeto, sinta-se a vontade para dar uma olhadinha.

## Endpoints:

## Questions

### POST - /questions

- Rota para criar uma questão.
- Request Body:
  - **description**: string.
  - **technology**: string, máximo de 50 caracteres.
  - **alternatives**: [
    {
    - **description**: string.
    - **isCorrect**: boolean.
      }
      ]

```
{
    "description": "Pergunta ",
    "technology": "java",
    "alternatives": [
        {
            "description": "resposta verdadeira",
            "isCorrect": true
        },
        {
            "description": "resposta falsa 1",
            "isCorrect": false
        },
        {
            "description": "resposta falsa 2",
            "isCorrect": false
        },
        {
            "description": "resposta falsa 3",
            "isCorrect": false
        }

    ]
}
```

- Return success [201 CREATED]

```
{
    "id": "a5ea6c64-4625-45fb-bf65-5e3b8e5082d6",
    "description": "teestando",
    "technology": "JAVA",
    "alternatives": [
        {
            "id": "5a091889-7155-4878-a066-720b49ed8d27",
            "description": "test",
            "isCorrect": true,
            "createAt": "2024-02-13T11:30:00.548621"
        },
        {
            "id": "176a86e5-2ff3-44f6-9330-baa8ce7b7adb",
            "description": "test1",
            "isCorrect": false,
            "createAt": "2024-02-13T11:30:00.551622"
        },
        {
            "id": "0d760ec4-9322-4fab-a46f-7dd75badf356",
            "description": "test2",
            "isCorrect": false,
            "createAt": "2024-02-13T11:30:00.552625"
        },
        {
            "id": "9e51cc03-c595-42a5-b8c5-c184b73614de",
            "description": "test3",
            "isCorrect": false,
            "createAt": "2024-02-13T11:30:00.553621"
        }
    ],
    "createAt": "2024-02-13T11:30:00.530617"
}
```

### GET - /questions/{technology}

- Rota para buscar questões de uma tecnologia.

- Return success [200 OK]

```
[
    {
      "id": "a5ea6c64-4625-45fb-bf65-5e3b8e5082d6",
      "description": "teestando",
      "technology": "JAVA",
      "alternatives": [
          {
              "id": "5a091889-7155-4878-a066-720b49ed8d27",
              "description": "test",
              "isCorrect": true,
              "createAt": "2024-02-13T11:30:00.548621"
          },
          {
              "id": "176a86e5-2ff3-44f6-9330-baa8ce7b7adb",
              "description": "test1",
              "isCorrect": false,
              "createAt": "2024-02-13T11:30:00.551622"
          },
          {
              "id": "0d760ec4-9322-4fab-a46f-7dd75badf356",
              "description": "test2",
              "isCorrect": false,
              "createAt": "2024-02-13T11:30:00.552625"
          },
          {
              "id": "9e51cc03-c595-42a5-b8c5-c184b73614de",
              "description": "test3",
              "isCorrect": false,
              "createAt": "2024-02-13T11:30:00.553621"
          }
      ],
      "createAt": "2024-02-13T11:30:00.530617"
    }
]

```

- Return Not Found [404 NOT_FOUND]

```
{
    "timeStamp": "2024-02-13T14:45:33.278+00:00",
    "message": "Não existe questões para a tecnologia TEST",
    "details": "uri=/questions/test"
}
```

### PUT - /questions

- Rota para atualizar uma questão.
- Request Body:
  - **id**: UUID.
  - **description**: string.
  - **technology**: string, máximo de 50 caracteres.

```
{
    "id": "685be46c-9dcc-4cc4-b691-4848b05e4436",
    "description": "Editando pergunta",
    "technology": "java"
}
```

- Return success [200 OK]

```
{
    "id": "685be46c-9dcc-4cc4-b691-4848b05e4436",
    "description": "Editando pergunta",
    "technology": "JAVA",
    "alternatives": [
        {
            "id": "5a091889-7155-4878-a066-720b49ed8d27",
            "description": "test",
            "isCorrect": true,
        },
        {
            "id": "176a86e5-2ff3-44f6-9330-baa8ce7b7adb",
            "description": "test1",
            "isCorrect": false,
        },
        {
            "id": "0d760ec4-9322-4fab-a46f-7dd75badf356",
            "description": "test2",
            "isCorrect": false,
        },
        {
            "id": "9e51cc03-c595-42a5-b8c5-c184b73614de",
            "description": "test3",
            "isCorrect": false,
        }
    ]
}
```

### DELETE - /questions/{id}

- Rota para apagar uma questão especifica.
- Return success [204 NO_CONTENT]

- Return Not Found [404 NOT_FOUND]

```
{
    "timeStamp": "2024-02-13T14:47:13.153+00:00",
    "message": "Questão com o id f1efbda7-0b61-4dcc-8ee7-c2afe10cb620 não encontrada.",
    "details": "uri=/questions/f1efbda7-0b61-4dcc-8ee7-c2afe10cb620"
}
```

### POST - /questions/alternatives

- Rota para criar uma alternativa.
- Ao passar o campo "isCorrect" como true para a nova alternativa, automaticamente ira transformar em false a antiga alternativa verdadeira.
- Request Body:
  - **questionId**: UUID.
  - **description**: string.
  - **isCorrect**: boolean.

```
{
  "questionId": "685be46c-9dcc-4cc4-b691-4848b05e4436",
  "description": "Criando uma nova alternativa",
  "isCorrect": false
}
```

- Return success [201 CREATED]

```
{
  "id": "6e070f83-edcd-4be1-8344-b15a2843390e",
  "description": "Criando uma nova alternativa",
  "isCorrect": false,
  "createAt": "2024-02-14T10:01:31.725848"
}
```

- Return Bad Request [404 BAD_REQUEST]

```
{
  "timeStamp": "2024-02-13T14:50:09.310+00:00",
  "message": "Questão pode possuir no máximo 4 alternativas",
  "details": "uri=/questions/alternatives"
}
```

### PUT - /questions/alternatives

- Rota para atualizar uma alternativa.
- Impossível atualizar o campo "isCorrect" de true para false, apenas mude outra alternativa para true, que a atualização da antiga alternativa verdadeira irá acontecer automaticamente para false.
- Request Body:
  - **id**: UUID.
  - **questionId**: UUID.
  - **description**: string.
  - **isCorrect**: boolean.

```
{
  "questionId": "685be46c-9dcc-4cc4-b691-4848b05e4436",
  "id": "6e070f83-edcd-4be1-8344-b15a2843390e",
  "description": "Atualizando uma nova alternativa",
  "isCorrect": true
}
```

- Return success [200 OK]

```
{
  "id": "6e070f83-edcd-4be1-8344-b15a2843390e",
  "description": "Atualizando uma nova alternativa",
  "isCorrect": true,
  "createAt": "2024-02-14T10:01:31.725848"
}
```

- Return Bad Request [404 NOT_FOUND]

```
{
  "timeStamp": "2024-02-14T13:08:44.146+00:00",
  "message": "Alternativa com id 6e070f83-edcd-4be1-8344-b15a3843390e não encontrada.",
  "details": "uri=/questions/alternatives"
}
```

- Return Bad Request [404 NOT_FOUND]

```
{
  "timeStamp": "2024-02-14T13:11:15.433+00:00",
  "message": "Questão com o id 685be46c-9dcc-4cc4-b791-4849b05e4436 não encontrada.",
  "details": "uri=/questions/alternatives"
}
```

- Return Bad Request [400 BAD_REQUEST]

```
{
  "timeStamp": "2024-02-14T13:04:43.931+00:00",
  "message": "Impossível atualizar o campo 'isCorrect' para false, primeiro atualize o campo 'isCorrect' para true de outra alternativa.",
  "details": "uri=/questions/alternatives"
}
```

### DELETE - /questions/alternatives/{id}

- Rota para apagar uma questão especifica.
- Return success [204 NO_CONTENT].
- Return Bad Request [404 NOT_FOUND]

```
{
  "timeStamp": "2024-02-14T13:08:44.146+00:00",
  "message": "Alternativa com id 6e070f83-edcd-4be1-8344-b15a3843390e não encontrada.",
  "details": "uri=/questions/alternatives"
}
```
