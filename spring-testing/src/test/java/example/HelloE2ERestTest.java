package example;

import example.person.Person;
import example.person.PersonRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloE2ERestTest {

    @Autowired
    private PersonRepository personRepository;

    @LocalServerPort
    private int port;

    @After
    public void tearDown() throws Exception {
        personRepository.deleteAll();
    }

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        when()
                .get(String.format("http://localhost:%s/hello", port))
                .then()
                .statusCode(is(200))
                .body(containsString("Hello World!"));
    }

    @Test
    public void shouldReturnGreeting() throws Exception {
        Person name = new Person("Suraj", "Nayak");
        personRepository.save(name);

        when()
                .get(String.format("http://localhost:%s/hello/Nayak", port))
                .then()
                .statusCode(is(200))
                .body(containsString("Hello Suraj Nayak"));
    }
}
