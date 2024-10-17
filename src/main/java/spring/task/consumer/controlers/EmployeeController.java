package spring.task.consumer.controlers;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import spring.task.consumer.dtos.EmployeeDto;
import spring.task.consumer.dtos.SearchDto;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/consumer/employee")
public class EmployeeController {

    private final RestTemplate restTemplate;

    private String base_url = "http://localhost:8080/api/employee";

    public EmployeeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<?> getAllEmp(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                       @RequestParam(name = "pageSize", defaultValue = "10") int pageSize)
    {
        var  URI_PROVIDER = UriComponentsBuilder.fromHttpUrl(base_url)
                .queryParam("pageNum", pageNum)
                .queryParam("pageSize", pageSize)
                .build()
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(URI_PROVIDER,
                HttpMethod.GET, entity,
                Object.class);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody  EmployeeDto emp) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<EmployeeDto> entity = new HttpEntity<>(emp, httpHeaders);

        return restTemplate.exchange(base_url,
                HttpMethod.POST, entity,
                Object.class);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint to delete a Employee from the db")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(base_url + "/"+ id,
                HttpMethod.DELETE, entity,
                Object.class);
    }


    @PostMapping("/search")
    public ResponseEntity<?> searchEmployees
            (@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
             @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
             @RequestBody SearchDto searchDto){

        var  URI_PROVIDER = UriComponentsBuilder.fromHttpUrl(base_url+"/search")
                .queryParam("pageNum", pageNum)
                .queryParam("pageSize", pageSize)
                .build()
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<SearchDto> entity = new HttpEntity<>(searchDto, httpHeaders);

        return restTemplate.exchange(URI_PROVIDER,
                HttpMethod.POST, entity,
                Object.class);
    }
}
