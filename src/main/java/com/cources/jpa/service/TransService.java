package com.cources.jpa.service;

import com.cources.jpa.domain.Trans;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TransService {
    String URI = "http://localhost:8081/api/v1/transaction";

    public String getMessage () {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URI + "/test", String.class);
    }

    public ResponseEntity<Trans> save (Trans trans) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(URI, trans, Trans.class);
    }

    public List getAll () {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<List<Trans>> entity = new HttpEntity<>(headers);
        return new RestTemplate().exchange(URI + "/test", HttpMethod.GET, entity, List.class).getBody();
    }

    public Trans saveEach (Trans trans) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Trans> entity = new HttpEntity<>(trans, headers);
        return new RestTemplate().exchange(URI, HttpMethod.POST, entity, Trans.class).getBody();
    }

    public Trans update (Trans trans) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Trans> entity = new HttpEntity<>(trans, headers);
        return new RestTemplate().exchange(URI, HttpMethod.PUT, entity, Trans.class).getBody();
    }
}