package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUBLISHER_ID", nullable = false)
    private Long publisherId;

    @Column
    private String publisherName;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "city", column = @Column(name = "PUBLISHER_ADDRESS")),
                        @AttributeOverride(name = "state", column = @Column(name = "PUBLISHER_STATE")),
                        @AttributeOverride(name = "zipcode", column = @Column(name = "PUBLISHER_ZIPCODE"))})
    private Address publisherAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(publisherId, publisher.publisherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisherId);
    }
}
