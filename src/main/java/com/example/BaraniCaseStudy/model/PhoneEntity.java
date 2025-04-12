package com.example.BaraniCaseStudy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "phone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Contact number is mandatory")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid phone number format")
    @Column(nullable = false, unique = true)
    private String contactNumber;

    @NotNull(message = "Contact type is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;
}
