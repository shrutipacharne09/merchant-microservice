package com.example.BaraniCaseStudy.model;

import com.example.BaraniCaseStudy.model.Merchant;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "email")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email ID is mandatory")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String emailId;

    @NotNull(message = "Email classification is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmailClassification classification;

    @ManyToOne
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;
}
