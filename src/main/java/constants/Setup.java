package constants;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Setup {
    private String urlRegistro;
    private String urlFabrica;
    private String urlEmprestimo;
    private String urlLogin;
    private String urlModelo;
    private String caminho;
    private Properties prop;

    public String getCaminho() {
        return caminho;
    }

    public String getUrlEmprestimo() {
        return urlEmprestimo;
    }

    public String getUrlLogin() {
        return urlLogin;
    }

    public String getUrlFabrica() {
        return urlFabrica;
    }

    public String getUrlModelo() {
        return urlModelo;
    }

    public String getUrlRegistro() {
        return urlRegistro;
    }

    public Setup(){
        int i = 0;
        ArrayList<String> configs = new ArrayList<>(
                Arrays.asList(
                        "url-registro",
                        "url-login",
                        "url-modelo",
                        "url-fabrica",
                        "url-emprestimo",
                        "caminho"
                ));
        prop = new Properties();
        try{
            prop.load(Files.newInputStream(Paths.get("src/test/resources/config.properties")));
            urlRegistro = prop.getProperty(configs.get(i++), "http://localhost:3434/cars-app/register");
            urlLogin = prop.getProperty(configs.get(i++), "http://localhost:3434/cars-app/api/login");
            urlModelo = prop.getProperty(configs.get(i++), "http://localhost:3434/cars-app/carShop/models");
            urlFabrica = prop.getProperty(configs.get(i++), "http://localhost:3434/cars-app/carShop/makes");
            urlEmprestimo = prop.getProperty(configs.get(i++), "http://localhost:3434/cars-app/api/loanApp");
            caminho = prop.getProperty(configs.get(i++), "C:\\Users\\Lucas Gomes\\RestAssured\\MassaDados_ApiForwardCar");

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
