package cl.com.billetera.desafio.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class OperationLog {

    @Id
    private Long id;
    private Long number1;
    private Long number2;
    private Long result;
    private String username;
    private LocalDateTime datetime;

    public OperationLog(Long number1, Long number2, Long result, String username) {
        this.number1 = number1;
        this.number2 = number2;
        this.result = result;
        this.username = username;
        this.datetime = LocalDateTime.now();
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber1() {
        return this.number1;
    }

    public void setNumber1(Long number1) {
        this.number1 = number1;
    }

    public Long getNumber2() {
        return this.number2;
    }

    public void setNumber2(Long number2) {
        this.number2 = number2;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDatetime() {
        return this.datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }


    public Long getResult() {
        return this.result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", datetime='" + getDatetime() + "'" +
            " number1='" + getNumber1() + "'" +
            ", number2='" + getNumber2() + "'" +
            ", result='" + getResult() + "'" +
 
            "}";
    }

    
}
