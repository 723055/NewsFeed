package org.test.root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.root.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByLogin(String login);
}
