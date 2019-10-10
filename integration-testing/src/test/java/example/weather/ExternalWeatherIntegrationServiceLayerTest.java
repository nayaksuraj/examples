package example.weather;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import example.helper.FileLoader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(SpringRunner.class)
public class ExternalWeatherIntegrationServiceLayerTest {

    final RestTemplate restTemplate = new RestTemplate();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Mock
    private WeatherClient weatherClient;


    @Before
    public void setUp() {
        weatherClient = new WeatherClient(restTemplate, "http://localhost:8089", "some-test-api-key");
    }


    @Test
    public void shouldReturnYesterdaysWeatherServiceLayer() throws Exception {

       wireMockRule.stubFor(get(urlPathEqualTo("/some-test-api-key/18.5204,73.8567"))
                .willReturn(aResponse()
                        .withBody(FileLoader.read("classpath:weatherApiResponse.json"))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));


        Optional<WeatherResponse> actualResponse = weatherClient.fetchWeather();
        WeatherResponse expectedResponse = new WeatherResponse("Clear");

        assertThat(actualResponse, is(Optional.of(expectedResponse)));

    }

}
