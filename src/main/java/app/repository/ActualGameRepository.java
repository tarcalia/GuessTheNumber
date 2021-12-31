package app.repository;

import app.domain.ActualGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for CRUD operations for {@link ActualGame} objects.
 */
@Repository
public interface ActualGameRepository extends CrudRepository<ActualGame, Integer> {
}
