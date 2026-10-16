package com.example.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "full_name")
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String phone;

    private String address;

    @PrePersist
    @PreUpdate
    public void syncNames() {
        if (this.name == null && this.fullName != null) {
            this.name = this.fullName;
        } else if (this.fullName == null && this.name != null) {
            this.fullName = this.name;
        }
    }
}
