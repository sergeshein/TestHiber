package com.example.testhiber.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    private final static String SEQ_NAME = "user_seq";
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(sequenceName = SEQ_NAME, name = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String name;
    private String password;
    private String email;
    private  boolean archived;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(
            mappedBy = "user",
//            orphanRemoval = true,
//            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE

    )
    private Bucket bucket;

}
