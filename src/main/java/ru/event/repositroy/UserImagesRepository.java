package ru.event.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.event.entity.UserImages;

@Repository
public interface UserImagesRepository extends JpaRepository<UserImages, Long> {
}
