package bd.ohedulalam.securityExample.repository;

import bd.ohedulalam.securityExample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(int user_id);

    User findByEmail(String s);
}
