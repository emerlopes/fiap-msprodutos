package bdd.steps.cadastrarcliente;

import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.MediaType;

import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class CadastrarProdutoStepDefinition {

    private Response response;
    private static final String HOST = "http://localhost:8081/";
    private Map<String, String> dataTable;


    @Quando("eu enviar uma requisicao POST para {string}")
    public void eu_enviar_uma_requisicao_post_para(String rota) {
        final var requestSpec = getRequestSpec(rota, null, null);
        response = requestSpec.post();
    }

    @Entao("a resposta deve ter o codigo de status {int}")
    public void a_resposta_deve_ter_o_codigo_de_status(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    private RequestSpecification getRequestSpec(String rota, Object request, Map<String, String> queryParams) {

        String correlationId = UUID.randomUUID().toString();
        String flowId = UUID.randomUUID().toString();

        if (rota.startsWith("/")) {
            rota = rota.substring(1);
        }

        final var baseUri = HOST + rota;

        RequestSpecification requestSpec = given()
                .header("correlation-id", correlationId)
                .header("flow-id", flowId)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .contentType(ContentType.JSON)
                .baseUri(baseUri);

        if (request != null) {
            requestSpec.body(request);
        }

        if (queryParams != null && !queryParams.isEmpty()) {
            requestSpec.queryParams(queryParams);
        }

        return requestSpec;
    }

}
