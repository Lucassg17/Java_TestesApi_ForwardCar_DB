package steps;

import AtributosJson.AtributosJsonFabrica;
import AtributosJson.AtributosJsonModelo;
import MetodosTestes.Metodos;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.hamcrest.core.StringContains.containsString;

public class StepForwardCar2 {
    Response resultadoGetFabrica;
    Response resultadoGetModelo;

    /////////////////@Teste004 @%ForwardCar2/////////////////

    @Dado("que realizo um metodo Get em Fabrica")
    public void queRealizoUmMetodoGetEmFabrica() {
        resultadoGetFabrica = Metodos.retornaFabrica();
    }

    @Entao("valido o tamanho da lista de fabricas que retorna no body do meu response")
    public void validoOTamanhoDaListaDeFabricasQueRetornaNoBodyDoMeuResponse() {
        resultadoGetFabrica.then()
                                .log().all()
                                .body(containsString("Land Rover"));
        Assert.assertEquals(resultadoGetFabrica.jsonPath().getInt(AtributosJsonFabrica.size), 9);
    }

    /////////////////@Teste005 @%ForwardCar2/////////////////

    @Dado("que realizo um metodo Get em Modelo")
    public void queRealizoUmMetodoGetEmModelo() {
        resultadoGetModelo = Metodos.retornaModelo();
    }

    @Entao("apos minha requisicao ser enviada valido o body do meu response")
    public void aposMinhaRequisicaoSerEnviadaValidoOBodyDoMeuResponse() {
        resultadoGetModelo.then()
                                .log().all()
                                .body(containsString("RLX-B"))
                                .body(containsString("RLX-AWD"));
    }
}
