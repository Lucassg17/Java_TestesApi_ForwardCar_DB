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

    @Dado("que crio um registro")
    public void queCrioUmRegistro() throws Exception {
        Metodos.geraRegistro();
    }

    @E("envio os dados para realizar um metodo Post em Emprestimo")
    public void envioOsDadosParaRealizarUmMetodoPostEmEmprestimo() throws Exception {
        resultadoPostEmprestimo = Metodos.recebeTokenERealizaPostEmprestimo();
    }

    @Entao("confirmo que meu post foi feito com sucesso validando o retorno do meu body")
    public void confirmoQueMeuPostFoiFeitoComSucessoValidandoORetornoDoMeuBody() {
        resultadoPostEmprestimo.then()
                                    .log().all()
                                    .body(containsString("status: accepted"));
    }

    /////////////////@Teste002 @%ForwardCar/////////////////

    @E("envio um metodo Get para Emprestimo")
    public void envioUmMetodoGetParaEmprestimo() throws Exception {
        resultadoGetEmprestimo = Metodos.recebeEmprestimoCriadoERealizaGetEmprestimo();
    }

    @Entao("ao enviar a requisicao valido o retorno do meu body")
    public void aoEnviarARequisicaoValidoQueORetornoDoMeuBody() {
        resultadoGetEmprestimo.then().log().all();
        Assert.assertEquals(resultadoGetEmprestimo.jsonPath().getString(AtributosJsonEmprestimo.firstName), "[Paula]");
    }

    /////////////////@Teste003 @%ForwardCar/////////////////

    @Quando("crio um metodo Post em Login")
    public void crioUmMetodoPostEmLogin() throws Exception {
        resultadoPostLogin = Metodos.realizaLogin();
    }

    @Entao("valido que meu login foi criado com sucesso")
    public void validoQueMeuLoginFoiCriadoComSucesso() {
        resultadoPostLogin.then().log().all();
        Assert.assertEquals(resultadoPostLogin.jsonPath().getString(AtributosJsonLogin.userName), "lucasg");
    }
}
