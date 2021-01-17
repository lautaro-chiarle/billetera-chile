package cl.com.billetera.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.com.billetera.desafio.dto.OperationRequest;
import cl.com.billetera.desafio.model.OperationLog;
import cl.com.billetera.desafio.repository.OperationLogRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Transactional
public class OperationService {


    @Autowired OperationLogRepository operationRepository;  
    	
	@Value("${operations.query.page-size}")
	private Integer pageSize; 

	public Mono<Long> calc(OperationRequest op) {
        Long result = op.getNumber1()+op.getNumber2();
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication userDetails = (Authentication) context.getAuthentication();
        String username = userDetails.getPrincipal().toString();
        OperationLog opLog = new OperationLog(op.getNumber1(), op.getNumber2(), result, username);
        
        return operationRepository.save(opLog).flatMap(o -> {
            return Mono.just(o.getResult());
        });
	}

	public Flux<OperationLog> getAll(Integer pageNumber) {
;
		return operationRepository.findAll(pageNumber, pageSize);
	}
    
}
