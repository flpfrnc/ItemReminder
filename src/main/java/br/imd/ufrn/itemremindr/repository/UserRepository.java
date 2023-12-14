package br.imd.ufrn.itemremindr.repository;


import br.imd.ufrn.itemremindr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByUsername(String login);
}
