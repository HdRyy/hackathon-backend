package com.kernelpanic.hackathonbackend.services;

import com.kernelpanic.hackathonbackend.DTO.CepResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class CepService {

    private final RestTemplate restTemplate;
    private final String API_URL = "https://brasilapi.com.br/api/cep/v2/";


    public CepResponse getCepDetails(String cep) {
        String apiUrl = API_URL + cep;

        return restTemplate.getForObject(apiUrl, CepResponse.class);
    }
}