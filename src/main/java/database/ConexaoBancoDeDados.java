package database;

import MetodosTestes.DadosParaCriarEmprestimo;
import MetodosTestes.DadosParaCriarLogin;
import MetodosTestes.DadosParaCriarRegistro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexaoBancoDeDados {
    private static String sqlQuery;
    private static Connection conection = null;
    private static Statement statement;
    private static PreparedStatement stm;
    private static ResultSet resultRegistro;
    private static ResultSet resultLogin;
    private static ResultSet resultEmprestimo;
    private static List<DadosParaCriarRegistro> dadosDeRegistro = new ArrayList<>();
    private static List<DadosParaCriarLogin> dadosDeLogin = new ArrayList<>();
    private static List<DadosParaCriarEmprestimo> dadosDeEmprestimo = new ArrayList<>();

    public static Connection criarConexao() {
        try {
            Class.forName("org.postgresql.Driver");
            conection = DriverManager.getConnection("jdbc:postgresql://lv-dev.betternow.com.br:8002/Treinamento",
                    "postgres", "postgres");

        }catch (ClassNotFoundException | SQLException exception){
            System.out.println("Erro ao acessar o Banco" + exception.getMessage());
        }
        return conection;
    }

    public static List<DadosParaCriarRegistro> pegarDadosDeRegistroDoBanco(String user){
        Connection connection = criarConexao();
        sqlQuery = "SELECT * FROM registrolucasepaula WHERE username = ?";

        try {
            //statement = connection.createStatement();
            stm = connection.prepareStatement(sqlQuery);
            stm.setString(1, user);

            resultRegistro = stm.executeQuery();

            while (resultRegistro.next()){
                DadosParaCriarRegistro dados = new DadosParaCriarRegistro();
                dados.setFirstName(resultRegistro.getString("firstname"));
                dados.setLastName(resultRegistro.getString("lastname"));
                dados.setUserName(resultRegistro.getString("username"));
                dados.setPassword(resultRegistro.getString("passwrd"));

                dadosDeRegistro.add(dados);
            }

            resultRegistro.close();
            stm.close();
            connection.close();

        }catch (SQLException e){
            System.out.println("Erro " + e.getMessage());
        }
        return dadosDeRegistro;
    }

    public static List<DadosParaCriarLogin> pegarDadosDeLoginDoBanco(String user){
        Connection connection = criarConexao();
        sqlQuery = "SELECT username, passwrd FROM registrolucasepaula WHERE username = ?";

        try {
           // statement = connection.createStatement();
            stm = connection.prepareStatement(sqlQuery);
            stm.setString(1, user);

            resultLogin = stm.executeQuery();

            while (resultLogin.next()){
                DadosParaCriarLogin dados = new DadosParaCriarLogin();
                dados.setUserName(resultLogin.getString("username"));
                dados.setPassword(resultLogin.getString("passwrd"));

                dadosDeLogin.add(dados);
            }

            resultLogin.close();
            stm.close();
            connection.close();

        }catch (SQLException e){
            System.out.println("Erro " + e.getMessage());
        }
        return dadosDeLogin;
    }

    public static List<DadosParaCriarEmprestimo> pegarDadosDeEmprestimoDoBanco(String address){
        Connection connection = criarConexao();
        sqlQuery = "SELECT * FROM emprestimoLucasEPaula WHERE address1 = ?";

        try {
            //statement = connection.createStatement();
            stm = connection.prepareStatement(sqlQuery);
            stm.setString(1, address);

            resultEmprestimo = stm.executeQuery();

            //resultEmprestimo = statement.executeQuery(sqlQuery);

            while (resultEmprestimo.next()){
                DadosParaCriarEmprestimo dados = new DadosParaCriarEmprestimo();
                dados.setFirstName(resultEmprestimo.getString("firstname"));
                dados.setLastName(resultEmprestimo.getString("lastname"));
                dados.setAddress1(resultEmprestimo.getString("address1"));
                dados.setCity(resultEmprestimo.getString("city"));
                dados.setState(resultEmprestimo.getString("state"));
                dados.setZip(resultEmprestimo.getString("zip"));
                dados.setCountry(resultEmprestimo.getString("country"));
                dados.setDob(resultEmprestimo.getString("dob"));
                dados.setSsn(resultEmprestimo.getString("ssn"));
                dados.setEmployer(resultEmprestimo.getString("employer"));
                dados.setPhoneNumber(resultEmprestimo.getString("phonenumber"));
                dados.setDurationOfJob(resultEmprestimo.getString("durationofjob"));
                dados.setIncome(resultEmprestimo.getString("income"));
                dados.setLoanTerm(resultEmprestimo.getString("loanterm"));
                dados.setLoanAmount(resultEmprestimo.getString("loanamount"));
                dados.setValidateAddress(resultEmprestimo.getString("validateaddress"));

                dadosDeEmprestimo.add(dados);
            }

            resultEmprestimo.close();
            stm.close();
            connection.close();

        }catch (SQLException e){
            System.out.println("Erro " + e.getMessage());
        }
        return dadosDeEmprestimo;
    }
}
