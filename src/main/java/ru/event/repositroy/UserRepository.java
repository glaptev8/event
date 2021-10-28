package ru.event.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.event.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String name);

  @Query(nativeQuery = true, value = "SELECT * from app_user order by random() limit 1")
  User getRandomUser();
}
