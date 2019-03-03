package whoami.dockerjavastarter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RestController
@Slf4j
public class DemoApplication {

	@Autowired
	private HttpServletRequest httpServletRequest;

	private static ConcurrentMap<String, Long> userRequestTimesMap = new ConcurrentHashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/greeting")
	public ResponseEntity<?> greeting() {

		String username = httpServletRequest.getRemoteAddr();
		log.info("request user:{}, remote ip: {}", httpServletRequest.getRemoteUser(), username);

		long requestTimes = 1L;
		if (userRequestTimesMap.containsKey(username)) {
			requestTimes = userRequestTimesMap.get(username) + 1;
		}

		userRequestTimesMap.put(username, requestTimes);

		return new ResponseEntity<>(String.format("hi %s, welcome to docker! this is your %d st visited.", username, requestTimes), HttpStatus.OK);
	}

	@GetMapping("/users/requests")
	public ResponseEntity<?> getUserRequests() {
		return new ResponseEntity<>(userRequestTimesMap, HttpStatus.OK);
	}

	@GetMapping("/users/request/{username}")
	public ResponseEntity<?> getUserRequestsByName(@PathVariable("username") String username) {
		return new ResponseEntity<>(userRequestTimesMap.get(username), HttpStatus.OK);
	}

}
