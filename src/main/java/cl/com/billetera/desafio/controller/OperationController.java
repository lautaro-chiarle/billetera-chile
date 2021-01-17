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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/operation")
public class OperationController {
    
    @Autowired
    OperationService operationService;

    @PostMapping("")
    public Mono<Long> calc(@RequestBody OperationRequest op) {
        return operationService.calc(op);
    }

    @GetMapping("/{pageNumber}")
    public Flux<OperationLog> getAll(@PathVariable Integer pageNumber) {
        return operationService.getAll(pageNumber);
    }

}
