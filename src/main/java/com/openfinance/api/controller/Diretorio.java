package com.openfinance.api.controller;

import com.openfinance.api.response.DiretorioResponse;
import com.openfinance.api.service.DiretorioService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/diretorio")
@CrossOrigin(origins = "*")
@Api(value = "Recurso para extrair instituições do diretório", description = "Recurso para extrair instituições do diretório")
public class Diretorio {

    @Autowired
    DiretorioService diretorioService;

    @Operation(summary = "Recurso para buscar instituições participantes do Open Finance Brasil", description = "Retorna status da operação")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved"),
        @ApiResponse(code = 401, message = "Not authenticated"),
        @ApiResponse(code = 404, message = "Not found - The product was not found"),
    })
    @RequestMapping(value = "/processing", method = RequestMethod.GET)
    public ResponseEntity<DiretorioResponse> processarAPiDiretorio(
        @Schema(description = "URL do diretório", defaultValue = "https://data.directory.openbankingbrasil.org.br/participants", required = true)
        @RequestParam String url
    ){
        return diretorioService.processaDiretorio(url);
    }
}
