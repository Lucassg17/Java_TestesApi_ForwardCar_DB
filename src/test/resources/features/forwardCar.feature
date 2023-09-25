#language: pt

Funcionalidade: Testando metodos HTTP da API ForwardCar
  Contexto:
  * que crio um registro

  @Teste001 @%ForwardCar
  Cenario: Realizando um Post em Emprestimo
    E envio os dados para realizar um metodo Post em Emprestimo
    Entao confirmo que meu post foi feito com sucesso validando o retorno do meu body

  @Teste002 @%ForwardCar
  Cenario: Realizando um Get em Emprestimos
    E envio um metodo Get para Emprestimo
    Entao ao enviar a requisicao valido o retorno do meu body

  @Teste003 @%ForwardCar
  Cenario: Realizando um Post em Login
    Quando crio um metodo Post em Login
    Entao valido que meu login foi criado com sucesso



