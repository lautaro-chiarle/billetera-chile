package cl.com.billetera.desafio.model;

import org.springframework.data.annotation.Id;

import org.springframework.data.relational.core.mapping.Table;

@Table("_user")
public class User {

    @Id
    private Long id;
    private String name;
    private String password;
    private String email;

// set and get… 

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User:{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }

}