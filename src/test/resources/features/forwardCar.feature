#language: pt

Funcionalidade: Testando metodos HTTP da API ForwardCar

  @Teste001 @%ForwardCar
  Cenario: Realizando um Post em Emprestimo
    Dado que crio um registro "lucasg"
    E envio os dados para realizar um metodo Post em Emprestimo com os parametros "lucasg", "Av. Teste 123"
    Entao confirmo que meu post foi feito com sucesso validando o retorno do meu body "status: accepted"

  @Teste002 @%ForwardCar
  Cenario: Realizando um Get em Emprestimos
    Dado que crio um registro "lucassg"
    E envio um metodo Get para Emprestimo com os parametros "lucassg", "Av. Teste 123"
    Entao ao enviar a requisicao valido o retorno do meu body "[lucas]"

  @Teste003 @%ForwardCar
  Cenario: Realizando um Post em Login
    Dado que crio um registro "Pahmorais"
    Quando crio um metodo Post em Login com o user "Pahmorais"
    Entao valido que meu login foi criado com sucesso "Pahmorais"



