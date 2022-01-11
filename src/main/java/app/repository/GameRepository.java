package app.repository;

import app.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for CRUD operations for {@link Game} objects.
 */
@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
}
