package ru.event.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestBody;
import ru.event.dto.EntertainmentType;
import ru.event.entity.Event;
import ru.event.entity.User;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

  @Query(nativeQuery = true, value = "SELECT * from event e where e.startDate >= ?3 and e.endDate <= ?4 and event.entertainmentType in (?5) limit = ?1 offset = ?1 * ?2")
  List<User> getListEvent(int pageSize, int pageNumber, Date startDate, Date endDate, List<EntertainmentType> entertainmentType);

  @Query(nativeQuery = true, value = "SELECT * from app_user order by random() limit 1")
  User getRandomUser();
}
