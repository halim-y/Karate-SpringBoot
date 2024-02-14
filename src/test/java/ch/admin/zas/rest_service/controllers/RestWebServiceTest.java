package ch.admin.zas.rest_service.controllers;

import org.junit.jupiter.api.*; //@Test
import static org.junit.jupiter.api.Assertions.*; //asserEquals

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// TEST UNITAIRE DANS CONTROLLER --> CASQUETTE DEVELOPER

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestWebServiceTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void helloTest(){
        // préparation
        String url ="/api/hello";

        // mise en pratique
        ResponseEntity<String> response = template.getForEntity(url, String.class);

        // vérification
        // on check d'abord si on a pas eu d'erreur 40x
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // si pas d'erreur → on check la réponse
        String str = response.getBody();
        assertEquals("Hello World!", str, "expected name to be world");
    }

    @Test
    public void helloTest2(){
        // préparation
        String url = "/api/hello?name=Halim";
        // mise en pratique
        ResponseEntity<String> response = template.getForEntity(url, String.class);
        // vérification
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String str = response.getBody();
        assertEquals("Hello Halim!", str, "expected name to be Halim");
    }

    @Test
    public void helloAdv(){
        String url = "/api/hello/advanced";
        ResponseEntity<String> response = template.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String str = response.getBody();
        assertEquals("Bonjour :)!", str, "expected language: FR");
    }

    @Test
    public void helloAdv2(){
        String url = "/api/hello/advanced?lang=en";
        ResponseEntity<String> response = template.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String str = response.getBody();
        assertEquals("Hello :)!", str, "expected language: EN");
    }

    @Test
    public void helloAdv3(){
        String url = "/api/hello/advanced?name=Marc";
        ResponseEntity<String> response = template.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String str = response.getBody();
        assertEquals("Bonjour Marc!", str, "expected name: Marc, expected language: FR");
    }

    @Test
    public void helloAdv4(){
        String url = "/api/hello/advanced?name=Marc&lang=en";
        ResponseEntity<String> response = template.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String str = response.getBody();
        assertEquals("Hello Marc!", str, "expected name: Marc, expected language: EN");
    }

    @Test
    public void helloAdv5(){
        String url = "/api/hello/advanced?name=Marc&lang=it";
        ResponseEntity<String> response = template.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String str = response.getBody();
        assertEquals("Error, this language doesn't exist !", str, "expected error");
    }
}
