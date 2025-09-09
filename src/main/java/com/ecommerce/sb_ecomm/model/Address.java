package com.ecommerce.sb_ecomm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street must contain at least 5 characters")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name must contain at least 5 characters")
    private String buildingName;

    @NotBlank
    @Size(min = 4, message = "City must contain at least 4 characters")
    private String city;

    @NotBlank
    @Size(min = 2, message = "State must contain at least 2 characters")
    private String state;

    @NotBlank
    @Size(min = 2, message = "Country must contain at least 2 characters")
    private String country;

    @NotBlank
    @Size(min = 6, message = "Zip code must contain at least 6 characters")
    private String zipCode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

    public Address(String street, String buildingName, String zipCode, String country, String state, String city) {
        this.street = street;
        this.buildingName = buildingName;
        this.zipCode = zipCode;
        this.country = country;
        this.state = state;
        this.city = city;
    }
}
