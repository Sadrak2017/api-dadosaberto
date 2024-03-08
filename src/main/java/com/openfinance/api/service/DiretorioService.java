package com.openfinance.api.service;

import com.openfinance.api.dto.InvoiceFinanceData;
import com.openfinance.api.enums.DiretorioStatusType;
import com.openfinance.api.response.DiretorioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DiretorioService {

  @Autowired
  ApiService apiService;

  public ResponseEntity<DiretorioResponse> processaDiretorio(String url) {
    InvoiceFinanceData invoiceFinanceData = apiService.accessDirectoryAndReturnFilteredJson(url);
    return ResponseEntity.status(HttpStatus.OK)
        .body(new DiretorioResponse<InvoiceFinanceData>().info(invoiceFinanceData, DiretorioStatusType.DADOS_RECUPERADO));
  }
}

