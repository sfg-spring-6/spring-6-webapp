package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class FauxProdServiceImpl implements FauxService {

    @Value("${faux.datasource}")
    private String datasource;

    @Override
    public String getDatasource() {
        return datasource;
    }
}
