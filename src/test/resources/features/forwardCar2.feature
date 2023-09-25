#language: pt

Funcionalidade: Testando metodos HTTP da API ForwardCar

  @Teste004 @%ForwardCar2
  Cenario: Realizando um Get em Fabrica
    Dado que realizo um metodo Get em Fabrica
    Entao valido o tamanho da lista de fabricas que retorna no body do meu response

  @Teste005 @%ForwardCar2
  Cenario: Realizando um Get em Modelo
    Dado que realizo um metodo Get em Modelo
    Entao apos minha requisicao ser enviada valido o body do meu response