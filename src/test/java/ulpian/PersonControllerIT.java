package ulpian;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import ulpian.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@RequiredArgsConstructor
@IntegrationTest({"server.port=0"})
public class PersonControllerIT {

    @Value("${local.server.port}")
    private int port;

	private URL base;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/persons");
		template = new TestRestTemplate();
	}

	@Test
	public void getAPerson() throws Exception {
		ResponseEntity<Person> response = template.getForEntity(base.toString()+"/1", Person.class);
		assertThat(response.getBody().getName(), equalTo("Peter"));
	}
	
	@Test
	public void getAllPerson() throws Exception {
		ResponseEntity<ArrayList> response = template.getForEntity(base.toString(), ArrayList.class);
		assertThat(response.getBody().size(), equalTo(3));
	}
	
	@Test
	public void createAPerson() throws Exception {
		ResponseEntity<Person> response = template.getForEntity(base.toString()+"/4", Person.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
		
		template.put(base.toString(), new Person("Bilbo"));
		response = template.getForEntity(base.toString()+"/4", Person.class);
		assertThat(response.getBody().getName(), equalTo("Bilbo"));
	}
	
}