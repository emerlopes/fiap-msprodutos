# language: pt

Funcionalidade: Produto
  Eu quero cadastrar produtos na minha loja
  Para que eu possa gerenciar os produtos da minha loja

  Cenario: Cadastrar produto via lote
    Quando eu enviar uma requisicao POST para '/produtos/jobs'
    Entao a resposta deve ter o codigo de status 200
