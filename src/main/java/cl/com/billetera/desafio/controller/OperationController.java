package cl.com.billetera.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.com.billetera.desafio.dto.OperationRequest;
import cl.com.billetera.desafio.model.OperationLog;
import cl.com.billetera.desafio.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

@Tag(name = "Operaciones", description = "Recurso para el manejo de operaciones")
@RequestMapping(value = "/operation")
public class OperationController {
    
    @Autowired
    OperationService operationService;

    @Operation(method = "POST", description = "Endpoint para realizar sumas.")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "El requerimiento es incorrecto!"),
			@ApiResponse(responseCode = "401", description = "El usuario es incorrecto!"),
			@ApiResponse(responseCode = "403", description = "No tiene permisos para acceder al endpoint!") }) //
    @PostMapping("")
    public Mono<Long> calc(@RequestBody OperationRequest op) {
        return operationService.calc(op);
    }

    @Operation(method = "POST", description = "Endpoint para recuperar el historial de operaciones de manera paginada.")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "El requerimiento es incorrecto!"),
			@ApiResponse(responseCode = "401", description = "El usuario es incorrecto!"),
			@ApiResponse(responseCode = "403", description = "No tiene permisos para acceder al endpoint!") }) //
    @GetMapping("/{pageNumber}")
    public Flux<OperationLog> getAll(@PathVariable Integer pageNumber) {
        return operationService.getAll(pageNumber);
    }

}
