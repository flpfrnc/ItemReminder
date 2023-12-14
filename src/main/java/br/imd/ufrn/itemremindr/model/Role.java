package br.imd.ufrn.itemremindr.model;

import br.imd.ufrn.itemremindr.roleEnum.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UserRole name;
}