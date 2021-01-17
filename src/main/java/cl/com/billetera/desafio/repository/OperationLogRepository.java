package cl.com.billetera.desafio.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import cl.com.billetera.desafio.model.OperationLog;
import reactor.core.publisher.Flux;


@Repository
public interface OperationLogRepository extends ReactiveCrudRepository<OperationLog, Long> {
    
    @Query("SELECT * FROM operation_log OFFSET :offset LIMIT :limit")
    Flux<OperationLog> findAll(Integer offset, Integer limit);
}




