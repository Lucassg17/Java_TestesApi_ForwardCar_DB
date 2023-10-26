package steps;

import AtributosJson.AtributosJsonEmprestimo;
import AtributosJson.AtributosJsonLogin;
import MetodosTestes.Metodos;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.hamcrest.core.StringContains.containsString;

public class StepForwardCar {
    Response resultadoGetEmprestimo;
    Response resultadoPostLogin;
    Response resultadoPostEmprestimo;

    /////////////////@Teste001 @%ForwardCar/////////////////

    @Dado("que crio um registro {string}")
    public void queCrioUmRegistro(String user) {
        Metodos.geraRegistro(user);
    }

    @E("envio os dados para realizar um metodo Post em Emprestimo com os parametros {string}, {string}")
    public void envioOsDadosParaRealizarUmMetodoPostEmEmprestimoComOsParametros(String userName, String address) {
        resultadoPostEmprestimo = Metodos.recebeTokenERealizaPostEmprestimo(userName, address);
    }

    @Entao("confirmo que meu post foi feito com sucesso validando o retorno do meu body {string}")
    public void confirmoQueMeuPostFoiFeitoComSucessoValidandoORetornoDoMeuBody(String validacao) {
        resultadoPostEmprestimo.then()
                                    .log().all()
                                    .body(containsString(validacao));
    }

    /////////////////@Teste002 @%ForwardCar/////////////////

    @E("envio um metodo Get para Emprestimo com os parametros {string}, {string}")
    public void envioUmMetodoGetParaEmprestimoComOsParametros(String userName, String address) {
        resultadoGetEmprestimo = Metodos.recebeEmprestimoCriadoERealizaGetEmprestimo(userName, address);
    }

    @Entao("ao enviar a requisicao valido o retorno do meu body {string}")
    public void aoEnviarARequisicaoValidoQueORetornoDoMeuBody(String firsName) {
        resultadoGetEmprestimo.then().log().all();
        Assert.assertEquals(resultadoGetEmprestimo.jsonPath().getString(AtributosJsonEmprestimo.firstName), firsName);
    }

    /////////////////@Teste003 @%ForwardCar/////////////////

    @Quando("crio um metodo Post em Login com o user {string}")
    public void crioUmMetodoPostEmLoginComOUser(String userName) {
        resultadoPostLogin = Metodos.realizaLogin(userName);
    }

    @Entao("valido que meu login foi criado com sucesso {string}")
    public void validoQueMeuLoginFoiCriadoComSucesso(String userName) {
        resultadoPostLogin.then().log().all();
        Assert.assertEquals(resultadoPostLogin.jsonPath().getString(AtributosJsonLogin.userName), userName);
    }
}
