package spring.task.consumer.controlers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import spring.task.consumer.dtos.DepartmentDTO;

import spring.task.consumer.dtos.EmployeeDto;
import spring.task.consumer.dtos.SearchDto;

import java.util.Collections;

@RestController
@RequestMapping("/consumer/department")
public class DepartmentController {

    private final RestTemplate restTemplate;

    private String base_url = "http://localhost:8080/api/department";

    public DepartmentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<?> getAllDeps(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
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
    public ResponseEntity<?> create(@RequestBody DepartmentDTO dep) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<DepartmentDTO> entity = new HttpEntity<>(dep, httpHeaders);

        return restTemplate.exchange(base_url,
                HttpMethod.POST, entity,
                Object.class);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody DepartmentDTO dep, @RequestParam Long id) {

        var  URI_PROVIDER = UriComponentsBuilder.fromHttpUrl(base_url)
                .queryParam("id", id)
                .build()
                .toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<DepartmentDTO> entity = new HttpEntity<>(dep, httpHeaders);

        return restTemplate.exchange(URI_PROVIDER,
                HttpMethod.PUT, entity,
                Object.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(base_url + "/"+ id,
                HttpMethod.DELETE, entity,
                Object.class);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchDepartment
            (@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
             @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
             @RequestBody SearchDto searchDto){

        var  URI_PROVIDER = UriComponentsBuilder.fromHttpUrl(base_url)
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
