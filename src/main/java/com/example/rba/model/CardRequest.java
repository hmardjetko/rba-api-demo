package com.example.rba.model;

import com.example.rba.model.enums.CardRequestStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class CardRequest {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CardRequestStatus cardRequestStatus;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime created;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updated;
    private String description;
    private String errorMessage;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_client", nullable = false)
    private Client client;

    public CardRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CardRequestStatus getCardRequestStatus() {
        return cardRequestStatus;
    }

    public void setCardRequestStatus(CardRequestStatus cardRequestStatus) {
        this.cardRequestStatus = cardRequestStatus;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardRequest that = (CardRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(client, that.client) && Objects.equals(cardRequestStatus, that.cardRequestStatus) && Objects.equals(created, that.created) && Objects.equals(updated, that.updated) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, cardRequestStatus, created, updated, description);
    }

    @Override
    public String toString() {
        return "CardRequest{" +
                "id=" + id +
                ", cardRequestStatus=" + cardRequestStatus +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                ", idClient=" + client.getId() +
                '}';
    }
}
