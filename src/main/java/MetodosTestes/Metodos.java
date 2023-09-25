package MetodosTestes;

import AtributosJson.AtributosJsonEmprestimo;
import AtributosJson.AtributosJsonLogin;
import AtributosJson.AtributosJsonRegistro;
import Utils.Hooks;
import database.ConexaoBancoDeDados;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Metodos {
    private static List<DadosParaCriarRegistro> valorColunasRegistros = new ArrayList<>();
    private static List<DadosParaCriarLogin> valorColunasLogin = new ArrayList<>();
    private static List<DadosParaCriarEmprestimo> valorColunasEmprestimo = new ArrayList<>();
    private static HashMap<String, String> token = new HashMap<>();

    public static Response retornaModelo() {
        Response modelo =
                given()
                        .when()
                        .get(Hooks.SETUP.getUrlModelo())
                        .then()
                        .extract().response();

        return modelo;
    }

    public static Response retornaFabrica() {
        Response fabrica =
                given()
                        .when()
                        .get(Hooks.SETUP.getUrlFabrica())
                        .then()
                        .extract().response();

        return fabrica;
    }

    public static Response geraRegistro() {
        Response registro =
                given()
                        .contentType(ContentType.JSON)
                        .body(geraCorpoRegistro())
                        .when()
                        .post(Hooks.SETUP.getUrlRegistro())
                        .then()
                        .extract().response();

        return registro;
    }

    public static Response realizaLogin() {
        Response login =
                given()
                        .contentType(ContentType.JSON)
                        .body(geraCorpoLogin())
                        .when()
                        .post(Hooks.SETUP.getUrlLogin())
                        .then()
                        .extract().response();

        return login;
    }

    public static Response recebeTokenERealizaPostEmprestimo() {
        Response tokenCriado = realizaLogin();

        token.put("Authorization", "Bearer " + tokenCriado.jsonPath().getString("access_token"));

        Response postEmprestimo =
                given()
                        .headers(token)
                        .body(geraCorpoEmprestimo())
                        .contentType(ContentType.JSON)
                        .when()
                        .post(Hooks.SETUP.getUrlEmprestimo())
                        .then()
                        .extract().response();

        return postEmprestimo;
    }

    public static Response recebeEmprestimoCriadoERealizaGetEmprestimo() {
        Response emprestimoCriado = recebeTokenERealizaPostEmprestimo();

        Response getEmprestimo =
                given()
                        .headers(token)
                        .when()
                        .get(Hooks.SETUP.getUrlEmprestimo())
                        .then()
                        .extract().response();

        return getEmprestimo;
    }


    public static String geraCorpoRegistro() {
        JSONObject registro = new JSONObject();
        valorColunasRegistros = ConexaoBancoDeDados.pegarDadosDeRegistroDoBanco();

        for (int i = 0; i < valorColunasRegistros.size(); i++) {
            registro.put(AtributosJsonRegistro.firstName, valorColunasRegistros.get(i).getFirstName());
            registro.put(AtributosJsonRegistro.lastName, valorColunasRegistros.get(i).getLastName());
            registro.put(AtributosJsonRegistro.userName, valorColunasRegistros.get(i).getUserName());
            registro.put(AtributosJsonRegistro.passWord, valorColunasRegistros.get(i).getPassword());

            if (valorColunasRegistros.get(i).getUserName().equals("Pahmorais")) {
                registro.toString();
                break;
            }
        }

        return registro.toString();
    }

    public static String geraCorpoLogin(){
        JSONObject login = new JSONObject();
        valorColunasLogin = ConexaoBancoDeDados.pegarDadosDeLoginDoBanco();

        for (int i = 0; i < valorColunasLogin.size(); i++){
            login.put(AtributosJsonLogin.userName, valorColunasLogin.get(i).getUserName());
            login.put(AtributosJsonLogin.passWord, valorColunasLogin.get(i).getPassword());

            if (valorColunasLogin.get(i).getUserName().equals("Pahmorais")) {
                login.toString();
                break;
            }
        }

        return login.toString();
    }

    public static String geraCorpoEmprestimo(){
        JSONObject emprestimo = new JSONObject();
        valorColunasEmprestimo = ConexaoBancoDeDados.pegarDadosDeEmprestimoDoBanco();

        for (int i = 0; i < valorColunasEmprestimo.size(); i++){
            emprestimo.put(AtributosJsonEmprestimo.firstName, valorColunasEmprestimo.get(i).getFirstName());
            emprestimo.put(AtributosJsonEmprestimo.lastName, valorColunasEmprestimo.get(i).getLastName());
            emprestimo.put(AtributosJsonEmprestimo.address1, valorColunasEmprestimo.get(i).getAddress1());
            emprestimo.put(AtributosJsonEmprestimo.city, valorColunasEmprestimo.get(i).getCity());
            emprestimo.put(AtributosJsonEmprestimo.state, valorColunasEmprestimo.get(i).getState());
            emprestimo.put(AtributosJsonEmprestimo.zip, valorColunasEmprestimo.get(i).getZip());
            emprestimo.put(AtributosJsonEmprestimo.country, valorColunasEmprestimo.get(i).getCountry());
            emprestimo.put(AtributosJsonEmprestimo.dob, valorColunasEmprestimo.get(i).getDob());
            emprestimo.put(AtributosJsonEmprestimo.ssn, valorColunasEmprestimo.get(i).getSsn());
            emprestimo.put(AtributosJsonEmprestimo.employer, valorColunasEmprestimo.get(i).getEmployer());
            emprestimo.put(AtributosJsonEmprestimo.phoneNumber, valorColunasEmprestimo.get(i).getPhoneNumber());
            emprestimo.put(AtributosJsonEmprestimo.durationOfJob, valorColunasEmprestimo.get(i).getDurationOfJob());
            emprestimo.put(AtributosJsonEmprestimo.income, valorColunasEmprestimo.get(i).getIncome());
            emprestimo.put(AtributosJsonEmprestimo.loanTerm, valorColunasEmprestimo.get(i).getLoanTerm());
            emprestimo.put(AtributosJsonEmprestimo.loanAmount, valorColunasEmprestimo.get(i).getLoanAmount());
            emprestimo.put(AtributosJsonEmprestimo.validateAddress, valorColunasEmprestimo.get(i).getValidateAddress());

            if (valorColunasEmprestimo.get(i).getFirstName().equals("Paula")||valorColunasEmprestimo.get(i).
                    getAddress1().equals("Av. Teste 321")) {
                emprestimo.toString();
                break;
            }
        }

        return emprestimo.toString();
    }
}
