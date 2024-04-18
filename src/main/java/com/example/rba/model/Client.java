package com.example.rba.model;

import com.example.rba.model.enums.ClientStatus;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true, length = 11)
    private String taxNumber;
    private String firstName;
    private String surname;
    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus;

    public Client() {
    }

    public Long getId() {
        return this.id;
    }

    public String getTaxNumber() {
        return this.taxNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public ClientStatus getClientStatus() {
        return this.clientStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(taxNumber, client.taxNumber) && Objects.equals(firstName, client.firstName) && Objects.equals(surname, client.surname) && Objects.equals(clientStatus, client.clientStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.taxNumber, this.firstName, this.surname);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", taxNumber='" + taxNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", clientStatus=" + clientStatus +
                '}';
    }

}