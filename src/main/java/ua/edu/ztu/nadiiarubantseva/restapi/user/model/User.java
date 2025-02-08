package ua.edu.ztu.nadiiarubantseva.restapi.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
}
